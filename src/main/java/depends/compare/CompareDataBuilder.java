package depends.compare;

import depends.data.bean.CompareRecord;
import depends.data.bean.DependencyRecord;
import depends.data.bean.Record;
import depends.deptypes.DependencyType;
import depends.format.matrix.DependencyMatrix;

import java.util.*;

/**
 * Created by xzy on 2018/11/27.
 */
public class CompareDataBuilder {
	private int versionAmount;
	private String projectName;
	// DependencyMatrix中包含Nodes（ArrayList<String>）和Relations（Map）
    private List<DependencyMatrix> dependencyMatrixs;

    // Map: 文件名 -> 文件ID（包含所有版本中的所有文件）
    private Map<String, Integer> fileUnionMap;

    // 用重新编号过的文件ID生成新的Relation Map
    private List<Map<Integer, Map<Integer, Map<String, Integer>>>> regeneratedRelations;
    
    // 标记每个文件相比于上一版本，存在与否
    // new file id -> existence code: 0 -> only in pre, 1 -> only in cur, 2 -> both
    private List<Map<Integer, Integer>> fileExistenceMaps;
    
    /* Compare Result : src -> dst -> change type code ( -> summary of overall change)
     * [change type code]: 0 -> disappear, 1 -> occur, 2 -> type change, 3 -> amount change but same types
     * */
    
	private List<Map<Integer, Map<Integer, Integer>>> compResultMaps;
	
	/* Change Detail: dependency type -> src -> dst -> changeDetail
	 * changeDetail(List<Integer>): [0]version, [1]change type code, [2]changed weights
	 * [change type code]: 0 -> disappear, 1 -> occur, 2 -> change
     */
	private Map<String, Map<Integer, Map<Integer, List<List<Integer>>>>> changeDetailMap;
	
	// Package Hierarchy Tree root node
	private List<PackageTreeNode> roots;
	
	private List<CompareRecord> compareRecords;

    private List<DependencyRecord> records = new ArrayList<>();

    public CompareDataBuilder(String projectName, List<DependencyMatrix> dependencyMatrixs) {
    	this.projectName = projectName;
    	this.dependencyMatrixs = dependencyMatrixs;
    	this.versionAmount = dependencyMatrixs.size();
        fileUnionMap = new TreeMap<>();
        regeneratedRelations = new ArrayList<>();
        fileExistenceMaps = new ArrayList<>();
        compResultMaps = new ArrayList<>();
        roots = new ArrayList<>();
        compareRecords = new ArrayList<>();
        initChangeDetailMap();
    }
    
    private void initChangeDetailMap() {
        changeDetailMap = new HashMap<>();
        ArrayList<String> allDependencyTypes = DependencyType.allDependencies();
        allDependencyTypes.stream().forEach(dependencyType -> {
        	changeDetailMap.put(dependencyType, new HashMap<>());
        });        
    }

    public void build(){
        // Step 1: 对所有版本中出现的文件进行重新编号
        buildNodes();
        // Step 2: 更新Relation中相应的文件编号
        buildRelations();
        
        for(int i=0; i<versionAmount-1; i++) {
            // Step 3: generate compare result into compResultMap
        	compResultMaps.add(buildCompResultBetween(i, i+1));
        }
        // Step 4: generate change detail into changeDetailMap, grouped by dependency types
        for(String depType: changeDetailMap.keySet()) {
        	buildChangeDetailFor(depType);
        }
        // 生成写入数据库的CompareRecord List
        buildCompareRecordList();
    }

    private void buildNodes() {
    	//有序Set
        SortedSet<String> fileUnionSet = new TreeSet<String>();
        for(DependencyMatrix matrix: dependencyMatrixs) {
        	matrix.getNodes().stream().forEach(fileName -> fileUnionSet.add(fileName));
        }

        // fileUnionMap: fileName -> new file id
        int i=0;
        for(String fileName: fileUnionSet) {
            fileUnionMap.put(fileName,i++);
        }

        // fileExistenceMap: new file id -> [0]pre[1]cur[2]both
        DependencyMatrix preMatrix, curMatrix;
        Set<String> fileNameSetBetween = new HashSet<>();
        for(int k=0; k<versionAmount-1; k++) {
        	fileExistenceMaps.add(new HashMap<>());
        	preMatrix = dependencyMatrixs.get(k);
        	curMatrix = dependencyMatrixs.get(k+1);
        	
        	fileNameSetBetween.addAll(preMatrix.getNodes());
        	fileNameSetBetween.addAll(curMatrix.getNodes());
        	
        	for(String fileName: fileNameSetBetween) {
        		int fileId = fileUnionMap.get(fileName);
	        	if(preMatrix.getNodes().contains(fileName) && curMatrix.getNodes().contains(fileName))
	            	fileExistenceMaps.get(k).put(fileId, 2);
	            else if(!preMatrix.getNodes().contains(fileName))
	            	fileExistenceMaps.get(k).put(fileId, 1);
	            else if(!curMatrix.getNodes().contains(fileName)){
					fileExistenceMaps.get(k).put(fileId, 0);
				}
        	}
            // build Package Hierarchy Tree
        	roots.add(buildPackageHierarchy(k));
        	fileNameSetBetween.clear();
        }
    }

    private void buildRelations(){
    	for(int i=0; i<versionAmount; i++) {
    		DependencyMatrix matrix = dependencyMatrixs.get(i);
    		Map<Integer, Map<Integer, Map<String, Integer>>> regeneratedRelation = new HashMap<>();
    		for(Map.Entry<Integer, Map<Integer, Map<String, Integer>>> srcEntry: matrix.getRelations().entrySet()) {
                Map<Integer, Map<String, Integer>> dstMap = new HashMap<>();
                for(Map.Entry<Integer, Map<String, Integer>> dstEntry: srcEntry.getValue().entrySet()) {
                    dstMap.put(getNewFileId(i, dstEntry.getKey()), dstEntry.getValue());
                }
                regeneratedRelation.put(getNewFileId(i, srcEntry.getKey()), dstMap);
            }
    		regeneratedRelations.add(regeneratedRelation);
    		// 生成写入数据库的Dependency Record List
    		generateDependencyRecords(i, regeneratedRelation);
    	}
    }
    
    private void generateDependencyRecords(int version, Map<Integer, Map<Integer, Map<String, Integer>>> regeneratedRelation) {
    	for(Integer src : regeneratedRelation.keySet()) {
    		for(Integer dest: regeneratedRelation.get(src).keySet()) {
    			DependencyRecord record = new DependencyRecord();
    			record.setVersion(version+1);
    			record.setProjectName(projectName);
    			record.setSrc(src);
    			record.setDest(dest);
    			for(Map.Entry<String, Integer> entry: regeneratedRelation.get(src).get(dest).entrySet()) {
    				String type = entry.getKey().toLowerCase();
    				int weight = entry.getValue();
    				record.setValue(type, weight);
    			}
    			record.updateTotalValue();
    			records.add(record);
    		}
    	}
    }
    
    // 生成任意指定的两个版本之间的compResultMap
    private Map<Integer, Map<Integer, Integer>> buildCompResultBetween(int preIndex, int curIndex) {         
    	Map<Integer, Map<Integer, Integer>> compResultMap = new HashMap<>();
    	Map<Integer, Map<Integer, Map<String, Integer>>> preRelations = regeneratedRelations.get(preIndex);
    	Map<Integer, Map<Integer, Map<String, Integer>>> curRelations = regeneratedRelations.get(curIndex);
    	
    	for(Integer src: fileUnionMap.values()) {
        	 Map<Integer, Map<String, Integer>> preDstMap, curDstMap;
             
             if(preRelations.containsKey(src) && curRelations.containsKey(src)) {// src in both version
            	 preDstMap = preRelations.get(src);
            	 curDstMap = curRelations.get(src);
            	 
            	 Set<Integer> dstUnionSet = new HashSet<>();
            	 dstUnionSet.addAll(preDstMap.keySet());
            	 dstUnionSet.addAll(curDstMap.keySet());
            	 
            	 for(Integer dst: dstUnionSet) {
            		 // iterate through dst
            		 if(preDstMap.containsKey(dst) && curDstMap.containsKey(dst)) {
            			 // dst in both version, check if relation changes
            			 // 先简单判断，如果两个版本中depends条数不一样，那么一定是依赖类型有变化[2]
            			 if(preDstMap.get(dst).size() != curDstMap.size()) {
            				 compResultMap.put(src, new HashMap<>());
            				 compResultMap.get(src).put(dst, 2);
            				 continue;
            			 }
            			 // 如果depends条数相同，判断是不是每个dependType都一一对应
            			 boolean allDepTypeMatch = true;
            			 for(Map.Entry<String, Integer> depMapEntry: preDstMap.get(dst).entrySet()) {
            				 if(!curDstMap.get(dst).containsKey(depMapEntry.getKey())) {
            					 allDepTypeMatch = false;
            					 break;
            				 }
            			 }
            			 // depType不是一一对应，那么是依赖类型有变化[2]
            			 if(!allDepTypeMatch) {
            				 compResultMap.put(src, new HashMap<>());
            				 compResultMap.get(src).put(dst, 2);
            				 break;
            			 } else { // depType一一对应，开始比较每种依赖类型的数量
            				 for(Map.Entry<String, Integer> depMapEntry: preDstMap.get(dst).entrySet()) {
            					 int dstWeight = curDstMap.get(dst).get(depMapEntry.getKey());
            					 if(dstWeight != depMapEntry.getValue()) {
            						 compResultMap.put(src, new HashMap<>());
            						 compResultMap.get(src).put(dst, 3);
            						 break;
            					 }
            				 }
            			 }
            			 
            		 } else if(preDstMap.containsKey(dst) && !curDstMap.containsKey(dst)) {
            			 // relation disappear
            			 compResultMap.put(src, new HashMap<>());
        				 compResultMap.get(src).put(dst, 0);
            		 } else {
            			 // relation occur
            			 compResultMap.put(src, new HashMap<>());
        				 compResultMap.get(src).put(dst, 1);
            		 }
             	 }
             } else if(preRelations.containsKey(src) && !curRelations.containsKey(src)) {
            	 // relation disappear
        		 compResultMap.put(src, new HashMap<>());
        		 preRelations.get(src).keySet().stream().forEach( dst -> {
    				 compResultMap.get(src).put(dst, 0);                	 
        		 });
             } else if(!preRelations.containsKey(src) && curRelations.containsKey(src)){
            	 // relation occur
            	 compResultMap.put(src, new HashMap<>());
            	 curRelations.get(src).keySet().stream().forEach( dst -> {
	    			 compResultMap.get(src).put(dst, 1);
	    		 });
             }
         }
         return compResultMap;
    }
    
    // 生成任意两个指定版本之间的changeDetail,存在changeDetailMap中按照depType分组的list的特定位置
    private void buildChangeDetailFor(String depType) {
    	for(int version = 0; version<versionAmount-1; version++) {
	    	for(Integer src: compResultMaps.get(version).keySet()) {
	    		for(Integer dst: compResultMaps.get(version).get(src).keySet()) {
	    			int changeTypeCode = -1;
	    			// 先判断src->dst中是否含有指定depType
	    			Map<String, Integer> preDepMap = null, curDepMap = null;
	    			if(regeneratedRelations.get(version).get(src)!= null && 
	    					regeneratedRelations.get(version).get(src).get(dst)!=null &&
	    						regeneratedRelations.get(version).get(src).get(dst).get(depType)!=null) {
	    				preDepMap = regeneratedRelations.get(version).get(src).get(dst);
	    				changeTypeCode++;
	    			}
	    			if(regeneratedRelations.get(version+1).get(src)!= null && 
	    					regeneratedRelations.get(version+1).get(src).get(dst)!=null &&
	    						regeneratedRelations.get(version+1).get(src).get(dst).get(depType)!=null) {
	    				curDepMap = regeneratedRelations.get(version+1).get(src).get(dst);
	    				changeTypeCode+=2;
	    			}
	    			
	    			//至少有一个不为空，才向其中加入info
	    			if(preDepMap!=null || curDepMap!=null) {
	    				if(!changeDetailMap.get(depType).containsKey(src)) {
			    			changeDetailMap.get(depType).put(src, new HashMap<>());
			    		}
	    				Map<Integer, List<List<Integer>>> dstMap = changeDetailMap.get(depType).get(src);
		    			if(!dstMap.containsKey(dst)) {
		    				dstMap.put(dst, new ArrayList<>());
		    			}
		    			List<Integer> detailInfo = new ArrayList<>();
		    			detailInfo.add(version);
		    			detailInfo.add(changeTypeCode);
		    			switch(changeTypeCode) {
		    			case 0: 
							detailInfo.add(-preDepMap.get(depType));
							break;
						case 1:
							detailInfo.add(curDepMap.get(depType));
							break;
						case 2:
						case 3:
							detailInfo.add(curDepMap.get(depType)-preDepMap.get(depType));
							break;
		    			}
						dstMap.get(dst).add(detailInfo);
	    			}
	    		}
	    	}
    	}
    }
    
    // 目前只生成两个相邻版本之间的Package Hierarchy Tree，以防版本相差太远时，包结构变化太大
    private PackageTreeNode buildPackageHierarchy(int version) {
    	PackageTreeNode root = new PackageTreeNode("");
    	Set<String> fileNameSet = new HashSet<>();
        fileNameSet.addAll(dependencyMatrixs.get(version).getNodes());
        fileNameSet.addAll(dependencyMatrixs.get(version+1).getNodes());
    	
    	for(String key: fileNameSet) {
        	PackageTreeNode current = root; 
    		String[] pkgs = key.split("\\.");
    		
    		for(String pkgName: pkgs) {
    			if(current.isLeaf() || current.getChildWithName(pkgName)==null) {
    				PackageTreeNode child = new PackageTreeNode(pkgName);
    				child.setParent(current);
    				current.addChildren(child);
    				current = child;
        		} else {
        			current = current.getChildWithName(pkgName);
        		}
    		}
    	}
    	return root;
    } 
	
	private void buildCompareRecordList() {
		for(int curVersion = 0; curVersion < versionAmount-1; curVersion++) {
			Map<Integer, Map<Integer, Integer>> compResultMap = compResultMaps.get(curVersion);
			for(Integer src: compResultMap.keySet()) {
				for(Integer dst: compResultMap.get(src).keySet()) {
					CompareRecord record = new CompareRecord();
					record.setSrc(src);
					record.setDest(dst);
					record.setVersion(curVersion+1);
					record.setProjectName(projectName);
					for(String depType: DependencyType.allDependencies()) {
						if(changeDetailMap.get(depType).get(src)!=null && 
								changeDetailMap.get(depType).get(src).get(dst)!=null) {
							List<List<Integer>> changeDetails = changeDetailMap.get(depType).get(src).get(dst);
							for(List<Integer> changeDetail: changeDetails) {
								// [0]存储的是版本号信息
								if(changeDetail.get(0).intValue() == curVersion) {
									record.setValue(depType, changeDetail.get(2));
									break;
								}
							}
						}
					}
					record.updateTotalValue();
					compareRecords.add(record);
				}
			}
		}
	}

    public int getVersionAmount() {
		return versionAmount;
	}

	public Map<String, Integer> getFileUnionMap() {
        return fileUnionMap;
    }
    
    public Map<Integer, Map<Integer, Map<String, Integer>>> getRegeneratedRelation(int index) {
        return regeneratedRelations.get(index);
    }
    
    public Map<Integer, Integer> getFileExistenceMap(int index) {
		return fileExistenceMaps.get(index);
	} 

    public List<Map<Integer, Map<Integer, Integer>>> getCompResultMaps() {
    	return compResultMaps;
    }
    
	public Map<Integer, Map<Integer, Integer>> getCompResultMap(int index) {
		return compResultMaps.get(index);
	}

	public Map<Integer, Map<Integer, List<List<Integer>>>> getChangeDetailMapByDepType(String depType) {
		return changeDetailMap.get(depType);
	}

	public List<CompareRecord> getCompareRecords() {
		return compareRecords;
	}

	public PackageTreeNode getRoot(int index) {
		return roots.get(index);
	}

	public Set<Integer> getAllFileInCompResult(int index){
    	Set<Integer> allFileInCompResult = new HashSet<>();
    	allFileInCompResult.addAll(compResultMaps.get(index).keySet());
    	for(Map<Integer, Integer> dstMap: compResultMaps.get(index).values()) {
    		allFileInCompResult.addAll(dstMap.keySet());
    	}
    	return allFileInCompResult;
    }
	
	public Set<Integer> getAllFileForDepType(int index, String depType) {
		Set<Integer> allFileForDepType = new HashSet<>();
		for(Map.Entry<Integer, Map<Integer, List<List<Integer>>>> entry: changeDetailMap.get(depType).entrySet()) {
			allFileForDepType.add(entry.getKey());
			allFileForDepType.addAll(entry.getValue().keySet());
		}
		return allFileForDepType;
	}
	
	private int getNewFileId(int version, int fileId) {
		return fileUnionMap.get(dependencyMatrixs.get(version).getNodes().get(fileId));
	}

	public List<DependencyRecord> getRecords() {
		return records;
	}

}

package depends.format.compare;

import depends.deptypes.DependencyType;
import depends.format.matrix.DependencyMatrix;

import java.util.*;

import org.stringtemplate.v4.compiler.CodeGenerator.list_return;

/**
 * Created by xzy on 2018/11/27.
 */
public class CompareDataBuilder {
	// DependencyMatrix中包含Nodes（ArrayList<String>）和Relations（Map）
    private DependencyMatrix preMatrix;
    private DependencyMatrix curMatrix;

    // Map: 文件名 -> 文件ID
    private Map<String, Integer> fileUnionMap;

    // 文件ID: pre -> cur
    private List<Integer> fileIdRegeneratedPre;
    private List<Integer> fileIdRegeneratedCur;

    // 用重新编号过的文件ID生成新的Relation Map
    private Map<Integer, Map<Integer, Map<String, Integer>>> regeneratedPreRelations;
    private Map<Integer, Map<Integer, Map<String, Integer>>> regeneratedCurRelations;
    
    // Information about if a file in both or only one version.
    // fileExistenceMap: new file id -> existence code: 0 -> only in pre, 1 -> only in cur, 2 -> both
    private Map<Integer, Integer> fileExistenceMap;
    
    /* Compare Result : src -> dst -> change type code ( -> summary of overall change)
     * Change Detail: dependency type -> List<Integer>: [0]src, [1]dst, [2]change type code, [3]changed weights
     * [change type code]: 0 -> disappear, 1 -> occur, 2 -> change
     * */
	private Map<Integer, Map<Integer, Integer>> compResultMap;
	private Map<String, List<List<Integer>>> changeDetailMap;
	
	// Package Hierarchy Tree root node
	private PackageTreeNode root;

    public CompareDataBuilder(DependencyMatrix preMatrix, DependencyMatrix curMatrix) {
        this.preMatrix = preMatrix;
        this.curMatrix = curMatrix;

        fileUnionMap = new TreeMap<>();
        fileExistenceMap = new TreeMap<>();
        fileIdRegeneratedPre = new ArrayList<>();
        fileIdRegeneratedCur = new ArrayList<>();
        compResultMap = new HashMap<>();
        initChangeDetailMap();
    }
    
    private void initChangeDetailMap() {
        changeDetailMap = new HashMap<>();
        ArrayList<String> allDependencyTypes = DependencyType.allDependencies();
        allDependencyTypes.stream().forEach(dependencyType -> changeDetailMap.put(dependencyType, new ArrayList<>()));        
    }

    public void build(){
        // Step 1: remap all files in both 2 versions of projects to new file id
        buildNodes();
        // Step 2: remap the relationMap for 2 projects(主要是更新其中的文件编号)
        buildRelations();
        // Step 3: generate compare result into compResultMap
        buildCompResult();
        // Step 4: generate change detail into changeDetailMap, grouped by dependency types
        buildChangeDetail();
    }

    private void buildNodes() {
        SortedSet<String> fileUnionSet = new TreeSet<>();
        preMatrix.getNodes().stream().forEach(fileName -> fileUnionSet.add(fileName));
        curMatrix.getNodes().stream().forEach(fileName -> fileUnionSet.add(fileName));

        // fileUnionMap: fileName -> new file id
        // fileExistenceMap: new file id -> [0]pre[1]cur[2]both
        int i=0;
        for(String fileName: fileUnionSet) {
            if(preMatrix.getNodes().contains(fileName) && curMatrix.getNodes().contains(fileName))
            	fileExistenceMap.put(i, 2);
            else if(!preMatrix.getNodes().contains(fileName))
            	fileExistenceMap.put(i, 1);
            else if(!curMatrix.getNodes().contains(fileName)){
				fileExistenceMap.put(i, 0);
			}
            fileUnionMap.put(fileName,i++);
        }

        // fileIdRegenerated: [index] is preId, [value] is curId
        preMatrix.getNodes().stream().forEach(fileName -> fileIdRegeneratedPre.add(fileUnionMap.get(fileName)));
        curMatrix.getNodes().stream().forEach(fileName -> fileIdRegeneratedCur.add(fileUnionMap.get(fileName)));
        
        // build Package Hierarchy Tree here
        this.root = buildPackageHierarchy();
    }

    private void buildRelations(){
        regeneratedPreRelations = regenerateRelation(preMatrix.getRelations(), fileIdRegeneratedPre);
        regeneratedCurRelations = regenerateRelation(curMatrix.getRelations(), fileIdRegeneratedCur);
    }
    
    private void buildCompResult() {
    	// TODO: CompDotDataBuilder.addRelations() as reference
    	 Set<Integer> srcUnionSet = new HashSet<>();	// set of all src in both versions
         srcUnionSet.addAll(regeneratedPreRelations.keySet());
         srcUnionSet.addAll(regeneratedCurRelations.keySet());
         
         for(Integer src: srcUnionSet) {
        	 Map<Integer, Map<String, Integer>> preDstMap, curDstMap;
             
             if(regeneratedPreRelations.containsKey(src) && regeneratedCurRelations.containsKey(src)) {// src in both version
            	 preDstMap = regeneratedPreRelations.get(src);
            	 curDstMap = regeneratedCurRelations.get(src);
            	 
            	 Set<Integer> dstUnionSet = new HashSet<>();
            	 dstUnionSet.addAll(preDstMap.keySet());
            	 dstUnionSet.addAll(curDstMap.keySet());
            	 
            	 for(Integer dst: dstUnionSet) {
            		 // iterate through dst
            		 if(preDstMap.containsKey(dst) && curDstMap.containsKey(dst)) {
            			 // dst in both version, check if relation changes
            			 if(preDstMap.get(dst).size() != curDstMap.size()) {
            				 compResultMap.put(src, new HashMap<>());
            				 compResultMap.get(src).put(dst, 2);
            				 continue;
            			 }
            			 for(Map.Entry<String, Integer> depMapEntry: preDstMap.get(dst).entrySet()) {
            				 boolean hasDep = curDstMap.get(dst).containsKey(depMapEntry.getKey());
            				 if(!hasDep || (hasDep && !curDstMap.get(dst).get(depMapEntry.getKey()).equals(depMapEntry.getValue()))) {
            					 compResultMap.put(src, new HashMap<>());
                				 compResultMap.get(src).put(dst, 2);
                				 break;
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
             } else if(regeneratedPreRelations.containsKey(src) && !regeneratedCurRelations.containsKey(src)) {
            	 // relation disappear
        		 compResultMap.put(src, new HashMap<>());
        		 regeneratedPreRelations.get(src).keySet().stream().forEach( dst -> {
    				 compResultMap.get(src).put(dst, 0);                	 
        		 });
             } else if(!regeneratedPreRelations.containsKey(src) && regeneratedCurRelations.containsKey(src)){
            	 // relation occur
            	 compResultMap.put(src, new HashMap<>());
	    		 regeneratedCurRelations.get(src).keySet().stream().forEach( dst -> {
	    			 compResultMap.get(src).put(dst, 1);
	    		 });
             }
         }
         
    }
    
    private void buildChangeDetail() {
    	for(Integer src: compResultMap.keySet()) {
    		for(Integer dst: compResultMap.get(src).keySet()) {
    			Map<String, Integer> preDepMap, curDepMap;
    			int changeTypeCode = compResultMap.get(src).get(dst);
    			
    			switch (changeTypeCode) {
				case 0:	// relation only in pre
					preDepMap = regeneratedPreRelations.get(src).get(dst);
					for(String depType: preDepMap.keySet()) {
						ArrayList<Integer> detail = new ArrayList<>();
						detail.add(src);
						detail.add(dst);
						detail.add(changeTypeCode);
						detail.add(-preDepMap.get(depType));
						changeDetailMap.get(depType).add(detail);
					}
					break;
				case 1: // relation only in cur
					curDepMap = regeneratedCurRelations.get(src).get(dst);
					for(String depType: curDepMap.keySet()) {
						ArrayList<Integer> detail = new ArrayList<>();
						detail.add(src);
						detail.add(dst);
						detail.add(changeTypeCode);
						detail.add(curDepMap.get(depType));
						changeDetailMap.get(depType).add(detail);
					}
					break;
				case 2: // relation in both
					preDepMap = regeneratedPreRelations.get(src).get(dst);
					curDepMap = regeneratedCurRelations.get(src).get(dst);
					for(String depType: changeDetailMap.keySet()) {
    					if(preDepMap.containsKey(depType) && curDepMap.containsKey(depType)) {
    						ArrayList<Integer> detail = new ArrayList<>();
    						detail.add(src);
    						detail.add(dst);
    						detail.add(changeTypeCode);
    						detail.add(curDepMap.get(depType) - preDepMap.get(depType));
    						changeDetailMap.get(depType).add(detail);
    					}
    				}
					break;
				default:
					System.out.println("Invalid Change Type Code");
					break;
				}
    		}
    	}
    }
    
    private PackageTreeNode buildPackageHierarchy() {
    	PackageTreeNode root = new PackageTreeNode("");
    	
    	for(String key: fileUnionMap.keySet()) {
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

    private Map<Integer, Map<Integer, Map<String, Integer>>> regenerateRelation(
            Map<Integer, Map<Integer, Map<String, Integer>>> relations, List<Integer> fileIdRegenerated) {
        Map<Integer, Map<Integer, Map<String, Integer>>> result = new HashMap<>();
        for(Map.Entry<Integer, Map<Integer, Map<String, Integer>>> srcEntry: relations.entrySet()) {
            Map<Integer, Map<String, Integer>> dstMap = new HashMap<>();
            for(Map.Entry<Integer, Map<String, Integer>> dstEntry: srcEntry.getValue().entrySet()) {
                dstMap.put(fileIdRegenerated.get(dstEntry.getKey()), dstEntry.getValue());
            }
            result.put(fileIdRegenerated.get(srcEntry.getKey()), dstMap);
        }
        return result;
    }

    public Map<String, Integer> getFileUnionMap() {
        return fileUnionMap;
    }

    public Map<Integer, Map<Integer, Map<String, Integer>>> getRegeneratedCurRelations() {
        return regeneratedCurRelations;
    }

    public List<Integer> getFileIdRegeneratedPre() {
        return fileIdRegeneratedPre;
    }

    public Map<Integer, Map<Integer, Map<String, Integer>>> getRegeneratedPreRelations() {
        return regeneratedPreRelations;
    }

    public List<Integer> getFileIdRegeneratedCur() {
        return fileIdRegeneratedCur;
    }
    
    public Map<Integer, Integer> getFileExistenceMap() {
		return fileExistenceMap;
	}

	public Map<Integer, Map<Integer, Integer>> getCompResultMap() {
		return compResultMap;
	}

	public List<List<Integer>> getChangeDetailMapByDepType(String depType) {
		return changeDetailMap.get(depType);
	}

	public PackageTreeNode getRoot() {
		return root;
	}

	public Set<Integer> getAllFileInCompResult(){
    	Set<Integer> allFileInCompResult = new HashSet<>();
    	allFileInCompResult.addAll(compResultMap.keySet());
    	for(Map<Integer, Integer> dstMap: compResultMap.values()) {
    		allFileInCompResult.addAll(dstMap.keySet());
    	}
    	return allFileInCompResult;
    }
	
	public Set<Integer> getAllFileForDepType(String depType) {
		Set<Integer> allFileForDepType = new HashSet<>();
		changeDetailMap.get(depType).stream().forEach(list -> {
			allFileForDepType.add(list.get(0));
			allFileForDepType.add(list.get(1));
		});
		return allFileForDepType;
	}

}

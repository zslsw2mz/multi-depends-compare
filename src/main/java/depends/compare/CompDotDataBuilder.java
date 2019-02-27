package depends.compare;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by xzy on 2018/11/27.
 */
public class CompDotDataBuilder {
    private CompareDataBuilder compareDataBuilder;
    private String outputDotFileName;
    private int versionAmout;
    
    public CompDotDataBuilder(CompareDataBuilder compareDataBuilder, String outputDotFileName) {
        this.compareDataBuilder = compareDataBuilder;
        this.outputDotFileName = outputDotFileName;
        this.versionAmout = compareDataBuilder.getVersionAmount();
    }

    // 对每两个相邻版本，生成overall的dot文件
    public void buildAll() {
    	for(int i=0; i<versionAmout-1; i++) {
            generateOverallDotFileFor(i);
    	}
    }
    
    // 生成指定的两个版本之间的overall dot文件
    public void buildBetween(int preVersion, int curVersion) {
    	generateOverallDotFileFor(preVersion);
    }
    
    // 生成指定DepType对应的每两个相邻版本之间的change detail dot文件
    public void buildForDepType(String depType) {
    	for(int i=0; i<compareDataBuilder.getVersionAmount()-1; i++) {
    		generateDotForDepTypeBetween(depType, i);
    	}
    }
    
    // version表示pre版本号，则current版本号为version+1
    public void generateOverallDotFileFor(int version) {
    	PackageTreeNode root = compareDataBuilder.getRoot(version);    	
    	PrintWriter writer;
    	try {
    		writer = new PrintWriter(outputDotFileName+"_"+version+"_"+(version+1)+"_overall.dot");

    		// file id: qualified name
            for(Map.Entry<String, Integer> entry: compareDataBuilder.getFileUnionMap().entrySet()) {
            	writer.println("//" + entry.getValue() + ":" + entry.getKey());
            }
            
            writer.println("digraph");
            writer.println("{");
            
            for(PackageTreeNode child: root.getChildren()) {
            	addNodes(writer, child, "", version);
            }
	    	addRelations(writer,version);

            writer.println("}");
            writer.close();
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public void generateDotForDepTypeBetween(String depType, int version) {
    	PackageTreeNode root = compareDataBuilder.getRoot(version);
    	PrintWriter writer;
    	try {
			writer = new PrintWriter(outputDotFileName+"_"+depType+"_"+version+"_"+(version+1)+".dot");
			// file id: qualified name
            for(Map.Entry<String, Integer> entry: compareDataBuilder.getFileUnionMap().entrySet()) {
            	if(compareDataBuilder.getAllFileForDepType(version, depType).contains(entry.getValue()))
            		writer.println("//" + entry.getValue() + ":" + entry.getKey());
            }
            writer.println("digraph");
            writer.println("{");
            
            for(PackageTreeNode child: root.getChildren()) {
            	addNodes(writer, child, depType, version);
            }
	    	addDetailedRelations(writer, depType, version);

            writer.println("}");
            writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    private void addNodes(PrintWriter writer, PackageTreeNode current, String depType, int version) {
    	if(current.isLeaf()) {
    		Integer fileId = compareDataBuilder.getFileUnionMap().get(current.getQualifiedName());
    		Set<Integer> fileSet = depType.length()>0 ? 
    				compareDataBuilder.getAllFileForDepType(version, depType):compareDataBuilder.getAllFileInCompResult(version);
    		if(fileSet.contains(fileId)) {
    			int existenceCode = compareDataBuilder.getFileExistenceMap(version).get(fileId);
    			switch (existenceCode) {
				case 0:
					writer.println("\t" + fileId + " [style=dotted];");
					break;
				case 1:
					writer.println("\t" + fileId + " [style=bold];");
					break;
				case 2:
					writer.println("\t" + fileId + " [color=gray, style=filled];");
					break;
				default:
					System.out.println("ERROR: Invalid File Existence Code");
					break;
				}
    		}
    		return;
    	}
    	
    	// Not leaf
    	writer.println("subgraph cluster_"+current.getQualifiedName().replace('.','_'));
    	writer.println("{");
    	for(PackageTreeNode child: current.getChildren()) {
    		addNodes(writer, child, depType, version);
    	}
    	writer.println("}");  	
    }
    
    private void addRelations(PrintWriter writer, int version) {
    	Map<Integer, Map<Integer, Integer>> compResultMap = compareDataBuilder.getCompResultMap(version);
    	for(Integer src: compResultMap.keySet()) {
    		for(Integer dst: compResultMap.get(src).keySet()) {
    			int changeTypeCode = compResultMap.get(src).get(dst);
    			switch(changeTypeCode) {
    			case 0:
    				writer.println("\t" + src + " -> " + dst + " [style=dotted];");
    				break;
    			case 1:
    				writer.println("\t" + src + " -> " + dst + " [style=bold];");
    				break;
    			case 2:
    				writer.println("\t" + src + " -> " + dst + " [color=red];");
    				break;
    			default:
    				System.out.println("ERROR: Invalid Change Type Code");
    			}
    		}
    	}
    }
    
    private void addDetailedRelations(PrintWriter writer, String depType, int version) {
    	Map<Integer, Map<Integer, List<List<Integer>>>> detailInfo = compareDataBuilder.getChangeDetailMapByDepType(depType);
    	for(Integer src: detailInfo.keySet()) {
    		for(Integer dst: detailInfo.get(src).keySet()) {
    			List<List<Integer>> detailList = detailInfo.get(src).get(dst);
    			for(List<Integer> detail: detailList) {
    				if(detail.get(0).equals(version)) {
                		int changeTypeCode = detail.get(1);
                		switch(changeTypeCode) {
            			case 0:
            				writer.println("\t" + src + " -> " + dst + " [style=dotted];");
            				break;
            			case 1:
            				writer.println("\t" + src + " -> " + dst + " [style=bold];");
            				break;
            			case 2:
            				String color = detail.get(2)>0 ? "red" : "green";
            				writer.println("\t" + src + " -> " + dst + " [color="+color+"];");
            				break;
            			default:
            				System.out.println("ERROR: Invalid Change Type Code");
            			}
                		break;
        			}
    			}
    		}
    	}
    }

/*    private boolean generateDotFile(String outputDotFile) {
         Nodes(background color): DOTTED for files in pre but not cur
        *                           BOLD for files in cur but not pre
        *                           filled GRAY for files in both but whose dependencies changed
        *  Edges: DOTTED for disappeared dependencies
        *         BOLD for new coming dependencies
        *         RED for changed dependencies
        * 
        PrintWriter writer;
        try {
            writer = new PrintWriter(outputDotFile);

            List<Integer> preFiles = compareDataBuilder.getFileIdRegeneratedPre();
            List<Integer> curFiles = compareDataBuilder.getFileIdRegeneratedCur();

            for(Map.Entry<String, Integer> entry: compareDataBuilder.getFileUnionMap().entrySet()) {
            	writer.println("//" + entry.getValue() + ":" + entry.getKey());
            }
            
            writer.println("digraph");
            writer.println("{");
            addRelations(writer, preFiles, curFiles);
            writer.println("}");
            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
*/    
/*    private void addRelations(PrintWriter writer, List<Integer> preFiles, List<Integer> curFiles) {
        Map<Integer, Map<Integer, Map<String, Integer>>> preRelations = compareDataBuilder.getRegeneratedPreRelations();
        Map<Integer, Map<Integer, Map<String, Integer>>> curRelations = compareDataBuilder.getRegeneratedCurRelations();
        
        Set<Integer> srcUnionSet = new HashSet<>();	// set of all src in both versions
        srcUnionSet.addAll(preRelations.keySet());
        srcUnionSet.addAll(curRelations.keySet());
        
        for(Integer src: srcUnionSet) {
            Map<Integer, Map<String, Integer>> preDstMap, curDstMap;
            
            if(preRelations.containsKey(src) && curRelations.containsKey(src)) { // src in both versions
                preDstMap = preRelations.get(src);
                curDstMap = curRelations.get(src);

                Set<Integer> dstUnionSet = new HashSet<>();
                dstUnionSet.addAll(preDstMap.keySet());
                dstUnionSet.addAll(curDstMap.keySet());
                
                for(Integer dst: dstUnionSet) {
                    if(preDstMap.containsKey(dst) && curDstMap.containsKey(dst)) {
                    	 1. Both have relation from src -> dst, check if changes
                    	 * If yes, both src and dst Nodes be FILLED GRAY, Lines be in RED/GREEN/YELLOW
                    	 * 
                    	
                        // TO BE ENHANCED: show degree of changes
                        // Level 1: number of depTypes differs
                        if(preDstMap.get(dst).size() != curDstMap.get(dst).size()) {
                            // add Nodes in first
                            writer.println("\t" + src + " [color=gray, style=filled];");
                            writer.println("\t" + dst + " [color=gray, style=filled];");
                            writer.println("\t" + src + " -> " + dst + " [color=red];");
                        } else {
                            for(Map.Entry<String, Integer> depMapEntry: preDstMap.get(dst).entrySet()) {
                                // Level 2: number of depTypes equals, but depTypes have differences
                            	if(!curDstMap.get(dst).containsKey(depMapEntry.getKey())) {
                                    // add Nodes in first
                                    writer.println("\t" + src + " [color=gray, style=filled];");
                                    writer.println("\t" + dst + " [color=gray, style=filled];");
                                    writer.println("\t" + src + " -> " + dst + " [color=yellow];");
                                    break;
                            	}
                                // Level 3: number of each type of dependencies
                                if(!curDstMap.get(dst).get(depMapEntry.getKey()).equals(depMapEntry.getValue())) {
                                    // add Nodes in first
                                    writer.println("\t" + src + " [color=gray, style=filled];");
                                    writer.println("\t" + dst + " [color=gray, style=filled];");
                                    writer.println("\t" + src + " -> " + dst + " [color=green];");
                                    break;
                                }
                            }
                        }
                        // TODO: Level 3, 4, 5...

                    } else if(preDstMap.containsKey(dst) && !curDstMap.containsKey(dst)) {
                        // src in both but dst only in pre
                    	// Nodes: src FILLED GRAY, dst FILLED GRAY/DOTTED
                    	// Line: src -> dst only in pre, so this line is DOTTED
                    	writer.println("\t"+ src + " [color=gray, style=filled];");
                    	if(curFiles.contains(dst))
                        	writer.println("\t"+ dst + " [color=gray, style=filled];");
                    	else
                    		writer.println("\t"+ dst + " [style=dotted];");
                        writer.println("\t" + src + " -> " + dst + " [style=dotted];");
                    } else {
                    	// src in both but dst only in cur
                    	// Nodes: src FILLED GRAY, dst FILLED GRAY/BOLD
                        // src -> dst only in cur, so this line is BOLD
                    	writer.println("\t"+ src + " [color=gray, style=filled];");
                    	if(preFiles.contains(dst))
                        	writer.println("\t"+ dst + " [color=gray, style=filled];");
                    	else
                    		writer.println("\t"+ dst + " [style=bold];");
                        writer.println("\t" + src + " -> " + dst + " [style=bold];");
                    }
                }

            } else if(preRelations.containsKey(src) && !curRelations.containsKey(src)) {
                // src in preRelations but not curRelations
            	if(curFiles.contains(src)) {
            		// src in cur but no relations on that
            		writer.println("\t"+ src + " [color=gray, style=filled];");
            	} else {
            		// src only in pre 
            		writer.println("\t"+ src + " [style=dotted];");
            	}
                preRelations.get(src).keySet().stream().forEach( dst -> {
                	if(curFiles.contains(dst))
                		writer.println("\t"+ dst + " [color=gray, style=filled];");
                	else
                		writer.println("\t"+ dst + " [style=dotted];");
                	writer.println("\t" + src + " -> " + dst + " [style=dotted];");
                });
            } else if(!preRelations.containsKey(src) && curRelations.containsKey(src)) {            	
            	if(preFiles.contains(src)) {
            		// src in pre but no relations on that
            		writer.println("\t"+ src + " [color=gray, style=filled];");
            	} else {
            		// src only in cur 
            		writer.println("\t"+ src + " [style=bold];");
            	}
                curRelations.get(src).keySet().stream().forEach( dst -> {
                	if(preFiles.contains(dst))
                		writer.println("\t"+ dst + " [color=gray, style=filled];");
                	else
                		writer.println("\t"+ dst + " [style=bold];");
                	writer.println("\t" + src + " -> " + dst + " [style=bold];");
                });            
            }
        }

    }
*/}

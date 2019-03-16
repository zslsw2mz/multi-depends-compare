package depends.compare;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class DiffDotDataBuilder {
	private DiffCompareDataBuilder diffCompareDataBuilder;
	private String outputFileName;
	
	public DiffDotDataBuilder(DiffCompareDataBuilder diffCompareDataBuilder, String outputFileName) {
		this.diffCompareDataBuilder = diffCompareDataBuilder;
		this.outputFileName = outputFileName;
	}
	
	public void buildAll() {
		for(int i=0; i<diffCompareDataBuilder.getVersionAmount()-2; i++) {
			generateDiffDotFileFor(i);
		}
	}
	
	/* changeTypeCode有4个值：[0]disappear [1]occur [2]type change [3]amount change but types the same
	 * 将[3]并入[2]中
	 * 组合的情况有几种，设置不同颜色的连线：
	 * a. 0+1	green
	 * b. 1+0	yellow
	 * c. 1+2	bold(black)
	 * d. 2+0	dotted(black)
	 * e. 2+2	red
	 * */
	public void generateDiffDotFileFor(int index) {
		PrintWriter writer;
		Map<Integer, Map<Integer, List<Integer>>> diffMap = diffCompareDataBuilder.getDiffResultMap(index);
		try {
			writer = new PrintWriter(outputFileName+"_"+index+"_"+(index+1)+"_"+(index+2)+"_diff.dot");
			
			// 1.写文件id：文件名注释
			Set<Integer> fileIdSet = new HashSet<>();
			fileIdSet.addAll(diffMap.keySet());
			for(Integer src: diffMap.keySet()) {
				fileIdSet.addAll(diffMap.get(src).keySet());
			} 
			for(Map.Entry<String, Integer> entry: diffCompareDataBuilder.getFileUnionMap().entrySet()) {
            	if(fileIdSet.contains(entry.getValue()))
            		writer.println("//" + entry.getValue() + ":" + entry.getKey());
            }
			
			// 2. 写Node和Relations
			writer.println("digraph");
            writer.println("{");
			for(Integer src: diffMap.keySet()) {
				for(Integer dst: diffMap.get(src).keySet()) {
					//Nodes
					writer.println("\t" + src + ";");
					writer.println("\t" + dst + ";");
					
					//Relations
					int pre = diffMap.get(src).get(dst).get(0);
					int cur = diffMap.get(src).get(dst).get(1);
					if(pre==0 && cur==1) {
						writer.println("\t" + src + " -> " + dst + " [color=green];");
					} else if(pre==1) {
						if(cur==0)
							writer.println("\t" + src + " -> " + dst + " [color=yellow];");
						else if(cur==2 || cur==3) 
							writer.println("\t" + src + " -> " + dst + " [style=bold];");
					} else if(pre==2 || pre==3) {
						if(cur==0)
							writer.println("\t" + src + " -> " + dst + " [style=dotted];");
						else if(cur==2 || cur==3) 
							writer.println("\t" + src + " -> " + dst + " [color=red];");
					}
				}
			}
			writer.println("}");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

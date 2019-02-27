package depends.compare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 比较compareResult之间的差别
 * 1. Basic：两个compareResultMap之间，overall级别的对比
 * 2. 针对某一个类，找出与其相关的依赖关系在所有版本演化过程中的变化
 * 3. 针对某一类型的依赖关系？
 * */
public class DiffCompareDataBuilder {
	private List<Map<Integer, Map<Integer, Integer>>> compResultMaps;
	private Map<String, Integer> fileUnionMap;
	private List<Map<Integer, Map<Integer, List<Integer>>>> diffResultMaps;
	private int versionAmount;
	
	public DiffCompareDataBuilder(CompareDataBuilder compareDataBuilder) {
		this.compResultMaps = compareDataBuilder.getCompResultMaps();
		this.fileUnionMap = compareDataBuilder.getFileUnionMap();
		this.versionAmount = compareDataBuilder.getVersionAmount();
		this.diffResultMaps = new ArrayList<>();
	}
	
	public void build() {
		for(int i=0; i<compResultMaps.size()-1; i++) {
			buildDiffBetween(i, i+1);
		}
	}
	
	public void buildDiffBetween(int preIndex, int curIndex) {
		Map<Integer, Map<Integer, Integer>> preMap = compResultMaps.get(preIndex);
		Map<Integer, Map<Integer, Integer>> curMap = compResultMaps.get(curIndex);
		
		for(Integer src: preMap.keySet()) {
			for(Integer dst: preMap.get(src).keySet()) {
				if(curMap.containsKey(src) && curMap.get(src).containsKey(dst)) {
					Map<Integer, Map<Integer, List<Integer>>> result = new HashMap<>();
					result.put(src, new HashMap<>());
					result.get(src).put(dst, new ArrayList<>());
					result.get(src).get(dst).add(preMap.get(src).get(dst));
					result.get(src).get(dst).add(curMap.get(src).get(dst));
					this.diffResultMaps.add(result);
					
				}
			}
		}
	}
	
	public Map<Integer, Map<Integer, List<Integer>>> getDiffResultMap(int index){
		return this.diffResultMaps.get(index);
	}

	public Map<String, Integer> getFileUnionMap() {
		return fileUnionMap;
	}

	public int getVersionAmount() {
		return versionAmount;
	}
}

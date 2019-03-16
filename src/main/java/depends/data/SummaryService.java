package depends.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import depends.deptypes.DependencyType;
import depends.format.matrix.DependencyMatrix;
import depends.util.Tuple;

public class SummaryService {
	private List<DependencyMatrix> dependencyMatrixs;
	private Map<String, List<Integer>> depMap;
	private List<Map<Integer, Map<Integer, Integer>>> pairMaps;
	private int totalWeight;
	private DependencyDAO dependencyDAO;
	
	public SummaryService(List<DependencyMatrix> dependencyMatrixs, DependencyDAO dependencyDAO) {
		this.dependencyMatrixs = dependencyMatrixs;
		this.dependencyDAO = dependencyDAO;
		this.depMap = new HashMap<>();
		this.pairMaps = new ArrayList<>();
		this.init();
	}
	
	public void init() {
        for(String depType: DependencyType.allDependencies()) {
        	depMap.put(depType, new ArrayList<>());
        }
        
        totalWeight = 0;
        for(int version = 0; version<this.dependencyMatrixs.size(); version ++) {
        	DependencyMatrix dependencyMatrix = this.dependencyMatrixs.get(version);  	
            Map<Integer, Map<Integer, Integer>> pairMap = new HashMap<>();
            
	        for(Integer src: dependencyMatrix.getRelations().keySet()) {
	        	for(Integer dst: dependencyMatrix.getRelations().get(src).keySet()) {
	        		int sum = 0;
	        		for(Map.Entry<String, Integer> entry: dependencyMatrix.getRelations().get(src).get(dst).entrySet()){
	        			sum += entry.getValue();
	        			totalWeight += entry.getValue();
	        			depMap.get(entry.getKey()).add(depMap.get(entry.getKey()).get(version)+1);
	        		}
	        		pairMap.put(src, new HashMap<>());
	        		pairMap.get(src).put(dst, sum);
	        	}
	        }
	        this.pairMaps.add(pairMap);
        }
	}
	
	
	public int getTotalFileAmount(int version) {
		return dependencyMatrixs.get(version).getNodes().size();
	}
	
	public int getPairAmount(int version) {
		return this.dependencyMatrixs.get(version).getRelations().size();
	}
	
	public int getTotalDependencyAmount(int version) {
		return this.totalWeight;
	}
	
	public List<Integer> getTightestPair(int version) {
		List<Integer> result = new ArrayList<>(); //[src, dest, weight]
		// 访问数据库方式
		
		// 在内存中计算
		/*int largestPairNum = 0;
        int largestSrc = -1, largestDst = -1;
        for(Integer src: pairMaps.get(version).keySet()) {
        	for(Map.Entry<Integer, Integer> entry: pairMaps.get(version).get(src).entrySet()) {
        		if(entry.getValue() > largestPairNum) {
        			largestSrc = src;
        			largestDst = entry.getKey();
        			largestPairNum = entry.getValue();
        		}
        	}
        }        
        System.out.println("Class pair with largest dependency amount is "+largestSrc+" and "+largestDst+", the weight is "+largestPairNum);
        result.add(largestSrc);
        result.add(largestDst);
        result.add(largestPairNum);*/
        return result;
	}
	
	public Tuple<String, Integer> getTheMostDepType(int version) {
		Tuple<String, Integer> result = new Tuple<>();
		int largestDepNum = 0;
        String largestDepKey = "";
        for(String depType: this.depMap.keySet()) {
        	if(this.depMap.get(depType).get(version) > largestDepNum) {
        		largestDepKey = depType;
        		largestDepNum = depMap.get(depType).get(version);
        	}
        }        
        System.out.println("Dependency Type with largest weight is "+largestDepKey+", the weight is "+largestDepNum);
        result.setX(largestDepKey);
        result.setY(largestDepNum);
        return result;
	}
	
	public List<Integer> getPairWithMostDepType(int version, String depType){
		List<Integer> result = new ArrayList<>(); //[src, dest, weight]
		
		return result;
	}
}

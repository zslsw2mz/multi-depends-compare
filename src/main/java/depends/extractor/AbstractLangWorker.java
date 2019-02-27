package depends.extractor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.plexus.util.FileUtils;

import depends.deptypes.DependencyType;
import depends.entity.Entity;
import depends.entity.repo.EntityRepo;
import depends.format.dot.DotDataBuilder;
import depends.format.excel.ExcelDataBuilder;
import depends.format.json.JDataBuilder;
import depends.format.json.JDepObject;
import depends.format.json.JsonFormatter;
import depends.format.matrix.DependencyMatrix;
import depends.format.matrix.FileDependencyGenerator;
import depends.format.xml.XDataBuilder;
import depends.format.xml.XDepObject;
import depends.format.xml.XmlFormatter;
import depends.util.Configure;
import depends.util.FileTraversal;
abstract public class AbstractLangWorker {
	public AbstractLangWorker(Configure configure) {
		this.configure = configure;
		entityRepo = new EntityRepo();
	}
	public void register() {
		LangWorkers.getRegistry().register(this);
	}
	public abstract String supportedLanguage();
	
	public abstract String[] fileSuffixes();
	
    private Configure configure ;

    DependencyMatrix dependencyMatrix;

    protected EntityRepo entityRepo = new EntityRepo();
    
	public void work() {
        parseAllFiles();
        resolveBindings();
        identifyDependencies();
        
        //TODO: remove
        Map<String, Integer> depMap = new HashMap<>();
        for(String depType: DependencyType.allDependencies()) {
        	depMap.put(depType, 0);
        }
        Set<Integer> fileInvolved = new HashSet<>();
        fileInvolved.addAll(dependencyMatrix.getRelations().keySet());
        
        Map<Integer, Map<Integer, Integer>> pairMap = new HashMap<>();
        int totalWeight = 0;
        for(Integer src: dependencyMatrix.getRelations().keySet()) {
        	fileInvolved.addAll(dependencyMatrix.getRelations().get(src).keySet());
        	for(Integer dst: dependencyMatrix.getRelations().get(src).keySet()) {
        		int sum = 0;
        		for(Map.Entry<String, Integer> entry: dependencyMatrix.getRelations().get(src).get(dst).entrySet()){
        			sum += entry.getValue();
        			totalWeight += entry.getValue();
        			depMap.put(entry.getKey(), depMap.get(entry.getKey())+1);
        		}
        		pairMap.put(src, new HashMap<>());
        		pairMap.get(src).put(dst, sum);
        	}
        }
        // find the largest
        int largestDepNum = 0;
        String largestDepKey = "";
        for(String depType: depMap.keySet()) {
        	if(depMap.get(depType) > largestDepNum) {
        		largestDepKey = depType;
        		largestDepNum = depMap.get(depType);
        	}
        }
        System.out.println("Dependency Type with largest weight is "+largestDepKey+", the weight is "+largestDepNum);
        
        int largestPairNum = 0;
        int largestSrc = -1, largestDst = -1;
        for(Integer src: pairMap.keySet()) {
        	for(Map.Entry<Integer, Integer> entry: pairMap.get(src).entrySet()) {
        		if(entry.getValue() > largestPairNum) {
        			largestSrc = src;
        			largestDst = entry.getKey();
        			largestPairNum = entry.getValue();
        		}
        	}
        }
        System.out.println("Class pair with largest dependency amount is "+largestSrc+" and "+largestDst+", the weight is "+largestPairNum);
        System.out.println("Total amount of class pairs: "+ dependencyMatrix.getRelations().size());
        System.out.println("Total amount of dependencies: " + totalWeight);
        System.out.println("Total file in this version of project: "+ dependencyMatrix.getNodes().size());
        System.out.println("Total file involved in dependency relations: "+ fileInvolved.size());
	}

	private void outputErrors() {
		List<String> errors = getErrors();
		for (String e:errors) {
			System.err.println(e);
		}
	}
	
	/**
	 * Errors during all execution steps. could be extend as several methods in future
	 * @return
	 */
	public abstract List<String> getErrors();
	/**
	 * 
	 * @return unsolved bindings
 	 */
    protected void resolveBindings() {
        System.out.println("start resolve bindings...");
        Set<String> unsolved = entityRepo.resolveAllBindings();
        if (unsolved.size()>0)
        	System.err.println("The following items are unsolved." + unsolved);
        System.out.println("bindings resolved successfully...");
    }
    
    private void identifyDependencies(){
        System.out.println("dependencie data generating...");	
        FileDependencyGenerator dependencyGenerator= new FileDependencyGenerator();
        dependencyMatrix  = dependencyGenerator.buildWithRemap(entityRepo);
        System.out.println("dependencie data generating done successfully...");	 	
    }

    private final void parseAllFiles() {
        System.out.println("start parsing files...");		
    	FileTraversal fileTransversal = new FileTraversal(new FileTraversal.IFileVisitor(){
			@Override
			public void visit(File file) {
	            FileParser fileParser = getFileParser(file.getAbsolutePath());
	            try {
	                System.out.println("parsing " + file.getAbsolutePath()
	                		+ "...");		
	                fileParser.parse();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }	
			}
    		
    	});
    	fileTransversal.extensionFilter(this.fileSuffixes());
		fileTransversal.travers(configure.getInputSrcPath());
        System.out.println("all files procceed successfully...");		
	}
    
    protected abstract FileParser getFileParser(String fileFullPath);
    

	private final void outputDeps(ArrayList<String> depTypes) {
        JDataBuilder jBuilder = new JDataBuilder();
        JDepObject jDepObject = jBuilder.build(dependencyMatrix,configure);
        JsonFormatter jasonFormatter = new JsonFormatter();
        jasonFormatter.toJson(jDepObject,configure.getOutputJsonFile());
        System.out.println("Export " + configure.getOutputJsonFile() + " successfully...");

        XDataBuilder xBuilder = new XDataBuilder();
        XDepObject xDepObject = xBuilder.build(dependencyMatrix,configure);
        XmlFormatter xmlFormatter = new XmlFormatter();
        xmlFormatter.toXml(xDepObject,configure.getOutputXmlFile());
        System.out.println("Export " + configure.getOutputXmlFile() + " successfully...");
        
		ExcelDataBuilder builder = new ExcelDataBuilder(dependencyMatrix);
		if (builder.output(configure.getOutputExcelFile())) {
			System.out.println("Export " + configure.getOutputExcelFile() + " successfully...");
		}	
		
		DotDataBuilder dotBuilder = new DotDataBuilder(dependencyMatrix);
		if (dotBuilder.output(configure.getOutputDotFile())) {
			System.out.println("Export " + configure.getOutputDotFile() + " successfully...");
		}
    }
	public List<String> includePaths() {
		String[] paths = configure.getIncludePath().split(";");
		ArrayList<String> r = new ArrayList<String>();
		for (String path:paths) {
			if (FileUtils.fileExists(path)) {
				if (!r.contains(path))
					r.add(path);
			}
			path = configure.getInputSrcPath() +File.separator+path;
			if (FileUtils.fileExists(path)) {
				if (!r.contains(path))
					r.add(path);
			}
		}
		return r;
	}
	public void outputResult() {
        outputErrors();
        outputDeps(DependencyType.allDependencies());
	}
	public DependencyMatrix getDependencies() {
		return dependencyMatrix;
	}
	public EntityRepo getEntityRepo() {
		return this.entityRepo;
	}
}

package depends;

import java.util.ArrayList;
import java.util.List;

import depends.extractor.AbstractLangWorker;
import depends.extractor.LangWorkers;
import depends.extractor.java.JavaWorker;
import depends.compare.CompDotDataBuilder;
import depends.compare.CompareDataBuilder;
import depends.compare.DepTypeXmlBuilder;
import depends.compare.DiffCompareDataBuilder;
import depends.compare.DiffDotDataBuilder;
import depends.data.DependencyDAO;
import depends.data.bean.DependencyRecord;
import depends.deptypes.DependencyType;
import depends.format.matrix.DependencyMatrix;
import depends.format.xml.XDepObject;
import depends.format.xml.XmlFormatter;
import depends.util.Configure;

public class CompMain {
	public static void main(String[] args) {
		if (args.length < 4) {
            System.out.println("Usage");
            System.out.println("\t<executable> <lang> <project-name> <dir1> ... <dirN>");
            return;
        }
        String lang = args[0];
        String projectName = args[1];
        
        Configure configure;
        AbstractLangWorker worker;
        String dir;
        List<DependencyMatrix> matrixs = new ArrayList<>();
        
        DependencyDAO dependencyDAO = new DependencyDAO();        
        for(int i=2; i<args.length; i++) {
        	dir = args[i];
        	configure = new Configure(lang, dir, "", projectName, i-1);
        	new JavaWorker(configure).register();
        	worker = LangWorkers.getRegistry().getWorkerOf(lang);
        	worker.work();
        	worker.outputResult();
        	matrixs.add(worker.getDependencies());
        }
        
        // Build CompareData for any kind of output use
        CompareDataBuilder compareDataBuilder = new CompareDataBuilder(projectName, matrixs);
        compareDataBuilder.build();
        // 将文件-ID写入数据库
        dependencyDAO.multiInsertFileIdMapping(projectName, compareDataBuilder.getFileUnionMap());
        // 将对比结果写入数据库
        dependencyDAO.multiInsertCompareRecords(compareDataBuilder.getCompareRecords());
        // 将DependencyRecord写入数据库
        dependencyDAO.multiInsertDependencyRecords(compareDataBuilder.getRecords());

        // Build diffData for compared data
        DiffCompareDataBuilder diffCompareDataBuilder = new DiffCompareDataBuilder(compareDataBuilder);
        diffCompareDataBuilder.build();
        
        // 生成Dot文件
        CompDotDataBuilder dotDataBuilder = new CompDotDataBuilder(compareDataBuilder, projectName);
        dotDataBuilder.buildAll();
        
//        DiffDotDataBuilder diffDotDataBuilder = new DiffDotDataBuilder(diffCompareDataBuilder, projectName);
//        diffDotDataBuilder.buildAll();
        
        // 生成指定depType对应的演化dot文件
        for(String depType: DependencyType.allDependencies()) {
        	// 每两个相邻版本之间指定depType的dot文件
        	dotDataBuilder.buildForDepType(depType);
        	// 记录每对src->dst随着版本变化，依赖关系的变化的xml文件
        	DepTypeXmlBuilder depTypeXmlBuilder = new DepTypeXmlBuilder(compareDataBuilder, depType);
        	XDepObject xDepObject = depTypeXmlBuilder.build();
        	XmlFormatter xmlFormatter = new XmlFormatter();
        	xmlFormatter.toXml(xDepObject, projectName+"_"+depType+".xml");
        }
	}
}


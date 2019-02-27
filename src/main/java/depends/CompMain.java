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
import depends.format.matrix.DependencyMatrix;
import depends.format.xml.XDepObject;
import depends.format.xml.XmlFormatter;
import depends.util.Configure;

public class CompMain {
	public static void main(String[] args) {
		if (args.length < 5) {
            System.out.println("Usage");
            System.out.println("\t<executable> <lang> <project-name> <dependencyType> <dir1> ... <dirN>");
            return;
        }
        String lang = args[0];
        String projectName = args[1];
        String depType = args[2];
        
        Configure configure;
        AbstractLangWorker worker;
        String dir;
        List<DependencyMatrix> matrixs = new ArrayList<>();
        
        for(int i=3; i<args.length; i++) {
        	dir = args[i];
        	configure = new Configure(lang, dir, "", projectName+"_"+(i-2));
        	new JavaWorker(configure).register();
        	worker = LangWorkers.getRegistry().getWorkerOf(lang);
        	worker.work();
        	matrixs.add(worker.getDependencies());
        }
        
        // Build CompareData for any kind of output use
        CompareDataBuilder compareDataBuilder = new CompareDataBuilder(matrixs);
        compareDataBuilder.build();

        // Build diffData for compared data
        DiffCompareDataBuilder diffCompareDataBuilder = new DiffCompareDataBuilder(compareDataBuilder);
        diffCompareDataBuilder.build();
        
        // 生成Dot文件
        CompDotDataBuilder dotDataBuilder = new CompDotDataBuilder(compareDataBuilder, projectName);
        dotDataBuilder.buildAll();
        
        DiffDotDataBuilder diffDotDataBuilder = new DiffDotDataBuilder(diffCompareDataBuilder, projectName);
        diffDotDataBuilder.buildAll();
        
        // 生成指定depType对应的演化dot文件
        if(depType.length() > 0) {
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


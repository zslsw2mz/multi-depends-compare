package depends;

import depends.extractor.AbstractLangWorker;
import depends.extractor.LangWorkers;
import depends.extractor.java.JavaWorker;
import depends.format.compare.CompDotDataBuilder;
import depends.format.matrix.DependencyMatrix;
import depends.util.Configure;

public class CompMain {
	public static void main(String[] args) {
		if (args.length < 4) {
            System.out.println("Usage");
            System.out.println("\t<executable> <lang> <dir1> <dir2> <project-name>");
            return;
        }
        String lang = args[0];
        String preDir = args[1];
        String curDir = args[2];
        String projectName = args[3];

        // Parsing preDir
        Configure configure = new Configure(lang, preDir, "", "pre_"+projectName);
        new JavaWorker(configure).register();
        AbstractLangWorker worker = LangWorkers.getRegistry().getWorkerOf(lang);
        worker.work();
        DependencyMatrix preMatrix = worker.getDependencies();

        // Parsing curDir
        configure = new Configure(lang, curDir, "", "cur_"+projectName);
        new JavaWorker(configure).register();
        worker = LangWorkers.getRegistry().getWorkerOf(lang);
        worker.work();
        DependencyMatrix curMatrix = worker.getDependencies();

        // Compare
        CompDotDataBuilder dotDataBuilder = new CompDotDataBuilder();
        dotDataBuilder.build(preMatrix, curMatrix, "compTest");
	}
}


package depends.util;

import depends.extractor.AbstractLangWorker;

public class Configure {
    public Configure(String lang, String inputDir, String includeDir, String projectName) {
        setInputSrcPath(inputDir);
        setIncludePath(includeDir);
        setProjectName(projectName);
    }

	private String  inputSrcPath;
    private String  includeSrcPath;
    private String  projectName ;

    private String  schemaVersion = "1.0";

    public String getInputSrcPath() {
        return inputSrcPath;
    }

    public void setInputSrcPath(String inputSrcPath) {
        this.inputSrcPath = inputSrcPath;
    }

    public String getIncludePath() {
        return includeSrcPath;
    }

    public void setIncludePath(String includePath) {
        this.includeSrcPath = includePath;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOutputJsonFile() {
        return projectName  + "_dep.json";
    }

    public String getOutputXmlFile() {
    	return projectName + "_dep.xml";
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public String getAttributeName() {
        return projectName + "-sdsm";
    }

	public String getOutputExcelFile() {
		return projectName + "_dep.xls";
	}
	
	public String getOutputDotFile() {
		return projectName + "_dep.dot";
	}

}


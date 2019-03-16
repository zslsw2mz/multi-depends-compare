package depends.util;

public class Configure {
    public Configure(String lang, String inputDir, String includeDir, String projectName) {
        setInputSrcPath(inputDir);
        setIncludePath(includeDir);
        setProjectName(projectName);
    }
    
    public Configure(String lang, String inputDir, String includeDir, String projectName, int version) {
    	this(lang, inputDir, includeDir, projectName);
    	this.version = version;
    }

	private String  inputSrcPath;
    private String  includeSrcPath;
    private String  projectName ;
    private int version;
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

    public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getOutputJsonFile() {
        return projectName  + "_dep_v"+ version +".json";
    }

    public String getOutputXmlFile() {
    	return projectName + "_dep_v"+ version +".xml";
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public String getAttributeName() {
        return projectName + "-sdsm";
    }

	public String getOutputExcelFile() {
		return projectName + "_dep_v"+ version +".xls";
	}
	
	public String getOutputDotFile() {
		return projectName + "_dep_v"+ version +".dot";
	}
	
	public String getOutputTxtFile() {
		return projectName + "_notable_packages_v"+version+".txt";
	}

}


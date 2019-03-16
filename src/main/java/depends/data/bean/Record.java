package depends.data.bean;

import java.io.Serializable;

public abstract class Record implements Serializable {
	private static final long serialVersionUID = 2265162867663580233L;
	private int src;
	private int dest;
	private int version;
	private String projectName;
	private int import_value;
	private int contain_value;
	private int implement_value;
	private int extend_value;
	private int call_value;
	private int parameter_value;
	private int return_value;
	private int set_value;
	private int use_value;
	private int create_value;
	private int cast_value;
	private int throw_value;
	private int annotation_value;
	private int total_value;
	
	public void updateTotalValue() {
		this.total_value = import_value + contain_value + implement_value + extend_value + 
				call_value + parameter_value + return_value + set_value + use_value + 
				create_value + cast_value + throw_value + annotation_value;
	}
	
	public void setValue(String label, int value) {
		switch(label.toLowerCase()) {
		case "import":
			this.import_value = value;
			break;
		case "contain":
			this.contain_value = value;
			break;
		case "implement":
			this.implement_value = value;
			break;
		case "extend":
			this.extend_value = value;
			break;
		case "call":
			this.call_value = value;
			break;
		case "parameter":
			this.parameter_value = value;
			break;
		case "return":
			this.return_value = value;
			break;
		case "set":
			this.set_value = value;
			break;
		case "use":
			this.use_value = value;
			break;
		case "create":
			this.create_value = value;
			break;
		case "cast":
			this.cast_value = value;
			break;
		case "throw":
			this.throw_value = value;
			break;
		case "annotation":
			this.annotation_value = value;
			break;
		}
	}
	
	public int getSrc() {
		return src;
	}
	public void setSrc(int src) {
		this.src = src;
	}
	public int getDest() {
		return dest;
	}
	public void setDest(int dest) {
		this.dest = dest;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getImportValue() {
		return import_value;
	}
	public void setImportValue(int import_value) {
		this.import_value = import_value;
	}
	public int getContainValue() {
		return contain_value;
	}
	public void setContainValue(int contain_value) {
		this.contain_value = contain_value;
	}
	public int getImplementValue() {
		return implement_value;
	}
	public void setImplementValue(int implement_value) {
		this.implement_value = implement_value;
	}
	public int getExtendValue() {
		return extend_value;
	}
	public void setExtendValue(int extend_value) {
		this.extend_value = extend_value;
	}
	public int getCallValue() {
		return call_value;
	}
	public void setCallValue(int call_value) {
		this.call_value = call_value;
	}
	public int getParameterValue() {
		return parameter_value;
	}
	public void setParameterValue(int parameter_value) {
		this.parameter_value = parameter_value;
	}
	public int getReturnValue() {
		return return_value;
	}
	public void setReturnValue(int return_value) {
		this.return_value = return_value;
	}
	public int getSetValue() {
		return set_value;
	}
	public void setSetValue(int set_value) {
		this.set_value = set_value;
	}
	public int getUseValue() {
		return use_value;
	}
	public void setUseValue(int use_value) {
		this.use_value = use_value;
	}
	public int getCreateValue() {
		return create_value;
	}
	public void setCreateValue(int create_value) {
		this.create_value = create_value;
	}
	public int getCastValue() {
		return cast_value;
	}
	public void setCastValue(int cast_value) {
		this.cast_value = cast_value;
	}
	public int getThrowValue() {
		return throw_value;
	}
	public void setThrowValue(int throw_value) {
		this.throw_value = throw_value;
	}
	public int getAnnotationValue() {
		return annotation_value;
	}
	public void setAnnotationValue(int annotation_value) {
		this.annotation_value = annotation_value;
	}
	public int getTotalValue() {
		return total_value;
	}
	public void setTotalValue(int total_value) {
		this.total_value = total_value;
	}
}

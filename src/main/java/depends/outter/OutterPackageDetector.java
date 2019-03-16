package depends.outter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class OutterPackageDetector {
	public static final String filePath = "src/main/java/depends/outter/allPackages.txt";
	private Set<String> importedPackages;
	
	public OutterPackageDetector() {}
	public OutterPackageDetector(Set<String> importedPackages) {
		this.importedPackages = importedPackages;
	}
	
	public void detectOutterPackage() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
			String packageName;
			while((packageName = bufferedReader.readLine()) != null) {
				if(packageName.length()>0) {
					if(importedPackages.contains(packageName))
						importedPackages.remove(packageName);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void output(String fileName) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
			for(String packageName: importedPackages) {
				bufferedWriter.write(packageName+"\r\n");
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Set<String> getImportedPackages() {
		return importedPackages;
	}
	public void setImportedPackages(Set<String> importedPackages) {
		this.importedPackages = importedPackages;
	}
}

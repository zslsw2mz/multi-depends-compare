package depends.compare;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.stringtemplate.v4.compiler.CodeGenerator.list_return;

import depends.format.xml.XCell;
import depends.format.xml.XCells;
import depends.format.xml.XDepObject;
import depends.format.xml.XDepend;
import depends.format.xml.XFiles;

public class DepTypeXmlBuilder {
	private CompareDataBuilder compareDataBuilder;
	private String depType;
	
	public DepTypeXmlBuilder(CompareDataBuilder compareDataBuilder, String depType) {
		this.compareDataBuilder = compareDataBuilder;
		this.depType = depType;
	}
	
	public XDepObject build() {
		ArrayList<String> files = new ArrayList<>();
		files.addAll(compareDataBuilder.getFileUnionMap().keySet());
		
		XFiles xFiles = new XFiles();
		xFiles.setFiles(files);
		
		XCells xCells = new XCells();
		xCells.setCells(buildCellList());
		
		XDepObject xDepObject = new XDepObject();
		xDepObject.setName(depType);
		xDepObject.setVariables(xFiles);
		xDepObject.setCells(xCells);
		
		return xDepObject;
	}
	
	private ArrayList<XCell> buildCellList(){
		ArrayList<XCell> xCellList = new ArrayList<>();
		Map<Integer, Map<Integer, List<List<Integer>>>> changeDetails = compareDataBuilder.getChangeDetailMapByDepType(depType);
		for(Integer src: changeDetails.keySet()) {
			for(Integer dst: changeDetails.get(src).keySet()) {
				List<List<Integer>> detailList = changeDetails.get(src).get(dst);
				ArrayList<XDepend> xDepends = buildDependList(detailList);
				XCell xCell = new XCell();
				xCell.setSrc(src);
				xCell.setDest(dst);
				xCell.setDepends(xDepends);
				xCellList.add(xCell);
			}
		}
		return xCellList;
	}
	
	private ArrayList<XDepend> buildDependList(List<List<Integer>> detailList){
		ArrayList<XDepend> dependList = new ArrayList<>();
		for(List<Integer> detailInfo: detailList) {
			int version = detailInfo.get(0);
			String changeType = "";
			switch(detailInfo.get(1)) {
			case 0:
				changeType += "disappear";
				break;
			case 1:
				changeType += "occur";
				break;
			case 2:
				changeType += "change";
				break;
			default:
				System.out.println("ERROR: Invalid change type code");	
			}
			String name = version+"_"+(version+1)+"_"+changeType;
			float weight = (float) detailInfo.get(2);
			XDepend xDepend = new XDepend();
			xDepend.setName(name);
			xDepend.setWeight(weight);
			dependList.add(xDepend);
		}
		return dependList;
	}
}

package depends.format.compare;

import java.util.ArrayList;

public class PackageTreeNode {
	private String name;
	private PackageTreeNode parent;
	private ArrayList<PackageTreeNode> children;
	
	public PackageTreeNode(String name) {
		this.name = name;
		this.children = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public PackageTreeNode getParent() {
		return parent;
	}

	public void setParent(PackageTreeNode parent) {
		this.parent = parent;
	}

	public ArrayList<PackageTreeNode> getChildren() {
		return children;
	}

	public void addChildren(PackageTreeNode node) {
		this.children.add(node);
	}
	
	public boolean isLeaf() {
		return this.children==null || this.children.size()==0;
	}
	
	public PackageTreeNode getChildWithName(String name) {
		if(isLeaf())
			return null;
		for(PackageTreeNode child: children) {
			if(child.getName().equals(name))
				return child;
		}
		return null;
	}
	
	public String getQualifiedName() {
		String qualifiedName = this.name;
		PackageTreeNode current = this.parent;
		while(current != null && current.getName().length()>0) {
			qualifiedName = current.getName()+"."+qualifiedName;
			current = current.parent;
		}
		return qualifiedName;
	}
}

package com.geewaza.study.commons.trietree;

/**
 * Created by WangHeng on 2016/2/4.
 */
public class SimpleTreeWriter<T extends TreeNodeData> extends TreeWriter<T> {


	public SimpleTreeWriter(Tree<T> tree) {
		super(tree);
	}

	@Override
	public void insert(String key, T data) {
		if (tree.isIgnorCase()) {
			key = key.toLowerCase();
		}
		if (tree.isKeepInnerSpace()) {
			key = key.replaceAll("\\s", "");
		}
		String[] indexArray = key.split("");
		TreeNode<T> root = tree.getRoot();
		TreeNode<T> currentNode = root;
		for (String c : indexArray) {
			TreeNode<T> childNode = currentNode.getChild(c);
			if (childNode != null) {
				T childData = childNode.getData();
				childData.merge(data);
				currentNode = childNode;
				continue;
			}
			childNode = new TreeNode<T>(c, (T) data.clone());
			currentNode.addChild(c, childNode);
			currentNode = childNode;
		}
	}

}

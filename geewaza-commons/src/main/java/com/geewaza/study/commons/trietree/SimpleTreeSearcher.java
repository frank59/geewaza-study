package com.geewaza.study.commons.trietree;

/**
 * Created by WangHeng on 2016/2/4.
 */
public class SimpleTreeSearcher<T extends TreeNodeData> extends TreeSearcher<T> {
	public SimpleTreeSearcher(Tree<T> tree) {
		super(tree);
	}

	@Override
	public T search(String query) {
		if (tree.isIgnorCase()) {
			query = query.toLowerCase();
		}
		if (tree.isKeepInnerSpace()) {
			query = query.replaceAll("\\s", "");
		}
		String[] indexArray = query.split("");
		TreeNode<T> root = tree.getRoot();
		TreeNode<T> currentNode = root;
		for (String c : indexArray) {
			TreeNode<T> childNode = currentNode.getChild(c);
			if (childNode != null) {
				currentNode = childNode;
				continue;
			}
			break;
		}
		return currentNode.getData();
	}
}

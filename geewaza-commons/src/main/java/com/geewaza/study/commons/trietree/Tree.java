package com.geewaza.study.commons.trietree;

import java.io.Serializable;

public class Tree<T extends TreeNodeData> implements Serializable {
	private static final long serialVersionUID = -6668674377660183475L;
	
	private TreeNode<T> root;

	private boolean keepInnerSpace;
	private boolean ignorCase;
	
	public Tree() {
		this(new TreeNode<T>(null, null), false, true);
	}

	public Tree(boolean keepInnerSpace, boolean ignorCase) {
		this(new TreeNode<T>(null, null), keepInnerSpace, ignorCase);
	}

	public Tree(TreeNode<T> root, boolean keepInnerSpace, boolean ignorCase) {
		this.root = root;
		this.keepInnerSpace = keepInnerSpace;
		this.ignorCase = ignorCase;
	}
	
	/**
	 * 保存根节点
	 * @param newRoot
	 * @return 如果之前设置了root，则返回之前的root，否则返回null
	 */
	public TreeNode<T> setRoot(TreeNode<T> newRoot) {
		TreeNode<T> oldRoot = root;
		root = newRoot;
		if (oldRoot != null) {
			return oldRoot;
		} else {
			return null;
		}
	}
	
	public TreeNode<T> getRoot() {
		return root;
	}

	public boolean isKeepInnerSpace() {
		return keepInnerSpace;
	}

	public void setKeepInnerSpace(boolean keepInnerSpace) {
		this.keepInnerSpace = keepInnerSpace;
	}

	public boolean isIgnorCase() {
		return ignorCase;
	}

	public void setIgnorCase(boolean ignorCase) {
		this.ignorCase = ignorCase;
	}
}

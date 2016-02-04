package com.geewaza.study.commons.trietree;

/**
 * Created by WangHeng on 2016/2/4.
 */
public abstract class TreeWriter<T extends TreeNodeData> {

	protected Tree<T> tree;
	public TreeWriter(Tree<T> tree) {
		this.tree = tree;
	}

	public abstract void insert(String key, T data);
}

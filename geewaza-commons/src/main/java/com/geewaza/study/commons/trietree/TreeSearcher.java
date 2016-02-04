package com.geewaza.study.commons.trietree;

/**
 * Created by WangHeng on 2016/2/4.
 */
public abstract class TreeSearcher<T extends TreeNodeData> {

	protected Tree<T> tree;

	public TreeSearcher(Tree<T> tree) {
		this.tree = tree;
	}

	public abstract T search(String query);
}

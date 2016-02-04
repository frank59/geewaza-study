package com.geewaza.study.commons.trietree;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TreeNode<T extends TreeNodeData> implements Serializable {
	private static final long serialVersionUID = 903385568620918398L;
	
	private String c;
	private Map<String, TreeNode<T>> childMap = new HashMap<String, TreeNode<T>>();
	private TreeNode<T> father;
	private T data;

	public TreeNode(String c, T data) {
		this.c = c;
		this.data = data;
	}
	
	public String getC() {
		return c;
	}
	public TreeNode<T> getFather() {
		return father;
	}
	public void setFather(TreeNode<T> father) {
		this.father = father;
	}
	public Map<String,TreeNode<T>> getChildMap() {
		return childMap;
	}
	public void setData(T data) {
		this.data = data;
	}
	public T getData() {
		return data;
	}
	
	/**
	 * 为该节点添加一个子节点
	 * @param childC
	 * @param childNode
	 * @return 如果之前设置了childC对应的节点，则返回之前对应的节点，否则返回null
	 */
	public TreeNode<T> addChild(String childC, TreeNode<T> childNode) {
		TreeNode<T> oldChildNode = childMap.get(childC);
		childMap.put(childC, childNode);
		childNode.setFather(this);
		if (oldChildNode != null) {
			return oldChildNode;
		} else {
			return null;
		}
	}
	public TreeNode<T> getChild(String childC) {
		return childMap.get(childC);
	}
	
}

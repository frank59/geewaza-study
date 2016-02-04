package com.geewaza.study.commons.trietree;

import java.io.Serializable;

public interface TreeNodeData extends Serializable, Cloneable{

	public TreeNodeData merge(TreeNodeData data);

	public TreeNodeData clone();

}

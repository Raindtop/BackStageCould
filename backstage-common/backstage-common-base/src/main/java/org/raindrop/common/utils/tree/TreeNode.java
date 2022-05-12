package org.raindrop.common.utils.tree;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author raindrop
 * @Date 2022/5/12
 **/
@Data
@ApiModel(value = "树形节点")
public class TreeNode {

	@ApiModelProperty(value = "当前节点id")
	protected Long id;

	@ApiModelProperty(value = "父节点id")
	protected Long parentId;

	@ApiModelProperty(value = "子节点列表")
	protected List<TreeNode> children = new ArrayList<TreeNode>();

	public void add(TreeNode node) {
		children.add(node);
	}

}

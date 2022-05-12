
package org.raindrop.upms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.raindrop.common.utils.tree.TreeNode;

import java.io.Serializable;

/**
 * @Author raindrop
 * @Date 2022/5/12
 **/
@Data
@ApiModel(value = "菜单树")
@EqualsAndHashCode(callSuper = true)
public class ResourceTreeVo extends TreeNode implements Serializable {

	/**
	 * 菜单图标
	 */
	@ApiModelProperty(value = "菜单图标")
	private String icon;

	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value = "菜单名称")
	private String name;

	private boolean spread = false;

	/**
	 * 前端路由标识路径
	 */
	@ApiModelProperty(value = "前端路由标识路径")
	private String path;

	/**
	 * 权限编码
	 */
	@ApiModelProperty(value = "权限编码")
	private String permission;

	@ApiModelProperty("资源类型 0-未知 10-菜单 20-按钮")
	private Integer type;
	/**
	 * 菜单标签
	 */
	@ApiModelProperty(value = "菜单标签")
	private String label;

	/**
	 * 排序值
	 */
	@ApiModelProperty(value = "排序值")
	private Integer sort;

	/**
	 * 是否包含子节点
	 *
	 * @since 3.7
	 */
	private Boolean hasChildren;

	public ResourceTreeVo() {
	}

	public ResourceTreeVo(long id, String name, long parentId) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.label = name;
	}

	public ResourceTreeVo(long id, String name, ResourceTreeVo parent) {
		this.id = id;
		this.parentId = parent.getId();
		this.name = name;
		this.label = name;
	}

	public ResourceTreeVo(ResourceVo menuVo) {
		this.id = menuVo.getResourceId();
		this.parentId = menuVo.getParentId();
		this.icon = menuVo.getIcon();
		this.name = menuVo.getName();
		this.path = menuVo.getPath();
		this.type = menuVo.getType();
		this.permission = menuVo.getPermission();
		this.label = menuVo.getName();
		this.sort = menuVo.getSort();
	}

}

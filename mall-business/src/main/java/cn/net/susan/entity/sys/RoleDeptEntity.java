package cn.net.susan.entity.sys;

import cn.net.susan.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色部门关联实体 该项目是知识星球：java突击队 的内部项目
 * 
 * @author 苏三
 * @date 2024-01-08 17:18:18
 */
@ApiModel("角色部门关联实体")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDeptEntity extends BaseEntity {
	

	/**
	 * 
	 */
	@ApiModelProperty("")
	private Long roleId;

	/**
	 * 
	 */
	@ApiModelProperty("")
	private Long deptId;
}

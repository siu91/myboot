package org.siu.myboot.server.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.siu.myboot.core.entity.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.siu.myboot.core.valid.Valid;
import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;


/**
 * 角色权限表
 *
 * @author @Author Siu
 * @Date 2020-03-05 14:25:02
 * @Version 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "role_authority", schema = "ganxu")
@ApiModel(value = "角色权限表")
public class RoleAuthority extends BaseEntity implements Serializable {

	/**
	 * 角色权限表主键
	 * nullable : false
	 * default  : nextval('ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu_common_seq", sequenceName = "ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu_common_seq")
	@ApiModelProperty(value = "角色权限表主键")
	@Column(name = "id", nullable = false)
	@NotNull(message = "角色权限表主键不能为空", groups = {Valid.UPDATE.class})
	private Long id;

	/**
	 * 角色ID
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "角色ID")
	@Column(name = "role_id", nullable = false)
	@NotNull(message = "角色ID不能为空", groups = {Valid.CREATE.class})
	private Long roleId;

	/**
	 * 权限标识
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "权限标识")
	@Column(name = "perm", nullable = true, length = 255)
	private String perm;

	/**
	 * 创建时间
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "创建时间")
	@CreatedDate
	@Column(name = "create_time", nullable = true)
	private java.util.Date createTime;

	/**
	 * 更新时间
	 * nullable : true
	 * default  : CURRENT_TIMESTAMP
	 */
	@ApiModelProperty(value = "更新时间")
	@LastModifiedDate
	@Column(name = "update_time", nullable = true)
	private java.util.Date updateTime;
}

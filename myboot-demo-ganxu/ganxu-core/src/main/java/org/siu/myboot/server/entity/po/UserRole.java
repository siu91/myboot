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
 * 用户权限表
 *
 * @author @Author Siu
 * @Date 2020-03-05 14:32:42
 * @Version 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_role", schema = "ganxu")
@ApiModel(value = "用户权限表")
public class UserRole extends BaseEntity implements Serializable {

	/**
	 * 用户信息表主键
	 * nullable : false
	 * default  : nextval('ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu_common_seq", sequenceName = "ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu_common_seq")
	@ApiModelProperty(value = "用户信息表主键")
	@Column(name = "id", nullable = false)
	@NotNull(message = "用户信息表主键不能为空", groups = {Valid.UPDATE.class})
	private Long id;

	/**
	 * 用户表主键
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "用户表主键")
	@Column(name = "user_id", nullable = false)
	@NotNull(message = "用户表主键不能为空", groups = {Valid.CREATE.class})
	private Long userId;

	/**
	 * 角色表主键ID
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "角色表主键ID")
	@Column(name = "role_id", nullable = true)
	private Long roleId;

	/**
	 * 角色标识
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "角色标识")
	@Column(name = "role", nullable = true, length = 60)
	private String role;

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

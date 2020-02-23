package org.siu.myboot.server.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.siu.myboot.core.entity.BaseEntity;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;

import java.io.Serializable;


/**
 * UserInfo
 *
 * @author @Author Siu
 * @Date 2020-02-23 15:13:42
 * @Version 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_info")
@ApiModel(value = "UserInfo")
public class UserInfo extends BaseEntity implements Serializable {

	/**
	 * 用户ID（主键）
	 * nullable : false
	 * default  : nextval('public_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "public_seq", sequenceName = "public_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public_seq")
	@ApiModelProperty(value = "用户ID（主键）")
	@Column(name = "user_id", nullable = true)
	private Long userId;

	/**
	 * 用户名
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "用户名")
	@Column(name = "user_name", nullable = true, length = 64)
	private String userName;

	/**
	 * 头像URL
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "头像URL")
	@Column(name = "avatar_url", nullable = true, length = 255)
	private String avatarUrl;

	/**
	 * 手机
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "手机")
	@Column(name = "phone", nullable = true, length = 64)
	private String phone;

	/**
	 * 密码
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "密码")
	@Column(name = "password", nullable = true, length = 64)
	private String password;

	/**
	 * 更新时间
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "更新时间")
	@Column(name = "update_time", nullable = true)
	private java.util.Date updateTime;
}

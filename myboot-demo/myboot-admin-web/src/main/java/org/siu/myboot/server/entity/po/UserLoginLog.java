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
 * 用户登录日志表
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_login_log")
@ApiModel(value = "用户登录日志表")
public class UserLoginLog extends BaseEntity implements Serializable {

	/**
	 * 用户登录日志表ID
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "用户登录日志表ID")
	@Column(name = "id", nullable = false)
	@NotNull(message = "用户登录日志表ID不能为空", groups = {Valid.UPDATE.class})
	private Long id;

	/**
	 * 用户ID
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "用户ID")
	@Column(name = "user_id", nullable = false)
	@NotNull(message = "用户ID不能为空", groups = {Valid.CREATE.class})
	private Long userId;

	/**
	 * 登录时间
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "登录时间")
	@Column(name = "login_time", nullable = false)
	@NotNull(message = "登录时间不能为空", groups = {Valid.CREATE.class})
	private java.util.Date loginTime;

	/**
	 * 登录IP
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "登录IP")
	@Column(name = "client_ip", nullable = true, length = 255)
	private String clientIp;

	/**
	 * 用户代理
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "用户代理")
	@Column(name = "user_agent", nullable = true, length = 512)
	private String userAgent;

	/**
	 * 登录状态：0-成功
	 * nullable : true
	 * default  : 0
	 */
	@ApiModelProperty(value = "登录状态：0-成功")
	@Column(name = "login_status", nullable = true)
	private Integer loginStatus;
}

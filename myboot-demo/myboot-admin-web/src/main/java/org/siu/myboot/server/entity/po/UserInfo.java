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
 * UserInfo
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:42:19
 * @Version 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_info")
@ApiModel(value = "UserInfo")
public class UserInfo extends BaseEntity implements Serializable {

	/**
	 * 用户信息表主键
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
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
	 * 真是姓名
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "真是姓名")
	@Column(name = "real_name", nullable = true, length = 255)
	private String realName;

	/**
	 * 证件类型
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "证件类型")
	@Column(name = "id_type", nullable = true)
	private Integer idType;

	/**
	 * 证件号
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "证件号")
	@Column(name = "id_no", nullable = true, length = 255)
	private String idNo;

	/**
	 * 邮箱
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "邮箱")
	@Column(name = "email", nullable = true, length = 255)
	private String email;

	/**
	 * 性别
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "性别")
	@Column(name = "gender", nullable = true)
	private Integer gender;

	/**
	 * 积分
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "积分")
	@Column(name = "points", nullable = true)
	private Long points;

	/**
	 * 注册时间
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "注册时间")
	@Column(name = "register_time", nullable = true)
	private java.util.Date registerTime;

	/**
	 * 生日
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "生日")
	@Column(name = "birthday", nullable = true)
	private java.sql.Date birthday;

	/**
	 * 会员级别：1 普通会员，2 青铜，3白银，4黄金，5钻石
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "会员级别：1 普通会员，2 青铜，3白银，4黄金，5钻石")
	@Column(name = "user_level", nullable = true)
	private Integer userLevel;

	/**
	 * 用户类型
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "用户类型")
	@Column(name = "user_type", nullable = true)
	private Integer userType;

	/**
	 * 账户余额
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "账户余额")
	@Column(name = "account_amount", nullable = true)
	private Long accountAmount;

	/**
	 * 版本（更新锁）
	 * nullable : true
	 * default  : 0
	 */
	@ApiModelProperty(value = "版本（更新锁）")
	@Column(name = "version", nullable = true)
	private Long version;

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

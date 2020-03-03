package org.siu.myboot.server.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.siu.myboot.core.entity.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.siu.myboot.core.valid.Valid;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;


/**
 * 用户表
 *
 * @author @Author Siu
 * @Date 2020-03-01 17:30:04
 * @Version 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user", schema = "ganxu")
@ApiModel(value = "用户表")
@SQLDelete(sql = "UPDATE user SET delete_status = 1 WHERE id = ?")
@Where(clause = "delete_status = 0")
public class User extends BaseEntity implements Serializable {

	/**
	 * 用户ID（主键）
	 * nullable : false
	 * default  : nextval('ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu_common_seq", sequenceName = "ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu_common_seq")
	@ApiModelProperty(value = "用户ID（主键）")
	@Column(name = "id", nullable = false)
	@NotNull(message = "用户ID（主键）不能为空", groups = {Valid.UPDATE.class})
	private Long id;

	/**
	 * 用户名
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "用户名")
	@Column(name = "user_name", nullable = false, length = 64)
	@NotNull(message = "用户名不能为空", groups = {Valid.CREATE.class})
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
	@Column(name = "phone", nullable = false, length = 64)
	@NotNull(message = "手机不能为空", groups = {Valid.CREATE.class})
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
	 * 版本（更新锁）
	 * nullable : true
	 * default  : 0
	 */
	@ApiModelProperty(value = "版本（更新锁）")
	@Column(name = "version", nullable = true)
	private Long version;

	/**
	 * 删除状态：0-未删除，其它删除
	 * nullable : true
	 * default  : 0
	 */
	@ApiModelProperty(value = "删除状态：0-未删除，其它删除")
	@Column(name = "delete_status", nullable = true)
	private Integer deleteStatus;

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

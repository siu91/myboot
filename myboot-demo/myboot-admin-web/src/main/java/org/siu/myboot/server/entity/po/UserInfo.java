package org.siu.myboot.server.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;


/**
 * 用户信息表
 *
 * @author @Author Siu
 * @Date 2020-02-24 23:46:44
 * @Version 0.0.1
 */
@Data
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_info")
@ApiModel(value = "用户信息表")
@SQLDelete(sql = "UPDATE user_info SET soft_delete = nextval( 'public_soft_delete_seq' ) WHERE user_id = ?")
@Where(clause = "soft_delete = 0")
public class UserInfo implements Serializable {

	/**
	 * 用户ID（主键）
	 * nullable : false
	 * default  : nextval('public_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "public_seq", sequenceName = "public_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public_seq")
	@ApiModelProperty(value = "用户ID（主键）")
	@Column(name = "user_id", nullable = false)
	private Long userId;

	/**
	 * 用户名（唯一）
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "用户名（唯一）")
	@Column(name = "user_name", nullable = false, length = 64)
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
	 * 手机（唯一）
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "手机（唯一）")
	@Column(name = "phone", nullable = false, length = 64)
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
	 * default  : null
	 */
	@ApiModelProperty(value = "更新时间")
	@LastModifiedDate
	@Column(name = "update_time", nullable = true)
	private java.util.Date updateTime;

	/**
	 * 软删除：0-未删除，其他-删除
	 * nullable : true
	 * default  : 0
	 */
	@ApiModelProperty(value = "软删除：0-未删除，其他-删除")
	@Column(name = "soft_delete", nullable = true)
	private Long softDelete;
}

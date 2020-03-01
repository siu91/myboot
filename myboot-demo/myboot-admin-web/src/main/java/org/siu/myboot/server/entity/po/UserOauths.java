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
 * 用户鉴权表
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
@Table(name = "user_oauths", schema = "ganxu")
@ApiModel(value = "用户鉴权表")
@SQLDelete(sql = "UPDATE user_oauths SET delete_status = 1 WHERE id = ?")
@Where(clause = "delete_status = 0")
public class UserOauths extends BaseEntity implements Serializable {

	/**
	 * id
	 * nullable : false
	 * default  : nextval('ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu_common_seq", sequenceName = "ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu_common_seq")
	@ApiModelProperty(value = "id")
	@Column(name = "id", nullable = false)
	@NotNull(message = "id不能为空", groups = {Valid.UPDATE.class})
	private Long id;

	/**
	 * users表主键
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "users表主键")
	@Column(name = "user_id", nullable = false)
	@NotNull(message = "users表主键不能为空", groups = {Valid.CREATE.class})
	private Long userId;

	/**
	 * 1、微信，2、QQ，3、支付宝，4、其他
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "1、微信，2、QQ，3、支付宝，4、其他")
	@Column(name = "oauth_type", nullable = false)
	@NotNull(message = "1、微信，2、QQ，3、支付宝，4、其他不能为空", groups = {Valid.CREATE.class})
	private Integer oauthType;

	/**
	 * 第三方 uid 、openid 等
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "第三方 uid 、openid 等")
	@Column(name = "oauth_id", nullable = false, length = 255)
	@NotNull(message = "第三方 uid 、openid 等不能为空", groups = {Valid.CREATE.class})
	private String oauthId;

	/**
	 * QQ / 微信同一主体下 Unionid 相同
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "QQ / 微信同一主体下 Unionid 相同")
	@Column(name = "unionid", nullable = false, length = 255)
	@NotNull(message = "QQ / 微信同一主体下 Unionid 相同不能为空", groups = {Valid.CREATE.class})
	private String unionid;

	/**
	 * 密码凭证 /access_token (目前更多是存储在缓存里)
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "密码凭证 /access_token (目前更多是存储在缓存里)")
	@Column(name = "credential", nullable = true, length = 255)
	private String credential;

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
}

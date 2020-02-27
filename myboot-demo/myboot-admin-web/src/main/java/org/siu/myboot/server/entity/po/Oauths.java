package org.siu.myboot.server.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.siu.myboot.core.entity.BaseEntity;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;


/**
 * 第三方授权表
 *
 * @author @Author Siu
 * @Date 2020-02-27 20:33:51
 * @Version 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "oauths")
@ApiModel(value = "第三方授权表")
public class Oauths extends BaseEntity implements Serializable {

	/**
	 * (qp)
	 * nullable : false
	 * default  : nextval('public_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "public_seq", sequenceName = "public_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public_seq")
	@ApiModelProperty(value = "(qp)")
	@Column(name = "id", nullable = false)
	private Long id;

	/**
	 * user_info表主键(qp)
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "user_info表主键(qp)")
	@Column(name = "user_id", nullable = false)
	private Long userId;

	/**
	 * 1、微信，2、QQ，3、支付宝，4、其他
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "1、微信，2、QQ，3、支付宝，4、其他", hidden = true)
	@Column(name = "oauth_type", nullable = false)
	private Integer oauthType;

	/**
	 * 第三方 uid 、openid 等
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "第三方 uid 、openid 等", hidden = true)
	@Column(name = "oauth_id", nullable = false, length = 255)
	private String oauthId;

	/**
	 * QQ / 微信同一主体下 Unionid 相同
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "QQ / 微信同一主体下 Unionid 相同", hidden = true)
	@Column(name = "unionid", nullable = false, length = 255)
	private String unionid;

	/**
	 * 密码凭证 /access_token (目前更多是存储在缓存里)
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "密码凭证 /access_token (目前更多是存储在缓存里)", hidden = true)
	@Column(name = "credential", nullable = true, length = 255)
	private String credential;

	/**
	 * (qp)
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "(qp)")
	@CreatedDate
	@Column(name = "create_time", nullable = true)
	private java.util.Date createTime;

	/**
	 * (qp)
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "(qp)")
	@LastModifiedDate
	@Column(name = "update_time", nullable = true)
	private java.util.Date updateTime;
}

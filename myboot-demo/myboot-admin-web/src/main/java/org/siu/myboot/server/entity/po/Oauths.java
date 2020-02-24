package org.siu.myboot.server.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
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
 * @Date 2020-02-24 23:46:44
 * @Version 0.0.1
 */
@Data
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "oauths")
@ApiModel(value = "第三方授权表")
public class Oauths implements Serializable {

	/**
	 * id
	 * nullable : false
	 * default  : nextval('public_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "public_seq", sequenceName = "public_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public_seq")
	@ApiModelProperty(value = "id")
	@Column(name = "id", nullable = false)
	private Long id;

	/**
	 * user_info表主键
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "user_info表主键")
	@Column(name = "user_id", nullable = false)
	private Long userId;

	/**
	 * 1、微信，2、QQ，3、支付宝，4、其他
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "1、微信，2、QQ，3、支付宝，4、其他")
	@Column(name = "oauth_type", nullable = false)
	private Integer oauthType;

	/**
	 * 第三方 uid 、openid 等
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "第三方 uid 、openid 等")
	@Column(name = "oauth_id", nullable = false, length = 255)
	private String oauthId;

	/**
	 * QQ / 微信同一主体下 Unionid 相同
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "QQ / 微信同一主体下 Unionid 相同")
	@Column(name = "unionid", nullable = false, length = 255)
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
	 * createTime
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "createTime")
	@CreatedDate
	@Column(name = "create_time", nullable = true)
	private java.util.Date createTime;

	/**
	 * updateTime
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "updateTime")
	@LastModifiedDate
	@Column(name = "update_time", nullable = true)
	private java.util.Date updateTime;
}

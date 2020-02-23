package org.siu.myboot.server.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.siu.myboot.core.entity.BaseEntity;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;

import java.io.Serializable;


/**
 * Oauths
 *
 * @author @Author Siu
 * @Date 2020-02-23 15:13:42
 * @Version 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "oauths")
@ApiModel(value = "Oauths")
public class Oauths extends BaseEntity implements Serializable {

	/**
	 * user_info表主键
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "user_info表主键")
	@Column(name = "user_id", nullable = true)
	private Long userId;

	/**
	 * 1、微信，2、QQ，3、支付宝，4、其他
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "1、微信，2、QQ，3、支付宝，4、其他")
	@Column(name = "oauth_type", nullable = true)
	private Integer oauthType;

	/**
	 * 第三方 uid 、openid 等
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "第三方 uid 、openid 等")
	@Column(name = "oauth_id", nullable = true, length = 255)
	private String oauthId;

	/**
	 * QQ / 微信同一主体下 Unionid 相同
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "QQ / 微信同一主体下 Unionid 相同")
	@Column(name = "unionid", nullable = true, length = 255)
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
	 * updateTime
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "updateTime")
	@Column(name = "update_time", nullable = true)
	private java.util.Date updateTime;
}

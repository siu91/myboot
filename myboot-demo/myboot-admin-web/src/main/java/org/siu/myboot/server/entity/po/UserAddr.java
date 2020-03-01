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
 * 用户地址表
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
@Table(name = "user_addr", schema = "ganxu")
@ApiModel(value = "用户地址表")
public class UserAddr extends BaseEntity implements Serializable {

	/**
	 * 用户地址表主键
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "用户地址表主键")
	@Column(name = "id", nullable = false)
	@NotNull(message = "用户地址表主键不能为空", groups = {Valid.UPDATE.class})
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
	 * 邮编
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "邮编")
	@Column(name = "zip", nullable = true, length = 255)
	private String zip;

	/**
	 * 省
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "省")
	@Column(name = "province", nullable = false, length = 64)
	@NotNull(message = "省不能为空", groups = {Valid.CREATE.class})
	private String province;

	/**
	 * 市
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "市")
	@Column(name = "city", nullable = false, length = 64)
	@NotNull(message = "市不能为空", groups = {Valid.CREATE.class})
	private String city;

	/**
	 * 区
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "区")
	@Column(name = "district", nullable = false, length = 64)
	@NotNull(message = "区不能为空", groups = {Valid.CREATE.class})
	private String district;

	/**
	 * 地址
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "地址")
	@Column(name = "address", nullable = false, length = 64)
	@NotNull(message = "地址不能为空", groups = {Valid.CREATE.class})
	private String address;

	/**
	 * 是否默认地址
	 * nullable : false
	 * default  : 1
	 */
	@ApiModelProperty(value = "是否默认地址")
	@Column(name = "default_addr ", nullable = false, length = 255)
	@NotNull(message = "是否默认地址不能为空", groups = {Valid.CREATE.class})
	private String defaultAddr ;

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
	@Column(name = "upate_time", nullable = true)
	private java.util.Date upateTime;
}

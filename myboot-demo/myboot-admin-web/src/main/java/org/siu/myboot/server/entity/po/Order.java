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
 * 订单主表
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:30:49
 * @Version 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order")
@ApiModel(value = "订单主表")
public class Order extends BaseEntity implements Serializable {

	/**
	 * 订单ID
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "订单ID")
	@Column(name = "id", nullable = false)
	@NotNull(message = "订单ID不能为空", groups = {Valid.UPDATE.class})
	private Long id;

	/**
	 * 订单编号
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "订单编号")
	@Column(name = "order_sn", nullable = false, length = 255)
	@NotNull(message = "订单编号不能为空", groups = {Valid.CREATE.class})
	private String orderSn;

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
	 * 收货人
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "收货人")
	@Column(name = "shipping_user", nullable = true, length = 255)
	private String shippingUser;

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
	@Column(name = "address", nullable = false, length = 512)
	@NotNull(message = "地址不能为空", groups = {Valid.CREATE.class})
	private String address;

	/**
	 * 支付方式：1现金，2余额，3网银，4支付宝，5微信
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "支付方式：1现金，2余额，3网银，4支付宝，5微信")
	@Column(name = "payment_method", nullable = true)
	private Integer paymentMethod;

	/**
	 * 订单金额
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "订单金额")
	@Column(name = "order_amount", nullable = true)
	private Long orderAmount;

	/**
	 * 优惠金额
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "优惠金额")
	@Column(name = "discount_amount", nullable = true)
	private Long discountAmount;

	/**
	 * 运费金额
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "运费金额")
	@Column(name = "shipping_amount", nullable = true)
	private Long shippingAmount;

	/**
	 * 支付金额
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "支付金额")
	@Column(name = "payment_amount", nullable = true)
	private Long paymentAmount;

	/**
	 * 快递公司名称
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "快递公司名称")
	@Column(name = "shipping_comp_name", nullable = true, length = 255)
	private String shippingCompName;

	/**
	 * 快递单号
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "快递单号")
	@Column(name = "shipping_sn", nullable = true, length = 255)
	private String shippingSn;

	/**
	 * 创建时间
	 * nullable : true
	 * default  : CURRENT_TIMESTAMP
	 */
	@ApiModelProperty(value = "创建时间")
	@CreatedDate
	@Column(name = "create_time", nullable = true)
	private java.util.Date createTime;

	/**
	 * 发货时间
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "发货时间")
	@Column(name = "shipping_time", nullable = true)
	private java.util.Date shippingTime;

	/**
	 * 支付时间
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "支付时间")
	@Column(name = "pay_time", nullable = true)
	private java.util.Date payTime;

	/**
	 * 收货时间
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "收货时间")
	@Column(name = "receive_time", nullable = true)
	private java.util.Date receiveTime;

	/**
	 * 订单状态
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "订单状态")
	@Column(name = "order_status", nullable = true)
	private Integer orderStatus;

	/**
	 * 订单积分
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "订单积分")
	@Column(name = "order_point", nullable = true)
	private Long orderPoint;

	/**
	 * 发票ID
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "发票ID")
	@Column(name = "invoice_id", nullable = true)
	private Long invoiceId;

	/**
	 * 更新时间
	 * nullable : true
	 * default  : CURRENT_TIMESTAMP
	 */
	@ApiModelProperty(value = "更新时间")
	@Column(name = "upate_time", nullable = true)
	private java.util.Date upateTime;
}

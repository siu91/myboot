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
 * 订单详情表
 *
 * @author @Author Siu
 * @Date 2020-03-01 17:30:03
 * @Version 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order_detail", schema = "ganxu")
@ApiModel(value = "订单详情表")
public class OrderDetail extends BaseEntity implements Serializable {

	/**
	 * 订单详情表ID
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "订单详情表ID")
	@Column(name = "id", nullable = false)
	@NotNull(message = "订单详情表ID不能为空", groups = {Valid.UPDATE.class})
	private Long id;

	/**
	 * 订单主表ID
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "订单主表ID")
	@Column(name = "order_id", nullable = false)
	@NotNull(message = "订单主表ID不能为空", groups = {Valid.CREATE.class})
	private Long orderId;

	/**
	 * 商品ID
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "商品ID")
	@Column(name = "product_id", nullable = false)
	@NotNull(message = "商品ID不能为空", groups = {Valid.CREATE.class})
	private Long productId;

	/**
	 * 商品名称
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "商品名称")
	@Column(name = "product_name", nullable = false, length = 255)
	@NotNull(message = "商品名称不能为空", groups = {Valid.CREATE.class})
	private String productName;

	/**
	 * 购买商品数量
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "购买商品数量")
	@Column(name = "product_cnt", nullable = false)
	@NotNull(message = "购买商品数量不能为空", groups = {Valid.CREATE.class})
	private Integer productCnt;

	/**
	 * 购买商品单价
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "购买商品单价")
	@Column(name = "product_price", nullable = false)
	@NotNull(message = "购买商品单价不能为空", groups = {Valid.CREATE.class})
	private Long productPrice;

	/**
	 * 平均成本价格
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "平均成本价格")
	@Column(name = "average_cost", nullable = true)
	private Long averageCost;

	/**
	 * 商品重量（克）
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "商品重量（克）")
	@Column(name = "weight", nullable = true)
	private Double weight;

	/**
	 * 优惠金额（分摊）
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "优惠金额（分摊）")
	@Column(name = "discount_amount", nullable = true)
	private Long discountAmount;

	/**
	 * 仓库ID
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "仓库ID")
	@Column(name = "w_id", nullable = true)
	private Long wId;

	/**
	 * 更新时间
	 * nullable : true
	 * default  : CURRENT_TIMESTAMP
	 */
	@ApiModelProperty(value = "更新时间")
	@Column(name = "upate_time", nullable = true)
	private java.util.Date upateTime;
}

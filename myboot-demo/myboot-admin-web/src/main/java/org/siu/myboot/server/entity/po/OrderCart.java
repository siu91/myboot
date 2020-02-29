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
 * 购物车
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:27:03
 * @Version 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order_cart")
@ApiModel(value = "购物车")
public class OrderCart extends BaseEntity implements Serializable {

	/**
	 * 购物车ID
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "购物车ID")
	@Column(name = "id", nullable = false)
	@NotNull(message = "购物车ID不能为空", groups = {Valid.UPDATE.class})
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
	 * 商品ID
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "商品ID")
	@Column(name = "product_id", nullable = false)
	@NotNull(message = "商品ID不能为空", groups = {Valid.CREATE.class})
	private Long productId;

	/**
	 * 加入购物车商品数量
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "加入购物车商品数量")
	@Column(name = "product_amount", nullable = false)
	@NotNull(message = "加入购物车商品数量不能为空", groups = {Valid.CREATE.class})
	private Integer productAmount;

	/**
	 * 商品价格
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "商品价格")
	@Column(name = "price", nullable = false)
	@NotNull(message = "商品价格不能为空", groups = {Valid.CREATE.class})
	private Long price;

	/**
	 * 创建时间
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "创建时间")
	@CreatedDate
	@Column(name = "create_time", nullable = false)
	@NotNull(message = "创建时间不能为空", groups = {Valid.CREATE.class})
	private java.util.Date createTime;

	/**
	 * 更新时间
	 * nullable : false
	 * default  : CURRENT_TIMESTAMP
	 */
	@ApiModelProperty(value = "更新时间")
	@Column(name = "upate_time", nullable = false)
	@NotNull(message = "更新时间不能为空", groups = {Valid.CREATE.class})
	private java.util.Date upateTime;
}

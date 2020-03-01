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
 * 商品库存表
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
@Table(name = "product_stock", schema = "ganxu")
@ApiModel(value = "商品库存表")
public class ProductStock extends BaseEntity implements Serializable {

	/**
	 * 商品库存表ID
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "商品库存表ID")
	@Column(name = "id", nullable = false)
	@NotNull(message = "商品库存表ID不能为空", groups = {Valid.UPDATE.class})
	private Long id;

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
	 * 仓库ID
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "仓库ID")
	@Column(name = "w_id", nullable = true)
	private Long wId;

	/**
	 * 当前商品数量
	 * nullable : false
	 * default  : 0
	 */
	@ApiModelProperty(value = "当前商品数量")
	@Column(name = "current_cnt", nullable = false)
	@NotNull(message = "当前商品数量不能为空", groups = {Valid.CREATE.class})
	private Integer currentCnt;

	/**
	 * 当前占用数据
	 * nullable : false
	 * default  : 0
	 */
	@ApiModelProperty(value = "当前占用数据")
	@Column(name = "lock_cnt", nullable = false)
	@NotNull(message = "当前占用数据不能为空", groups = {Valid.CREATE.class})
	private Integer lockCnt;

	/**
	 * 在途数据
	 * nullable : false
	 * default  : 0
	 */
	@ApiModelProperty(value = "在途数据")
	@Column(name = "in_transit_cnt", nullable = false)
	@NotNull(message = "在途数据不能为空", groups = {Valid.CREATE.class})
	private Integer inTransitCnt;

	/**
	 * 移动加权成本
	 * nullable : true
	 * default  : 0
	 */
	@ApiModelProperty(value = "移动加权成本")
	@Column(name = "average_cost", nullable = true)
	private Long averageCost;

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

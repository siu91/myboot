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
 * 商品信息表
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
@Table(name = "product", schema = "ganxu")
@ApiModel(value = "商品信息表")
public class Product extends BaseEntity implements Serializable {

	/**
	 * 商品ＩＤ
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "商品ＩＤ")
	@Column(name = "id", nullable = false)
	@NotNull(message = "商品ＩＤ不能为空", groups = {Valid.UPDATE.class})
	private Long id;

	/**
	 * 商品编码
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "商品编码")
	@Column(name = "product_code", nullable = true)
	private Long productCode;

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
	 * 国条码
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "国条码")
	@Column(name = "bar_code", nullable = true, length = 255)
	private String barCode;

	/**
	 * 品牌表的ID
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "品牌表的ID")
	@Column(name = "brand_id", nullable = true)
	private Long brandId;

	/**
	 * 一级分类ID
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "一级分类ID")
	@Column(name = "category1_id", nullable = true)
	private Long category1Id;

	/**
	 * 二级分类ID
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "二级分类ID")
	@Column(name = "category2_id", nullable = true)
	private Long category2Id;

	/**
	 * 三级分类ID
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "三级分类ID")
	@Column(name = "category3_id", nullable = true)
	private Long category3Id;

	/**
	 * 商品的供应商ID
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "商品的供应商ID")
	@Column(name = "supplier_id", nullable = true)
	private Long supplierId;

	/**
	 * 商品销售价格
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "商品销售价格")
	@Column(name = "price", nullable = false)
	@NotNull(message = "商品销售价格不能为空", groups = {Valid.CREATE.class})
	private Long price;

	/**
	 * 原价
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "原价")
	@Column(name = "origin_price", nullable = false)
	@NotNull(message = "原价不能为空", groups = {Valid.CREATE.class})
	private Long originPrice;

	/**
	 * 商品加权平均成本
	 * nullable : true
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@ApiModelProperty(value = "商品加权平均成本")
	@Column(name = "average_cost", nullable = true)
	private Long averageCost;

	/**
	 * 上下架状态：0下架1上架
	 * nullable : false
	 * default  : 0
	 */
	@ApiModelProperty(value = "上下架状态：0下架1上架")
	@Column(name = "publish_status", nullable = false)
	@NotNull(message = "上下架状态：0下架1上架不能为空", groups = {Valid.CREATE.class})
	private Integer publishStatus;

	/**
	 * 审核状态：0未审核，1已审核
	 * nullable : false
	 * default  : 0
	 */
	@ApiModelProperty(value = "审核状态：0未审核，1已审核")
	@Column(name = "audit_status", nullable = false)
	@NotNull(message = "审核状态：0未审核，1已审核不能为空", groups = {Valid.CREATE.class})
	private Integer auditStatus;

	/**
	 * 商品重量（克）
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "商品重量（克）")
	@Column(name = "weight", nullable = true)
	private Double weight;

	/**
	 * 商品长度（毫米）
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "商品长度（毫米）")
	@Column(name = "length", nullable = true)
	private Double length;

	/**
	 * 商品高度（毫米）
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "商品高度（毫米）")
	@Column(name = "height", nullable = true)
	private Double height;

	/**
	 * 商品宽度（毫米）
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "商品宽度（毫米）")
	@Column(name = "width", nullable = true)
	private Double width;

	/**
	 * 商品单位：盒/包/台/袋
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "商品单位：盒/包/台/袋")
	@Column(name = "unit", nullable = true)
	private Integer unit;

	/**
	 * 颜色
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "颜色")
	@Column(name = "color", nullable = true)
	private Integer color;

	/**
	 * 生产日期
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "生产日期")
	@Column(name = "production_date", nullable = false)
	@NotNull(message = "生产日期不能为空", groups = {Valid.CREATE.class})
	private java.sql.Date productionDate;

	/**
	 * 有效期
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "有效期")
	@Column(name = "expiration_date", nullable = false)
	@NotNull(message = "有效期不能为空", groups = {Valid.CREATE.class})
	private java.sql.Date expirationDate;

	/**
	 * 商品描述
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "商品描述")
	@Column(name = "desc", nullable = true, length = 255)
	private String desc;

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
	 * nullable : true
	 * default  : CURRENT_TIMESTAMP
	 */
	@ApiModelProperty(value = "更新时间")
	@Column(name = "upate_time", nullable = true)
	private java.util.Date upateTime;
}

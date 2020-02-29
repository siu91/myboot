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
 * 商品品牌信息表
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:43:44
 * @Version 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product_brand")
@ApiModel(value = "商品品牌信息表")
public class ProductBrand extends BaseEntity implements Serializable {

	/**
	 * 商品品牌信息表ID
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "商品品牌信息表ID")
	@Column(name = "id", nullable = false)
	@NotNull(message = "商品品牌信息表ID不能为空", groups = {Valid.UPDATE.class})
	private Long id;

	/**
	 * 品牌名称
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "品牌名称")
	@Column(name = "brand_name", nullable = false, length = 255)
	@NotNull(message = "品牌名称不能为空", groups = {Valid.CREATE.class})
	private String brandName;

	/**
	 * 联系电话
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "联系电话")
	@Column(name = "telephone", nullable = true, length = 50)
	private String telephone;

	/**
	 * 品牌网站
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "品牌网站")
	@Column(name = "brand_website", nullable = true, length = 255)
	private String brandWebsite;

	/**
	 * 品牌logo
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "品牌logo")
	@Column(name = "brand_logo", nullable = true, length = 255)
	private String brandLogo;

	/**
	 * 品牌描述
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "品牌描述")
	@Column(name = "brand_desc", nullable = true, length = 255)
	private String brandDesc;

	/**
	 * 品牌状态,0禁用,1启用
	 * nullable : true
	 * default  : 0
	 */
	@ApiModelProperty(value = "品牌状态,0禁用,1启用")
	@Column(name = "brand_status", nullable = true)
	private Integer brandStatus;

	/**
	 * 排序
	 * nullable : true
	 * default  : 0
	 */
	@ApiModelProperty(value = "排序")
	@Column(name = "brand_order", nullable = true)
	private Integer brandOrder;

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

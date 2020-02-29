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
 * 商品分类信息表
 *
 * @author @Author Siu
 * @Date 2020-02-29 23:47:03
 * @Version 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product_category")
@ApiModel(value = "商品分类信息表")
public class ProductCategory extends BaseEntity implements Serializable {

	/**
	 * 商品分类ID
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "商品分类ID")
	@Column(name = "id", nullable = false)
	@NotNull(message = "商品分类ID不能为空", groups = {Valid.UPDATE.class})
	private Long id;

	/**
	 * 分类名称
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "分类名称")
	@Column(name = "category_name", nullable = false, length = 255)
	@NotNull(message = "分类名称不能为空", groups = {Valid.CREATE.class})
	private String categoryName;

	/**
	 * 分类编码
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "分类编码")
	@Column(name = "category_code", nullable = false, length = 255)
	@NotNull(message = "分类编码不能为空", groups = {Valid.CREATE.class})
	private String categoryCode;

	/**
	 * 父分类ID
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "父分类ID")
	@Column(name = "parent_id", nullable = true)
	private Integer parentId;

	/**
	 * 分类层级
	 * nullable : false
	 * default  : 0
	 */
	@ApiModelProperty(value = "分类层级")
	@Column(name = "category_level", nullable = false)
	@NotNull(message = "分类层级不能为空", groups = {Valid.CREATE.class})
	private Integer categoryLevel;

	/**
	 * 分类状态
	 * nullable : false
	 * default  : 0
	 */
	@ApiModelProperty(value = "分类状态")
	@Column(name = "category_status", nullable = false)
	@NotNull(message = "分类状态不能为空", groups = {Valid.CREATE.class})
	private Integer categoryStatus;

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

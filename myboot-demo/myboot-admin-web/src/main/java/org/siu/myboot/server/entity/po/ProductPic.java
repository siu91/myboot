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
 * 商品图片表
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
@Table(name = "product_pic")
@ApiModel(value = "商品图片表")
public class ProductPic extends BaseEntity implements Serializable {

	/**
	 * 商品图片表ID
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "商品图片表ID")
	@Column(name = "id", nullable = false)
	@NotNull(message = "商品图片表ID不能为空", groups = {Valid.UPDATE.class})
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
	 * 图片描述
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "图片描述")
	@Column(name = "pic_desc", nullable = true, length = 255)
	private String picDesc;

	/**
	 * 图片URL
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "图片URL")
	@Column(name = "pic_url", nullable = false, length = 255)
	@NotNull(message = "图片URL不能为空", groups = {Valid.CREATE.class})
	private String picUrl;

	/**
	 * 是否主图
	 * nullable : false
	 * default  : 0
	 */
	@ApiModelProperty(value = "是否主图")
	@Column(name = "main_pic", nullable = false)
	@NotNull(message = "是否主图不能为空", groups = {Valid.CREATE.class})
	private Integer mainPic;

	/**
	 * 排序
	 * nullable : false
	 * default  : 0
	 */
	@ApiModelProperty(value = "排序")
	@Column(name = "pic_order", nullable = false)
	@NotNull(message = "排序不能为空", groups = {Valid.CREATE.class})
	private Integer picOrder;

	/**
	 * 图片是否有效：0无效 1有效
	 * nullable : false
	 * default  : 1
	 */
	@ApiModelProperty(value = "图片是否有效：0无效 1有效")
	@Column(name = "pic_status", nullable = false)
	@NotNull(message = "图片是否有效：0无效 1有效不能为空", groups = {Valid.CREATE.class})
	private Integer picStatus;

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

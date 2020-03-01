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
 * ProductComment
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
@Table(name = "product_comment", schema = "ganxu")
@ApiModel(value = "ProductComment")
public class ProductComment extends BaseEntity implements Serializable {

	/**
	 * 商品评论表ID
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "商品评论表ID")
	@Column(name = "id", nullable = false)
	@NotNull(message = "商品评论表ID不能为空", groups = {Valid.UPDATE.class})
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
	 * 订单ID
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "订单ID")
	@Column(name = "order_id", nullable = false)
	@NotNull(message = "订单ID不能为空", groups = {Valid.CREATE.class})
	private Long orderId;

	/**
	 * 用户ID
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "用户ID")
	@Column(name = "user_id", nullable = false)
	@NotNull(message = "用户ID不能为空", groups = {Valid.CREATE.class})
	private Integer userId;

	/**
	 * 评论标题
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "评论标题")
	@Column(name = "title", nullable = true, length = 55)
	private String title;

	/**
	 * 评论内容
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "评论内容")
	@Column(name = "content", nullable = false, length = 300)
	@NotNull(message = "评论内容不能为空", groups = {Valid.CREATE.class})
	private String content;

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

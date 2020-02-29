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
 * 物流公司信息表
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
@Table(name = "shipping_info")
@ApiModel(value = "物流公司信息表")
public class ShippingInfo extends BaseEntity implements Serializable {

	/**
	 * id
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "id")
	@Column(name = "id", nullable = false)
	@NotNull(message = "id不能为空", groups = {Valid.UPDATE.class})
	private Long id;

	/**
	 * 快递单号
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "快递单号")
	@Column(name = "shipping_sn", nullable = false, length = 255)
	@NotNull(message = "快递单号不能为空", groups = {Valid.CREATE.class})
	private String shippingSn;

	/**
	 * 物流公司名称
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "物流公司名称")
	@Column(name = "ship_name", nullable = true, length = 255)
	private String shipName;

	/**
	 * 物流公司联系人
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "物流公司联系人")
	@Column(name = "ship_contact", nullable = true, length = 255)
	private String shipContact;

	/**
	 * 物流公司联系电话
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "物流公司联系电话")
	@Column(name = "telephone", nullable = true, length = 255)
	private String telephone;

	/**
	 * 配送价格
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "配送价格")
	@Column(name = "price", nullable = true)
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

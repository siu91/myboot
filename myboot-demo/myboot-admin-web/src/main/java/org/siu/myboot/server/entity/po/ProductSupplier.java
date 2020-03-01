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
 * 商品供应商信息表
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
@Table(name = "product_supplier", schema = "ganxu")
@ApiModel(value = "商品供应商信息表")
public class ProductSupplier extends BaseEntity implements Serializable {

	/**
	 * 商品供应商ID
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "商品供应商ID")
	@Column(name = "id", nullable = false)
	@NotNull(message = "商品供应商ID不能为空", groups = {Valid.UPDATE.class})
	private Long id;

	/**
	 * 供应商名称
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "供应商名称")
	@Column(name = "supplier_name", nullable = false, length = 255)
	@NotNull(message = "供应商名称不能为空", groups = {Valid.CREATE.class})
	private String supplierName;

	/**
	 * 供应商编码
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "供应商编码")
	@Column(name = "supplier_code", nullable = false, length = 255)
	@NotNull(message = "供应商编码不能为空", groups = {Valid.CREATE.class})
	private String supplierCode;

	/**
	 * 供应商类型：1.自营，2.平台
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "供应商类型：1.自营，2.平台")
	@Column(name = "supplier_type", nullable = true)
	private Integer supplierType;

	/**
	 * 供应商联系人
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "供应商联系人")
	@Column(name = "contact_person", nullable = true, length = 255)
	private String contactPerson;

	/**
	 * 联系电话
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "联系电话")
	@Column(name = "telephone", nullable = true, length = 50)
	private String telephone;

	/**
	 * 供应商开户银行名称
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "供应商开户银行名称")
	@Column(name = "bank_name", nullable = true, length = 255)
	private String bankName;

	/**
	 * 银行账号
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "银行账号")
	@Column(name = "bank_account", nullable = true, length = 255)
	private String bankAccount;

	/**
	 * 供应商地址
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "供应商地址")
	@Column(name = "address", nullable = true, length = 255)
	private String address;

	/**
	 * 状态：0禁止，1启用
	 * nullable : true
	 * default  : 0
	 */
	@ApiModelProperty(value = "状态：0禁止，1启用")
	@Column(name = "supplier_status", nullable = true)
	private Integer supplierStatus;

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

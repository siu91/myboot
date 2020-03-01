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
 * 仓库信息表
 *
 * @author @Author Siu
 * @Date 2020-03-01 17:30:04
 * @Version 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "warehouse", schema = "ganxu")
@ApiModel(value = "仓库信息表")
public class Warehouse extends BaseEntity implements Serializable {

	/**
	 * 仓库信息表id
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "仓库信息表id")
	@Column(name = "id", nullable = false)
	@NotNull(message = "仓库信息表id不能为空", groups = {Valid.UPDATE.class})
	private Long id;

	/**
	 * 仓库编码
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "仓库编码")
	@Column(name = "warehouse_sn", nullable = true, length = 255)
	private String warehouseSn;

	/**
	 * 仓库名称
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "仓库名称")
	@Column(name = "warehoust_name", nullable = true, length = 255)
	private String warehoustName;

	/**
	 * 仓库电话
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "仓库电话")
	@Column(name = "warehouse_phone", nullable = true, length = 255)
	private String warehousePhone;

	/**
	 * 仓库联系人
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "仓库联系人")
	@Column(name = "contact", nullable = true, length = 255)
	private String contact;

	/**
	 * 省
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "省")
	@Column(name = "province", nullable = true, length = 64)
	private String province;

	/**
	 * 市
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "市")
	@Column(name = "city", nullable = true, length = 64)
	private String city;

	/**
	 * 区
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "区")
	@Column(name = "district", nullable = true, length = 64)
	private String district;

	/**
	 * 地址
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "地址")
	@Column(name = "address", nullable = true, length = 512)
	private String address;

	/**
	 * 仓库状态：0禁用，1启用
	 * nullable : false
	 * default  : 1
	 */
	@ApiModelProperty(value = "仓库状态：0禁用，1启用")
	@Column(name = "warehouse_status", nullable = false)
	@NotNull(message = "仓库状态：0禁用，1启用不能为空", groups = {Valid.CREATE.class})
	private Integer warehouseStatus;

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

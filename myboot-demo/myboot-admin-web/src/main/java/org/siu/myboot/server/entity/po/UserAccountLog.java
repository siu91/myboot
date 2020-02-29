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
 * 用户余额变动表
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
@Table(name = "user_account_log")
@ApiModel(value = "用户余额变动表")
public class UserAccountLog extends BaseEntity implements Serializable {

	/**
	 * 用户账户变动日志表ID
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "用户账户变动日志表ID")
	@Column(name = "id", nullable = false)
	@NotNull(message = "用户账户变动日志表ID不能为空", groups = {Valid.UPDATE.class})
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
	 * 变动来源：：1订单，2退货单
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "变动来源：：1订单，2退货单")
	@Column(name = "source", nullable = true)
	private Integer source;

	/**
	 * 来源ID，如订单编号
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "来源ID，如订单编号")
	@Column(name = "source_no", nullable = false)
	@NotNull(message = "来源ID，如订单编号不能为空", groups = {Valid.CREATE.class})
	private Long sourceNo;

	/**
	 * 变动金额
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "变动金额")
	@Column(name = "amount", nullable = false)
	@NotNull(message = "变动金额不能为空", groups = {Valid.CREATE.class})
	private Long amount;

	/**
	 * 创建时间
	 * nullable : true
	 * default  : CURRENT_TIMESTAMP
	 */
	@ApiModelProperty(value = "创建时间")
	@CreatedDate
	@Column(name = "create_time", nullable = true)
	private java.util.Date createTime;
}

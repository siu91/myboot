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
 * 用户积分日志表
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
@Table(name = "user_point_log")
@ApiModel(value = "用户积分日志表")
public class UserPointLog extends BaseEntity implements Serializable {

	/**
	 * 用户积分日志表主键
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "用户积分日志表主键")
	@Column(name = "id", nullable = false)
	@NotNull(message = "用户积分日志表主键不能为空", groups = {Valid.UPDATE.class})
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
	 * 积分来源：0订单，1登陆，2活动
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "积分来源：0订单，1登陆，2活动")
	@Column(name = "point_source", nullable = true)
	private Integer pointSource;

	/**
	 * 积分来源ID：订单ID等
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "积分来源ID：订单ID等")
	@Column(name = "refer_id", nullable = true)
	private Long referId;

	/**
	 * 变更积分数
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "变更积分数")
	@Column(name = "change_point", nullable = false)
	@NotNull(message = "变更积分数不能为空", groups = {Valid.CREATE.class})
	private Long changePoint;

	/**
	 * 创建时间
	 * nullable : false
	 * default  : CURRENT_TIMESTAMP
	 */
	@ApiModelProperty(value = "创建时间")
	@CreatedDate
	@Column(name = "create_time", nullable = false)
	@NotNull(message = "创建时间不能为空", groups = {Valid.CREATE.class})
	private java.util.Date createTime;
}

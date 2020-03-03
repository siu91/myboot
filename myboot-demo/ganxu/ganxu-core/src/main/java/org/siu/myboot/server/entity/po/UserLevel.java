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
 * 用户级别表
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
@Table(name = "user_level", schema = "ganxu")
@ApiModel(value = "用户级别表")
public class UserLevel extends BaseEntity implements Serializable {

	/**
	 * 用户级别表主键
	 * nullable : false
	 * default  : nextval('ganxu.ganxu_common_seq'::regclass)
	 */
	@Id
	@SequenceGenerator(name = "ganxu.ganxu_common_seq", sequenceName = "ganxu.ganxu_common_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ganxu.ganxu_common_seq")
	@ApiModelProperty(value = "用户级别表主键")
	@Column(name = "id", nullable = false)
	@NotNull(message = "用户级别表主键不能为空", groups = {Valid.UPDATE.class})
	private Long id;

	/**
	 * 等级名称
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "等级名称")
	@Column(name = "level_name ", nullable = false, length = 255)
	@NotNull(message = "等级名称不能为空", groups = {Valid.CREATE.class})
	private String levelName ;

	/**
	 * 该级别最低积分
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "该级别最低积分")
	@Column(name = "min_point", nullable = false)
	@NotNull(message = "该级别最低积分不能为空", groups = {Valid.CREATE.class})
	private Long minPoint;

	/**
	 * 该级别最高积分
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "该级别最高积分")
	@Column(name = "max_point", nullable = false)
	@NotNull(message = "该级别最高积分不能为空", groups = {Valid.CREATE.class})
	private Long maxPoint;

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
	@LastModifiedDate
	@Column(name = "update_time", nullable = true)
	private java.util.Date updateTime;

	/**
	 * 会员级别：1 普通会员，2 青铜，3白银，4黄金，5钻石
	 * nullable : false
	 * default  : null
	 */
	@ApiModelProperty(value = "会员级别：1 普通会员，2 青铜，3白银，4黄金，5钻石")
	@Column(name = "level_code", nullable = false)
	@NotNull(message = "会员级别：1 普通会员，2 青铜，3白银，4黄金，5钻石不能为空", groups = {Valid.CREATE.class})
	private Integer levelCode;
}

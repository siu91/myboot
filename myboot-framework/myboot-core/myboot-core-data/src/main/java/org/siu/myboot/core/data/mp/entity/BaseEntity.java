/*
 * @(#) BaseEntity.java 2018/09/18
 *
 * Copyright 2018 CEC(Fujian) Healthcare Big Data Operation Service Co., Ltd. All rights reserved.
 */
package org.siu.myboot.core.data.mp.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @Author Siu
 * @Date 2020/3/16 9:50
 * @Version 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public abstract class BaseEntity<T extends Model<T>> extends Model<T> implements Serializable {

}

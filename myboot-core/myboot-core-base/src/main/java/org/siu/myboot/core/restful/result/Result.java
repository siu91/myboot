package org.siu.myboot.core.restful.result;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author Siu
 * @Date 2020/2/23 16:47
 * @Version 0.0.1
 */
@Data
@Accessors(chain = true)
public class Result {
    private int code;
    private String message;
    private Object data;
    private Object debug;




}

package com.jijie.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Description: </p>
 *
 * @author jijie
 * @Date 2021/5/17 11:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResult<T> {
    //404 not_found
    private Integer code;
    private String message;
    private T data;

    public CommentResult(Integer code,String message) {
        this(code,message,null);
    }
}

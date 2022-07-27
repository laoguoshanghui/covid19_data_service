package com.hqyj.covid19.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> implements Serializable {
    /**
     * 返回编码
     */
    private Integer code;
    /**
     * 返回提示消息
     */
    private String message;
    /**
     * 返回数据体
     */
    private T data;

    public ResponseDTO(Integer code , T data){
        this.code = code;
        this.data = data;
    }

}

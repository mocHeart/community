package com.moc.community.dto;

import com.moc.community.exception.CustomizeErrorCodeEnum;
import com.moc.community.exception.CustomizeException;
import lombok.Data;

@Data
public class ResultDto {

    private Integer code;
    private String message;


    private static ResultDto errorOf(Integer code, String message) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }


    public static ResultDto errorOf(CustomizeErrorCodeEnum errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultDto errorOf(CustomizeException e) {
        return errorOf(e.getCode(), e.getMessage());
    }

    public static ResultDto okOf() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
    }

}

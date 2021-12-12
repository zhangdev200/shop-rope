package com.javaweb.shopping.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@ApiModel(value = "ResultVO对象",description = "封装接口返回给前端的数据")
public class ResultVO {
    //响应给前端的状态码
    @ApiModelProperty(value = "响应状态码",dataType = "int")
    private int code;


    //响应给前端的提示信息
    @ApiModelProperty("响应提示信息")
    private String msg;

    //响应给前端的数据
    @ApiModelProperty("响应数据")
    private Object data;

    public ResultVO() {
    }

    public ResultVO(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}

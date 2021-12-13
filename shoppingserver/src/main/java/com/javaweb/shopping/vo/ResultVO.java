package com.javaweb.shopping.vo;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ResultVO {
    //响应给前端的状态码
    private int code;


    //响应给前端的提示信息

    private String msg;

    //响应给前端的数据

    private Object data;

    public ResultVO() {
    }

    public ResultVO(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}

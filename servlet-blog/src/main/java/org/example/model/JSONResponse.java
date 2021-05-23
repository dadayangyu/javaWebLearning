package org.example.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class JSONResponse {
    //业务操作是否成功,这里Boolean后面的内容必须是前端定义好的
    private boolean success;
    //业务数据  必须是object类型
    private Object data;
    //错误码
    private String code;
    //错误信息
    private String message;

    public void setSuccess(boolean b) {
    }

    public void setData(List<Article> query) {
    }

    public void setCode(String err) {
    }

    public void setMassage(String s) {
    }

    public void setMessage(String s) {
    }
}

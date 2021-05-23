package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/*使用插件Lombok来自动生成方法*/
@Getter
@Setter
@ToString
public class User {
    private Integer id;//在这里用基础数据类型的包装类型,int默认类型是0,包装类的默认类型是null
    private String username;
    private String password;
    private String nickname;
    private boolean sex;
    java.util.Date Birthday;
    private String head;

}

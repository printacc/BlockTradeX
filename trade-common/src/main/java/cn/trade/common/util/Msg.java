package cn.trade.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Msg {

    //响应的代码
    private Integer code;

    //响应的消息
    private String message;
    private List<Object> objects;

    public  List<Object> getObjects() {
        return objects;
    }
    //响应的参数
    private Map<String,Object> extend = new HashMap<>();

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //关键字
    private String name ;
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public static Msg success(){
        Msg msg = new Msg();
        msg.setCode(200);
        msg.setMessage("处理成功!");
        return msg;
    }

    public static Msg fail(){
        Msg msg = new Msg();
        msg.setCode(500);
        msg.setMessage("处理失败!");
        return msg;
    }

    public Msg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }
}

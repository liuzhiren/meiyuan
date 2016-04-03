package bean;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by a312689543 on 2016/3/28.
 */
public class ChatMessage {
    private String name;
    private String msg;
    private Date date;
    private Type type;
    public enum Type{
        INCOMING,OUTCOMING
    }
    public ChatMessage(){

    }
    public ChatMessage(String msg,Type type,Date date){
        super();
        this.msg=msg;
        this.type=type;
        this.date=date;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

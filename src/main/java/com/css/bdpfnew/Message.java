package com.css.bdpfnew;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 消息
 * @Auther: GodNan
 * @Date: 2018/5/25 10:28
 * @Version: 1.0
 * @Description:
 */
public class Message {

    public final static String ERROR_CODE_PARAM = "10000";
    public final static String ERROR_CODE_EMPTY_DATA = "10001";

    public final static String ERROR_CODE_SQL = "20000";

    public final static String ERROR_CODE_REQUEST_EXPIRED = "40000";
    public final static String ERROR_CODE_AUTHENTICATE = "40001";
    
    public final static String ERROR_ZHUXIAO_NEXT_APPLY_DATE = "80008";

    /**
     * 类型
     */
    public enum Type {

        /** 成功 */
        success,

        /** 警告 */
        warn,

        /** 错误 */
        error
    }

    /** 类型 */
    private Type type;

    /** 错误代码 */
    private String returnCode;

    /** 时间戳 */
    private String timestamp;

    /** 内容 */
    private String content;

    private Object data;
    /**
     * 初始化一个新创建的 Message 对象，使其表示一个空消息。
     */
    public Message() {

    }

    /**
     * 初始化一个新创建的 Message 对象
     *
     * @param type
     *            类型
     * @param content
     *            内容
     */
    public Message(Type type, String content) {
        this.type = type;
        this.content = content;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        this.timestamp = date;
    }

    /**
     * 初始化一个新创建的 Message 对象
     *
     * @param type
     *            类型
     * @param content
     *            内容
     * @param  data
     *            数据
     */
    public Message(Type type, String content,Object data) {
        this.type = type;
        this.content = content;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        this.timestamp = date;
        this.data = data;
    }


    /**
     * 返回成功消息
     *
     * @param content
     *            内容
     *
     * @return 成功消息
     */
    public static Message success(String content) {
        return new Message(Type.success, content);
    }

    /**
     * 返回成功消息
     *
     * @param content
     *            内容
     * @param data
     *            数据
     *
     * @return 成功消息
     */
    public static Message success(String content, Object data) {
        return new Message(Type.success, content, data);
    }

    /**
     * 返回警告消息
     *
     * @param content
     *            内容
     *
     * @return 警告消息
     */
    public static Message warn(String content) {
        return new Message(Type.warn, content);
    }

    /**
     * 返回错误消息
     *
     * @param content
     *            内容
     *
     * @return 错误消息
     */
    public static Message error(String content) {
        return new Message(Type.error, content);
    }

    /**
     * 返回错误消息
     *
     * @param content
     *            内容
     * @param code
     *            错误代码
     * @return 错误消息
     */
    public static Message error(String content, String code) {
        Message message = new Message(Type.error, content);
        message.setReturnCode(code);
        return message;
    }


    /**
     * 获取类型
     *
     * @return 类型
     */
    public Type getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type
     *            类型
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * 获取内容
     *
     * @return 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content
     *            内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取时间戳
     * @return timestamp
     *              时间戳
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * 设置时间戳
     * @param timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @Override
    public String toString() {
        return "{" +
                "type=" + type +
                ", returnCode='" + returnCode + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", content='" + content + '\'' +
                ", data=" + data +
                '}';
    }
}

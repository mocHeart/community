package com.moc.community.exception;

public enum CustomizeErrorCodeEnum implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你找的问题不在啦，要不换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"为选择任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试"),
    SYS_ERROR(2004,"服务器崩溃啦，请稍后再试"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "你回复的评论不存在啦，要不换个试试？")
    ;


    private Integer code;
    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

package com.moc.community.exception;

public enum CustomizeErrorCodeEnum implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不在啦，要不换个试试？");


    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCodeEnum(String message) {
        this.message = message;
    }
}

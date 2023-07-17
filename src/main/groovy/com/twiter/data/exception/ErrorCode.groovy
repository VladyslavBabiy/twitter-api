package com.twiter.data.exception

enum ErrorCode {
    UNEXPECTED_EXCEPTION('000'),
    USER_NOT_FOUND_EXCEPTION('001'),
    POST_NOT_FOUND_EXCEPTION('002'),
    POST_HAS_ALREADY_BEEN_LIKED('003'),
    USER_SUBSCRIPTION_EXCEPTION('004'),


    private String code;

    ErrorCode(String code) {
        this.code = code
    }

    String getCode() {
        return code
    }

    @Override
    String toString() {
        return name() + ' (' + code + ')';
    }

}
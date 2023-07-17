package com.twiter.data.exception

class ApplicationException extends RuntimeException {
    private List<ErrorCode> errors = new ArrayList<>();

    ApplicationException(ErrorCode... errorCodes) {
        super(Arrays.toString(errorCodes));
        for (ErrorCode errorCode : errorCodes) {
            errors.add(errorCode);
        }
    }

    List<ErrorCode> getErrors() {
        return errors;
    }
}

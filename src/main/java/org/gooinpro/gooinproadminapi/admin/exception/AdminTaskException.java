package org.gooinpro.gooinproadminapi.admin.exception;

import lombok.Getter;

@Getter
public class AdminTaskException extends RuntimeException {

    private final int status;
    private final String msg;

    public AdminTaskException(int status, String msg) {
        super(msg);
        this.status = status;
        this.msg = msg;
    }
}

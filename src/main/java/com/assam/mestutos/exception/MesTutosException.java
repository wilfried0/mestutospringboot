package com.assam.mestutos.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MesTutosException extends Exception {
    public MesTutosException() {}

    @Getter
    public static class ResponseException{
        private String message;
        private int status;

        public ResponseException(Exception e, int status) {
            this.message = e.getMessage();
            this.status = status;
        }
    }
    public MesTutosException(String message) {
        super(message);
    }

    public MesTutosException(String message, Throwable cause) {
        super(message, cause);
    }

    public MesTutosException(Throwable cause) {
        super(cause);
    }
}

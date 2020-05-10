package br.com.cooperative.vote.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(value = BAD_REQUEST)
public class EnterAValidAssociateException extends RuntimeException {
    public EnterAValidAssociateException(String msg) {
        super(msg);
    }
}

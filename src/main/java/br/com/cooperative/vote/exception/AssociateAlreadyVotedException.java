package br.com.cooperative.vote.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(value = BAD_REQUEST)
public class AssociateAlreadyVotedException extends RuntimeException {
    public AssociateAlreadyVotedException(String msg) {
        super(msg);
    }
}

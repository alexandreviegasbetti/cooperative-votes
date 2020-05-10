package br.com.cooperative.vote.exception;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String msg) {
        super(msg);
    }
}

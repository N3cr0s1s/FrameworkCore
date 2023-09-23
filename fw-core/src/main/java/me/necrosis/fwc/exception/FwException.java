package me.necrosis.fwc.exception;

import lombok.Getter;

@Getter
public class FwException extends Exception {

    private final Throwable exception;

    public FwException(String message, Throwable e) {
        super(message);
        this.exception = e;
    }

}

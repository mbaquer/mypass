package com.mypass.recovery;

public abstract class RecoveryHandler {
    private RecoveryHandler nextHandler;

    public void setNextHandler(RecoveryHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleRequest(String question, String answer) {
        if (nextHandler != null) {
            nextHandler.handleRequest(question, answer);
        }
    }

    protected abstract boolean verifyAnswer(String question, String answer);
}
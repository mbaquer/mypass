package com.mypass.recovery;

public class Question2Handler extends RecoveryHandler {
    private final String expectedQuestion = "Your second security question";
    private final String expectedAnswer;

    public Question2Handler(String expectedAnswer) {
        this.expectedAnswer = expectedAnswer;
    }

    @Override
    public void handleRequest(String question, String answer) {
        if (verifyAnswer(question, answer)) {
            super.handleRequest("", "");
        } else {
            System.out.println("Incorrect answer for Question 2");
        }
    }

    @Override
    protected boolean verifyAnswer(String question, String answer) {
        return expectedQuestion.equals(question) && expectedAnswer.equals(answer);
    }
}
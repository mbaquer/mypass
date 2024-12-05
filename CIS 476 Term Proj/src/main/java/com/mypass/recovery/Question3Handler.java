package com.mypass.recovery;

public class Question3Handler extends RecoveryHandler {
    private final String expectedQuestion = "Your third security question";
    private final String expectedAnswer;

    public Question3Handler(String expectedAnswer) {
        this.expectedAnswer = expectedAnswer;
    }

    @Override
    public void handleRequest(String question, String answer) {
        if (verifyAnswer(question, answer)) {
            System.out.println("All questions answered correctly. Reset your password now.");
        } else {
            System.out.println("Incorrect answer for Question 3");
        }
    }

    @Override
    protected boolean verifyAnswer(String question, String answer) {
        return expectedQuestion.equals(question) && expectedAnswer.equals(answer);
    }
}
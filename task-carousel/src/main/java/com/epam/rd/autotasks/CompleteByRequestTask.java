package com.epam.rd.autotasks;

public class CompleteByRequestTask implements Task {
    private boolean isCompleteCalled = false;
    private boolean isFinished = false;

    public CompleteByRequestTask() {
        // Constructor sin parámetros
    }

    @Override
    public void execute() {
        if (isCompleteCalled) {
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    public void complete() {
        isCompleteCalled = true;
    }
}

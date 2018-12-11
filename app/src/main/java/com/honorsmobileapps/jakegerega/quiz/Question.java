package com.honorsmobileapps.jakegerega.quiz;

public class Question {
    private int resourceIDOfStringResource;
    private boolean answerState;

    public Question(int resourceIDOfStringResource, boolean answerState){
        this.resourceIDOfStringResource = resourceIDOfStringResource;
        this.answerState = answerState;
    }
    public boolean getAnswerState(){
        return answerState;
    }
    public int getIDOfStringResource(){
        return resourceIDOfStringResource;
    }
    public void setIDOfStringResource(int resourceIDOfStringResource){
        this.resourceIDOfStringResource = resourceIDOfStringResource;
    }
    public void setAnswerState(boolean answerState){
        this.answerState = answerState;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ericstaryou
 */
public class Subtopic {

    int subtopicID;
    String title;
    String textualExplanation;
    String codeValue;

    public int getSubtopicID() {
        return subtopicID;
    }

    public void setSubtopicID(int subtopicID) {
        this.subtopicID = subtopicID;
    }

    public String getTextualExplanation() {
        return textualExplanation;
    }

    public void setTextualExplanation(String textualExplanation) {
        this.textualExplanation = textualExplanation;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

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
public class State {
    int subtopicID;
    int assessmentID;
    String config;
    
    public int getSubtopicID() {
        return subtopicID;
    }

    public void setSubtopicID(int subtopicID) {
        this.subtopicID = subtopicID;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}

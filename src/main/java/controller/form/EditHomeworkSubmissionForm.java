/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.form;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
public class EditHomeworkSubmissionForm {
    
    
    private String submission;
    private int submissionId;

    /**
     * @return the submissionId
     */
    public int getSubmissionId() {
        return submissionId;
    }

    /**
     * @param submissionId the submissionId to set
     */
    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    /**
     * @return the submission
     */
    public String getSubmission() {
        return submission;
    }

    /**
     * @param submission the submission to set
     */
    public void setSubmission(String submission) {
        this.submission = submission;
    }
}

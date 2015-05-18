/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
@Entity
@Table(name="homeworksubmissions")
public class HomeworkSubmission {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    
    @ManyToOne
    private Homework homework;
    
    @ManyToOne
    private User student;
    
    private boolean submitted = false;
    
    private String submission;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the homework
     */
    public Homework getHomework() {
        return homework;
    }

    /**
     * @param homework the homework to set
     */
    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    /**
     * @return the student
     */
    public User getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(User student) {
        this.student = student;
    }

    /**
     * @return the submitted
     */
    public boolean isSubmitted() {
        return submitted;
    }

    /**
     * @param submitted the submitted to set
     */
    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
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

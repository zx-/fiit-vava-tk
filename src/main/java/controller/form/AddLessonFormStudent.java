/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.form;

import model.entity.User;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
public class AddLessonFormStudent {
    
    private User student;
    
    private boolean present = true;
    
    private int userId;

    public AddLessonFormStudent() {
    }

    
    
    AddLessonFormStudent(User s) {
        
        this.student = s;  
        this.userId = s.getUser_id();
        
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
     * @return the present
     */
    public boolean isPresent() {
        return present;
    }

    /**
     * @param present the present to set
     */
    public void setPresent(boolean present) {
        this.present = present;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
    
}

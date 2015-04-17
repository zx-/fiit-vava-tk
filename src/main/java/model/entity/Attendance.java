/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import javax.persistence.CascadeType;
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
@Table(name = "attendances")
public class Attendance {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Lesson lesson;
    
    private boolean wasPresent;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private User student;

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
     * @return the lesson
     */
    public Lesson getLesson() {
        return lesson;
    }

    /**
     * @param lesson the lesson to set
     */
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    /**
     * @return the wasPresent
     */
    public boolean isWasPresent() {
        return wasPresent;
    }

    /**
     * @param wasPresent the wasPresent to set
     */
    public void setWasPresent(boolean wasPresent) {
        this.wasPresent = wasPresent;
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
    
    
    
}

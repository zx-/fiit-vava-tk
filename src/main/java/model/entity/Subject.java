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
import javax.persistence.OneToOne;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
@Entity
public class Subject {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
        
    private int order;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private User teacher;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private ClassRoom classRoom;

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
     * @return the order
     */
    public int getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * @return the teacher
     */
    public User getTeacher() {
        return teacher;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    /**
     * @return the classRoom
     */
    public ClassRoom getClassRoom() {
        return classRoom;
    }

    /**
     * @param classRoom the classRoom to set
     */
    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
    
    
}

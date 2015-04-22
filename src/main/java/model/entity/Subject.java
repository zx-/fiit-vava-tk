/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
@Entity
@Table(name = "subjects")
public class Subject {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
        
    private int subjectOrder;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private User teacher;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private ClassRoom classRoom;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="subject",fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Lesson> lessons;
    
    private String name;
    
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
     * @return the subjectOrder
     */
    public int getSubjectOrder() {
        return subjectOrder;
    }

    /**
     * @param subjectOrder the subjectOrder to set
     */
    public void setSubjectOrder(int subjectOrder) {
        this.subjectOrder = subjectOrder;
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lessons
     */
    public Collection<Lesson> getLessons() {
        return lessons;
    }

    /**
     * @param lessons the lessons to set
     */
    public void setLessons(Collection<Lesson> lessons) {
        this.lessons = lessons;
    }
    
    public void addLesson(Lesson l){
    
            if(this.lessons == null){
            
                this.lessons = new ArrayList<>();
            
            }
            
            this.lessons.add(l);
    
    }
    
    
}

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
import javax.persistence.OrderBy;
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
    
    @ManyToOne
    private User teacher;
    
    @ManyToOne
    private ClassRoom classRoom;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="subject",fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Lesson> lessons;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="subject",fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy("id DESC")
    private Collection<Homework> homeworks;
    
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
    
    /**
     * @return the homeworks
     */
    public Collection<Homework> getHomeworks() {
        return homeworks;
    }

    /**
     * @param homeworks the homeworks to set
     */
    public void setHomeworks(Collection<Homework> homeworks) {
        this.homeworks = homeworks;
    }
    
    public void addHomework(Homework h){
    
        if(this.homeworks == null){
            
                this.homeworks = new ArrayList<>();
            
            }
            
            this.homeworks.add(h);
    
    }
    
    public boolean equals(Object other) {
        if (this == other) return true;
        if ( other == null || getClass() != other.getClass()) 
            return false;

        final Subject s = (Subject) other;

        if ( !s.getName().equals( getName() ) ) return false;
        if ( !s.getClassRoom().equals( getClassRoom() ) ) return false;
        if ( !s.getTeacher().equals( getTeacher()) ) return false;
        
        return true;
    }

    public int hashCode() {
        int result;
        result = getName().hashCode()*17 + getClassRoom().hashCode()*29;
        return result;
    }


    
}

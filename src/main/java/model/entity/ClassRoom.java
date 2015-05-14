/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
@Entity
@Table(name = "classrooms", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id") })
public class ClassRoom {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="teacher_id")
    private User teacher;
    
    @Column()
    private String name;
    
    @OneToMany(targetEntity=User.class, mappedBy="classRoom", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<User> students;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="classRoom",fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy("subjectOrder")
    private Collection<Subject> subjects;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return the students
     */
    public Collection<User> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(Collection<User> students) {
        this.students = students;
    }

    /**
     * @return the subjects
     */
    public Collection<Subject> getSubjects() {
        return subjects;
    }

    /**
     * @param subjects the subjects to set
     */
    public void setSubjects(Collection<Subject> subjects) {
        this.subjects = subjects;
    }
    
    public void addStudent(User u){
    
        if(this.students == null ){
        
            this.students = new ArrayList<>();
        
        }
        this.students.add(u);
    
    }

    public void addSubject(Subject sub) {
        
        if(this.subjects == null ){
        
            this.subjects = new ArrayList<>();
        
        }
        this.subjects.add(sub);
        
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if ( !(other instanceof ClassRoom) ) return false;

        final ClassRoom c = (ClassRoom) other;

        if ( !c.getName().equals( getName() ) ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = getName().hashCode();
        return result;
    }
    
}

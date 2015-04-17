/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
    
    @OneToMany(targetEntity=User.class, mappedBy="classRoom", cascade = CascadeType.ALL)
    private Collection<User> students;
    
    @OneToMany(cascade = CascadeType.ALL)
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
    
}

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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
@Entity
@Table(name="homeworks")
public class Homework {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    
    @ManyToOne
    private Subject subject;
    
    private String name;
    
    private String task;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="homework",fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<HomeworkSubmission> submissions;
    
    public void addSubmission(HomeworkSubmission s){
        
        if(this.submissions == null){

            this.submissions = new ArrayList<>();

              }

        this.submissions.add(s);      
    
    }

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
     * @return the subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
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
     * @return the task
     */
    public String getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * @return the submissions
     */
    public Collection<HomeworkSubmission> getSubmissions() {
        return submissions;
    }

    /**
     * @param submissions the submissions to set
     */
    public void setSubmissions(Collection<HomeworkSubmission> submissions) {
        this.submissions = submissions;
    }
    
}

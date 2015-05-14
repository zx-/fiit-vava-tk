/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
@Entity
@Table(name = "lessons")
public class Lesson {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    
    @ManyToOne
    //@ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private User substitution = null;
    
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @PrePersist
    protected void onCreate() {
        setDate(new Date());
    }
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="lesson")
    private Collection<Attendance> attendances;

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
     * @return the substitution
     */
    public User getSubstitution() {
        return substitution;
    }

    /**
     * @param substitution the substitution to set
     */
    public void setSubstitution(User substitution) {
        this.substitution = substitution;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the attendances
     */
    public Collection<Attendance> getAttendances() {
        return attendances;
    }

    /**
     * @param attendances the attendances to set
     */
    public void setAttendances(Collection<Attendance> attendances) {
        this.attendances = attendances;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void addAttendance(Attendance a){
    
        if(this.attendances == null){
        
            this.attendances = new ArrayList<>();
        
        }
        
        this.attendances.add(a);
    
    }
    
    public boolean equals(Object other) {
        if (this == other) return true;
        if ( !(other instanceof Lesson) ) return false;

        final Lesson l = (Lesson) other;

        if ( !l.getSubject().equals( getSubject() ) ) return false;
        if ( !l.getDescription().equals( getDescription() ) ) return false;
        
        return true;
    }
    
}

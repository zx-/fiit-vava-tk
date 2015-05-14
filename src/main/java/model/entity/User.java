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
import javax.persistence.ManyToOne;
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
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = "username") })
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Integer user_id;
    
    @Column(name = "username", unique = true, nullable = false, length=50)
    private String username;
    
    @Column(name = "password", unique = false, nullable = false, length=50)
    private String password;
    
    @Column(name = "email", unique = true, nullable = false, length=335)
    private String email;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinTable(name="user_roles",
        joinColumns = {
            @JoinColumn(name="user_id",referencedColumnName="user_id")
        },
        inverseJoinColumns = {
            @JoinColumn(name="role_id",referencedColumnName="id")
        })
    private Role role;
    
    
    @ManyToOne(cascade = CascadeType.ALL)
    private ClassRoom classRoom;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="student")
    private Collection<Grade> grades;

    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="teacher",fetch=FetchType.LAZY)
    @OrderBy("subjectOrder")
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Subject> teaching;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="student",fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Attendance> attendances;
    
    /**
     * @return the user_id
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [id=" + user_id + ", name=" + username + ", email=" + email + "]";
}

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
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
     * @return the grades
     */
    public Collection<Grade> getGrades() {
        return grades;
    }

    /**
     * @param grades the grades to set
     */
    public void setGrades(Collection<Grade> grades) {
        this.grades = grades;
    }

    /**
     * @return the teaching
     */
    public Collection<Subject> getTeaching() {
        return teaching;
    }

    /**
     * @param teaching the teaching to set
     */
    public void setTeaching(Collection<Subject> teaching) {
        this.teaching = teaching;
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

    public void addTeaching(Subject sub) {
   
        if(this.teaching == null){
        
            this.teaching = new ArrayList<>();
        
        }
        
        this.teaching.add(sub);
    
    }
    
    public void addAttendance(Attendance a){
    
        if(this.attendances == null){
        
            this.attendances = new ArrayList<>();
        
        }
        
        this.attendances.add(a);
    
    
    }
    
    
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if ( !(other instanceof User) ) return false;

        final User u = (User) other;

        if ( !u.getUsername().equals( getUsername()) ) return false;
        if ( !u.getEmail().equals( getEmail()) ) return false;

        return true;
    }

//    @Override
//    public int hashCode() {
//        int result;
//        result = getUsername().hashCode() + 27 * getEmail().hashCode();
//        return result;
//    }

    public void addGrade(Grade g) {
  
        if(this.grades == null){
        
            this.grades = new ArrayList<>();
        
        }
        
        this.grades.add(g);
    
    }


    public Collection<Grade> getGradesBySubject(Subject s){
    
        ArrayList<Grade> gr = new ArrayList<>();
        
        for(Grade g:getGrades()){
        
            if(g.getSubject().equals(s))
                gr.add(g);
        
        }
        
        return gr;
    
    }
    
     public Collection<Attendance> getAttendanceBySubject(Subject s){
    
        ArrayList<Attendance> gr = new ArrayList<>();
        
        for(Attendance g:getAttendances()){
        
            if(g.getLesson().getSubject().equals(s))
                gr.add(g);
        
        }
        
        return gr;
    
    }
    
    
}

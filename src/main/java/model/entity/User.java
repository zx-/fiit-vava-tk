/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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



    
    
}

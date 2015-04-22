/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.entity.User;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
public class AddLessonForm {
    
    private List<AddLessonFormStudent> students;
    
    private String description;    

    /**
     * @return the students
     */
    public List<AddLessonFormStudent> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(List<AddLessonFormStudent> students) {
        this.students = students;
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

    public void addStudents(Collection<User> students) {
        
        this.students = new ArrayList<>();
        
        for(User s:students){
        
            this.students.add(new AddLessonFormStudent(s));
        
        }
    
    }
    
}

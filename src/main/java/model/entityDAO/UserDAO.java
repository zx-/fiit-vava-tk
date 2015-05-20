/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entityDAO;

import java.util.Collection;
import model.entity.User;
import java.util.List;
import model.entity.Attendance;
import model.entity.Grade;
import model.entity.Subject;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */

public interface UserDAO {
    public List<User> list();
     
    public User get(int id);
     
    public void saveOrUpdate(User user);
     
    public void delete(int id);

    public User getUser(String login);
    
    public Collection<Attendance> getAttendanceBySubject(User user,Subject s);
    
    public Collection<Grade> getGradesBySubject(User user,Subject s);
    
    
}


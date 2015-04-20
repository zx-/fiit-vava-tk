/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entityDAO;

import java.util.Collection;
import model.entity.ClassRoom;
import model.entity.User;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
public interface ClassRoomDAO {
    
    public Collection<ClassRoom> getByTeacher(User u);
    
    public Collection<ClassRoom> getAll();
    
    public void saveOrUpdate(ClassRoom c);
    
    public ClassRoom getByName(String name);    
}

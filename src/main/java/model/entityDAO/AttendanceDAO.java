/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entityDAO;

import java.util.Collection;
import model.entity.Attendance;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
public interface AttendanceDAO {
    
    public Collection<Attendance> getAttendancesByStudentAndSubject(int userId,int subjectId);
    
}

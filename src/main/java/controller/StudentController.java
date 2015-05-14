/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Collection;
import model.entity.Attendance;
import model.entity.ClassRoom;
import model.entity.Subject;
import model.entity.User;
import model.entityDAO.AttendanceDAO;
import model.entityDAO.SubjectDAO;
import model.entityDAO.UserDAO;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    
    private static final Logger logger = Logger.getLogger(StudentController.class);

    @Autowired
    private UserDAO userDao;
    
    @Autowired
    private AttendanceDAO attendanceDAO;
    
    @Autowired
    private SubjectDAO subjectDao;
 
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView root() {
    
        ModelAndView model = new ModelAndView("student-root");
        
        
        return model;
    
    }
    
    @RequestMapping(value = "/timetable", method = RequestMethod.GET)
    public ModelAndView timetable() {
        
        Authentication authentication = SecurityContextHolder.getContext().
	                getAuthentication();
        UserDetails name = (UserDetails) authentication.getPrincipal();
            
        User student = userDao.getUser(name.getUsername());
        ClassRoom classRoom = student.getClassRoom();
        

        ModelAndView model = new ModelAndView("student-timetable");
        model.addObject("classroom", classRoom.getName());
        model.addObject("timetable",classRoom.getSubjects());

        return model;

    }
    
    @RequestMapping(value = "/subject/{subject}-{subId}", method = RequestMethod.GET)
    public ModelAndView subject(
            @PathVariable String subject,
            @PathVariable int subId) {
        
        Authentication authentication = SecurityContextHolder.getContext().
	                getAuthentication();
        UserDetails name = (UserDetails) authentication.getPrincipal();
            
        User student = userDao.getUser(name.getUsername());
             
        Subject sub = subjectDao.getById(subId);
        
        Collection<Attendance> col = student.getAttendances();
                
        ModelAndView model = new ModelAndView("student-subject");
        model.addObject("subjectName",subject);
        model.addObject("grades",student.getGradesBySubject(sub));
        model.addObject("attendance",student.getAttendanceBySubject(sub));
        return model;

    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.form.AddHomeworkForm;
import controller.form.EditHomeworkSubmissionForm;
import java.util.Collection;
import model.entity.Attendance;
import model.entity.ClassRoom;
import model.entity.HomeworkSubmission;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import view.PDFView;

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
    
    @RequestMapping(value = "/homeworks", method = RequestMethod.GET)
    public ModelAndView homeworks() {
        
        Authentication authentication = SecurityContextHolder.getContext().
	                getAuthentication();
        UserDetails name = (UserDetails) authentication.getPrincipal();
            
        User student = userDao.getUser(name.getUsername());
        

        ModelAndView model = new ModelAndView(
                "student-homeworks",
                "editHomeworkSubmissionForm", new EditHomeworkSubmissionForm()
        );

        model.addObject("hw", student.getHomeworkSubmissions());
        model.addObject("actionPath", "student/homeworks");
        
        
        return model;

    }
    
    @RequestMapping(value = "/homeworks", method = RequestMethod.POST)
    public String homeworksEdit(
        @ModelAttribute("editHomeworkSubmissionForm") EditHomeworkSubmissionForm editHomeworkSubmissionForm
            ){
        
        Authentication authentication = SecurityContextHolder.getContext().
	                getAuthentication();
        UserDetails name = (UserDetails) authentication.getPrincipal();
            
        User student = userDao.getUser(name.getUsername());
        

        HomeworkSubmission sub = student.getHomeworkSubmissionById(
                editHomeworkSubmissionForm.getSubmissionId()
        );
        
        sub.setSubmission(editHomeworkSubmissionForm.getSubmission());
        sub.setSubmitted(true);
        
        userDao.saveOrUpdate(student);
        
        return "redirect:/student/homeworks";

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
    
    
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ModelAndView pdfTest(){
        
        Authentication authentication = SecurityContextHolder.getContext().
	                getAuthentication();
        UserDetails name = (UserDetails) authentication.getPrincipal();
            
        User student = userDao.getUser(name.getUsername());
    
        ModelAndView modelView = new ModelAndView(new PDFView());
        
        modelView.addObject("student", student);
        
        return modelView;
    
    }
    
}

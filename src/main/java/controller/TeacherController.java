/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.form.AddLessonForm;
import controller.form.AddLessonFormStudent;
import java.util.Collection;
import model.entity.Attendance;
import model.entity.ClassRoom;
import model.entity.Lesson;
import model.entity.Subject;
import model.entity.User;
import model.entityDAO.ClassRoomDAO;
import model.entityDAO.SubjectDAO;
import model.entityDAO.UserDAO;
import org.apache.log4j.Logger;
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

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    
    private static final Logger logger = Logger.getLogger(TeacherController.class);

    @Autowired
    private UserDAO userDao;
    
    @Autowired
    private ClassRoomDAO classRoomDao;
    
    @Autowired
    private SubjectDAO subjectDao;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView root() {
    
        ModelAndView model = new ModelAndView("teacher-root");
        
        
        return model;
    
    }
    
    @RequestMapping(value = "/timetable", method = RequestMethod.GET)
    public ModelAndView timetable() {
        
        Authentication authentication = SecurityContextHolder.getContext().
	                getAuthentication();
        UserDetails name = (UserDetails) authentication.getPrincipal();
            
        User teacher = userDao.getUser(name.getUsername());
        Collection<Subject> subjects = teacher.getTeaching();
        
        
        
        

        ModelAndView model = new ModelAndView("teacher-timetable");
        //model.addObject("classroom", classRoom.getName());
        model.addObject("timetable",subjects);

        return model;

    }
    
    @RequestMapping(value = "/{subject}-{subId}/{classRoomName}", method = RequestMethod.GET)
    public ModelAndView subjectDetail(
            @PathVariable String subject,
            @PathVariable int subId,
            @PathVariable String classRoomName) {    
        
               
        
        Authentication authentication = SecurityContextHolder.getContext().
	                getAuthentication();
        UserDetails name = (UserDetails) authentication.getPrincipal();
            
        User teacher = userDao.getUser(name.getUsername());        
        Subject sub = subjectDao.getById(subId);
        ClassRoom classRoom = classRoomDao.getByName(classRoomName);
         
        if(classRoom == null || sub == null){ 
            
            ModelAndView model = new ModelAndView("teacher-subject");
            
            model.addObject("classRoom","Not Found!");
            return model;
        
        }
        
        
        Collection<Lesson> lessons = sub.getLessons();        
        AddLessonForm addLessonForm = new AddLessonForm();
        addLessonForm.addStudents(classRoom.getStudents());
        
        ModelAndView model = new ModelAndView(
                "teacher-subject",
                "addLessonForm",addLessonForm);
        
        model.addObject("actionPath","teacher/"+subject+"-"+subId+"/"+classRoomName);
        
        if(lessons != null){
        
            model.addObject("lessons",lessons);
        
        }
        
        
        model.addObject("classRoom",classRoom.getName());
        model.addObject("subject",subject);
        
        return model;
    
    }
    
    @RequestMapping(value = "/{subject}-{subId}/{classRoomName}", method = RequestMethod.POST)
    public String addLessonToSubject(
            @ModelAttribute("addLessonForm") AddLessonForm addLessonForm,
            @PathVariable String subject,
            @PathVariable int subId,
            @PathVariable String classRoomName){
    
        Authentication authentication = SecurityContextHolder.getContext().
	                getAuthentication();
        UserDetails name = (UserDetails) authentication.getPrincipal();
            
        User teacher = userDao.getUser(name.getUsername());        
        Subject sub = subjectDao.getById(subId);
        
        Lesson lesson = new Lesson();
        lesson.setDescription(addLessonForm.getDescription());
        
                
        for(AddLessonFormStudent as :addLessonForm.getStudents()){
        
            Attendance a = new Attendance();
            User s = userDao.get(as.getUserId());
            
            //a.setLesson(lesson);
            lesson.addAttendance(a);
            
            //a.setStudent(s);
            s.addAttendance(a);
            
            a.setWasPresent(as.isPresent());
            
            userDao.saveOrUpdate(s);
        
        }
        
        sub.addLesson(lesson);
        
        subjectDao.saveOrUpdate(sub);
        
        
        return "redirect:/teacher/"+subject+"-"+subId+"/"+classRoomName;
    
    }
    
}

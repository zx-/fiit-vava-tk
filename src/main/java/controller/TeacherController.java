/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Collection;
import model.entity.Subject;
import model.entity.User;
import model.entityDAO.UserDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
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
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView root() {
    
        ModelAndView model = new ModelAndView("teacher-root");
        
        
        return model;
    
    }
    
    @RequestMapping(value = "/timetable", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        
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
    
}

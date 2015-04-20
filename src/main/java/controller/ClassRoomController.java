/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.entity.ClassRoom;
import model.entityDAO.ClassRoomDAO;
import model.entityDAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/class-room")
public class ClassRoomController {
    
    @Autowired
    private UserDAO userDao;
    
    @Autowired
    private ClassRoomDAO classRoomDao;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView root() {
    
        ModelAndView model = new ModelAndView("classroom-root");
        
        model.addObject("classrooms",classRoomDao.getAll());
        
        return model;
    
    }
    
    @RequestMapping(value="/{classRoomName}", method = RequestMethod.GET)
    public ModelAndView room(@PathVariable String classRoomName) {
    
        ModelAndView model = new ModelAndView("classroom-classroom");
        
        model.addObject("classroom",classRoomName);
        
        ClassRoom c = classRoomDao.getByName(classRoomName);
        
        if(c!= null){
        
            model.addObject("students",c.getStudents());
        
        }
        
        return model;
    
    }
    
}

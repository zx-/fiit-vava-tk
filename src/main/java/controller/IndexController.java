package controller;

import java.util.Collection;
import model.entity.User;
import model.entityDAO.UserDAO;
import java.util.List;
import model.entity.ClassRoom;
import model.entity.Role;
import model.entityDAO.ClassRoomDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    private static final Logger logger = Logger.getLogger(IndexController.class);

    @Autowired
    private UserDAO userDao;
    
    @Autowired
    private ClassRoomDAO classRoomDao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home() {
        
        Authentication authentication = SecurityContextHolder.getContext().
	                getAuthentication();
	String name = authentication.getName();

//        Role r = new Role();
//        r.setRole("admin");
//        ClassRoom c = new ClassRoom();
//        c.setName("aa class");
//        
//        
//        User u = new User();
//        u.setEmail("aaa");
//        u.setPassword("aaa");
//        u.setUsername("aaa");
//        u.setClassRoom(c);
//        u.setRole(r);
//        c.setTeacher(u);
        
//        userDao.saveOrUpdate(u);
        
        List<User> listUsers = userDao.list();
        //List<User> listUsers = new ArrayList<>();
        
//        Collection<ClassRoom> clist = classRoomDao.getByTeacher(listUsers.get(0));

        ModelAndView model = new ModelAndView("home");
        model.addObject("userList", listUsers);
        model.addObject("userAAA", name);
        model.addObject("userB", listUsers.get(0).getClassRoom().getName());
        model.addObject("userC", listUsers.get(0).getClassRoom().getTeacher());
        return model;
    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is protected page!");
        model.setViewName("admin");

        return model;

    }
    

}

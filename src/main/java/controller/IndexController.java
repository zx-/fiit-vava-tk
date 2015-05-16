package controller;

import java.util.Collection;
import model.entity.User;
import model.entityDAO.UserDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.entity.ClassRoom;
import model.entity.Role;
import model.entityDAO.ClassRoomDAO;
import model.utils.DBPopulator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import view.PDFView;

@Controller
@RequestMapping("/")
public class IndexController {

    private static final Logger logger = Logger.getLogger(IndexController.class);

    @Autowired
    private UserDAO userDao;
    
    @Autowired
    private ClassRoomDAO classRoomDao;
    
    @Autowired
    private DBPopulator populator;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home() {
        
        Authentication authentication = SecurityContextHolder.getContext().
	                getAuthentication();
        
        

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
        
        if(listUsers.isEmpty()){
        
            User u = new User();
            u.setEmail("admin@mail.com");
            u.setUsername("admin");
            u.setPassword("admin");
            Role r = new Role();
            r.setRole("ROLE_ADMIN");
            u.setRole(r);
            userDao.saveOrUpdate(u);
        
        }
        
        logger.debug("Printing student classes");
        
        for(User u:listUsers){
        
            if("ROLE_STUDENT".equals(u.getRole().getRole())){
            
                logger.debug(u.getClassRoom());
            
            }
        
        }
        
        if(authentication instanceof AnonymousAuthenticationToken){        
            
            String name = (String) authentication.getPrincipal();
            model.addObject("userAAA", name);
        
        } else {
        
            UserDetails name = (UserDetails) authentication.getPrincipal();
            model.addObject("userAAA", name);
        
        }
        
        return model;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is protected page!");
        model.setViewName("admin");

        return model;

    }
    
    @RequestMapping(value = "/admin/populate", method = RequestMethod.GET)
    public ModelAndView adminPopulate() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        
        if(populator.populate()){
        
            model.addObject("message", "Database Populated");
            
        } else {
        
            model.addObject("message", "Database was not populated");
        
        }
        
        model.setViewName("admin");

        return model;

    }
    
    @RequestMapping("/role_resolve")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        if (request.isUserInRole("ROLE_STUDENT")) {
            return "redirect:/student";
        }
        if (request.isUserInRole("ROLE_TEACHER")) {
            return "redirect:/teacher";
        }
        return "redirect:/";
    }
    

}

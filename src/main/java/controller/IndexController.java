package controller;

import java.util.ArrayList;
import model.entity.User;
import model.entityDAO.UserDAO;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    private static final Logger logger = Logger.getLogger(IndexController.class);

    @Autowired
    private UserDAO userDao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home() {

        List<User> listUsers = userDao.list();
        //List<User> listUsers = new ArrayList<>();
        User u = userDao.getUser("aaa");

        ModelAndView model = new ModelAndView("home");
        model.addObject("userList", listUsers);
        model.addObject("userAAA", u);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Robert Cuprik <robertcuprik@hotmail.com>
 */
@Controller
public class SecurityController {

    private static final Logger logger = Logger.getLogger(SecurityController.class);

    
    @RequestMapping(value = "/user-login", method = RequestMethod.GET)
    public ModelAndView loginForm() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login-error", method = RequestMethod.GET)
    public ModelAndView invalidLogin() {
        
        logger.debug("Login error in securityController called");
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("error", "Error while loging in!");
        return modelAndView;
    }

    @RequestMapping(value = "/success-login", method = RequestMethod.GET)
    public ModelAndView successLogin() {
        return new ModelAndView("admin");
    }
    
}

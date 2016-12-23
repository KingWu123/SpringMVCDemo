package com.demo.controller;

import com.demo.JMS.JMSActiveMQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kingwu on 02/12/2016.
 */
@Controller
@RequestMapping({"/", "homepage"})
public class MainController {

    @Autowired
    private JMSActiveMQ jmsActiveMQ;

    @RequestMapping(method = RequestMethod.GET)
    public String home(){

       // jmsActiveMQ.sendActivemqMessqge();

        return "home";
    }
}

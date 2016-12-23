package com.demo.controller;

import com.demo.JMS.JMSActiveMQ;
import com.demo.data.Spitter;
import com.demo.data.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

/**
 * Created by kingwu on 14/12/2016.
 */
@Controller
@RequestMapping(value = "/spitter")
public class SpitterController {

    @Autowired
    private SpitterRepository spitterRepository;

    @Autowired
    private JMSActiveMQ jmsActiveMQ;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {

        jmsActiveMQ.receiveJmsTmpMessage();

        model.addAttribute("spitter", new Spitter());

        Spitter findSpitter = spitterRepository.findOne(2);
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@RequestPart("profilePicture") MultipartFile profilePicture, @Valid Spitter spitter, Errors errors, RedirectAttributes model) {
        if (errors.hasErrors()){
            System.out.println(errors.hasErrors());
            return "registerForm";
        }

        try {
            profilePicture.transferTo(new File("" + profilePicture.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        spitterRepository.addSpitter(spitter);

        model.addAttribute("username", spitter.getUsername());
        model.addFlashAttribute("spitter", spitter);
        return "redirect:/spitter/{username}";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable("username") String userName, Model model) {
        if (model.containsAttribute("spitter")){
            Spitter spitter = (Spitter) model.asMap().get("spitter");
        }
        Spitter findSpitter = spitterRepository.findOne(12);
        return "profile";
    }


}

package com.demo.controller;

import com.demo.JMS.JMSActiveMQ;
import com.demo.data.Spittle;
import com.demo.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kingwu on 14/12/2016.
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    @Autowired
    private JMSActiveMQ jmsActiveMQ;

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository){
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Spittle> spittles(Model model,
                         @RequestParam(value = "max", defaultValue = Long.MAX_VALUE + "") long max,
                         @RequestParam(value = "count", defaultValue = "20") int count){

       // jmsActiveMQ.receiveActivemqMessage();

        jmsActiveMQ.sendJmsTmpMessage(spittleRepository.findOneSpitte());

        return spittleRepository.findSpittles(max, count);
      //  throw new RuntimeException();
     //   model.addAttribute("spittleList", spittleRepository.findSpittles(max, count));
     //   return "spittles";
    }



}

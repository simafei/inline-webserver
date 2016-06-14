package cn.simafei.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserController {
	//private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/hello")
    @ResponseBody
	public String hello(String input){
        return "Hello,World";
    }

}

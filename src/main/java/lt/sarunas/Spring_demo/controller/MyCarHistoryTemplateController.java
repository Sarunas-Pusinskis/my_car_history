package lt.sarunas.Spring_demo.controller;

import lt.sarunas.Spring_demo.service.MyCarHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/mycarhistorytemplate")
public class MyCarHistoryTemplateController {

    @Autowired
    MyCarHistoryService myCarHistoryService;

    // http://localhost:8080/mycarhistorytemplate/test
        @GetMapping(path = "/test")
        public String getTestPage(){
        return "test_page";
        }

    // http://localhost:8080/mycarhistorytemplate/home
    @GetMapping(path = "/home")
    public String getHomePage(){return "home_page";}
}

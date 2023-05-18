package lt.sarunas.Spring_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // anotacija tam, kad susijungtų su localhost:8080 -> 127.0.0.1:8080
@RequestMapping (path = "/MyCarHistory") // anotacija  //localhost:8080/MyCarHistory
public class MyCarHistoryController {

    // http://localhost:8080/MyCarHistory/test
    @GetMapping(path = "/test")
    public @ResponseBody String getTestPage(){ // anotacija @ResponseBody teksto atspausdinimui
        return "This is umpalumpa. Write something";
    }
}

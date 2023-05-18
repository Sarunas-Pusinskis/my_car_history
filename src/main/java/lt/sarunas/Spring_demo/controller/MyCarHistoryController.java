package lt.sarunas.Spring_demo.controller;

import lt.sarunas.Spring_demo.my_car_history.My_car_history;
import lt.sarunas.Spring_demo.service.MyCarHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller // anotacija tam, kad susijungtų su localhost:8080 -> 127.0.0.1:8080
@RequestMapping (path = "/MyCarHistory") // anotacija  //localhost:8080/MyCarHistory
public class MyCarHistoryController {

    @Autowired
    MyCarHistoryService myCarHistoryService;

    // http://localhost:8080/MyCarHistory/test
    @GetMapping(path = "/test")
    public @ResponseBody String getTestPage(){ // anotacija @ResponseBody teksto atspausdinimui
        return "This is umpalumpa. Write something";
    }

    // http://localhost:8080/MyCarHistory/all
    @GetMapping(path = "/all")
    public @ResponseBody List<My_car_history> getAllMycarhistory(){
        return myCarHistoryService.getAllMyCarHistory();
    }
}

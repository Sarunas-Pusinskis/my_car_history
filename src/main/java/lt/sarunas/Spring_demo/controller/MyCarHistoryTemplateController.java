package lt.sarunas.Spring_demo.controller;

import lt.sarunas.Spring_demo.my_car_history.My_car_history;
import lt.sarunas.Spring_demo.service.MyCarHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/mycarhistorytemplate")
public class MyCarHistoryTemplateController {

    @Autowired
    MyCarHistoryService myCarHistoryService;

    // http://localhost:8080/mycarhistorytemplate/test
        @GetMapping(path = "/test")
        public String getTestPage(){return "test_page";}

    // http://localhost:8080/mycarhistorytemplate/home
    @GetMapping(path = "/home")
    public String getHomePage(){return "home_page";}


    // http://localhost:8080/mycarhistorytemplate/firstpage/1
    @GetMapping(path = "/firstpage/{id}")
    public String getMyCarHistoryDetailsToFirstPage(Model model, @PathVariable int id){

        My_car_history my_car_history = myCarHistoryService.getMy_car_historyById(id);
        model.addAttribute("part_name", my_car_history.getPart_Name());
        model.addAttribute("quantity", my_car_history.getQuantity());
        model.addAttribute("part_price", my_car_history.getPart_Price());
        model.addAttribute("repair_cost", my_car_history.getRepair_Cost());
        model.addAttribute("service_name", my_car_history.getService_Name());
        model.addAttribute("date", my_car_history.getDate());
            return "/test/firstpage";
    }
}

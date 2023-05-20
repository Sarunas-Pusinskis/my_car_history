package lt.sarunas.Spring_demo.controller;

import lt.sarunas.Spring_demo.my_car_history.My_car_history;
import lt.sarunas.Spring_demo.service.MyCarHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(path = "/mycarhistory")
public class MyCarHistoryController {

    @Autowired
    MyCarHistoryService myCarHistoryService;

    // TESTAS:
    // http://localhost:8080/mycarhistory/all
    @GetMapping(path = "/all")
    public @ResponseBody List<My_car_history> getAllMyCarHistory(){
        return myCarHistoryService.getAllMyCarHistory();
    }

    // http://localhost:8080/mycarhistory/1 - > konkrečios eilutės pagal ID atvaizdavimas
    @GetMapping(path = "/{id}")
    public @ResponseBody My_car_history getMy_car_historyById (@PathVariable int id){
        return myCarHistoryService.getMy_car_historyById(id);
    }

    // http://localhost:8080/mycarhistory/test
        @GetMapping(path = "/test")
        public String getTestPage(){return "test_page";
    }

    // http://localhost:8080/mycarhistory/home
    @GetMapping(path = "/home")
    public String getHomePage(){return "home_page";
    }

    // http://localhost:8080/mycarhistory/firstpage/1
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

    // http://localhost:8080/mycarhistory/firstpage/all
    @GetMapping(path = "/firstpage/all")
    public String getAllMyCarHistoryFirstPageList (Model model){
        List<My_car_history> my_car_histories =  myCarHistoryService.getAllMyCarHistory();
        model.addAttribute("key_my_car_histories_list", my_car_histories);
        return "/test/firstpage_mycarhistory_list";
    }

    // HTML CSS styles implementation
    // http://localhost:8080/mycarhistory/mycarhistory/1
    @GetMapping(path = "/mycarhistory/{id}") //----NEVEIKIA NUORODA SU LENTELE. TAISYTI
    public  String getMyCarHistory(@PathVariable int id, Model model){
            My_car_history my_car_history = myCarHistoryService.getMy_car_historyById(id);
            model.addAttribute("key_my_car_history", my_car_history);
            return "/mycarhistory/mycarhistory_th";
    }

    // http://localhost:8080/mycarhistory/mycarhistory/all
    @GetMapping(path = "/mycarhistory/all")
    public String getAllMyCarHistory (Model model){
            List<My_car_history> my_car_histories = myCarHistoryService.getAllMyCarHistory();
            model.addAttribute("key_mycarhistories_list", my_car_histories);
            return "/mycarhistory/mycarhistories_th";
    }

    // http://localhost:8080/mycarhistory/mycarhistory/getandpost
    @RequestMapping (value = "/mycarhistory/getandpost", method = RequestMethod.GET)
    public String getMyCarHistoryByName(Model model){
            model.addAttribute("key_my_car_history", new My_car_history());
            model.addAttribute("key_my_car_history_listas", Collections.emptyList());
            return "/mycarhistory/post_get_mycarhistories_th";
    }

    // ----------------NEPAVYKSTA PALEISTI FUNKCIONALUMO, KAD SUVEDUS Į PAIEŠKĄ PVZ "OIL", IŠMESTŲ VISKĄ KĄ RANDA SU OIL.
   /* @RequestMapping (value = "/mycarhistory/getandpost", method = RequestMethod.POST)
    public String postMyCarHistoryByNameLike(Model model, @ModelAttribute(value = "key_my_car_history") My_car_history my_car_history){
            List<My_car_history> my_car_histories = myCarHistoryService.getAllMyCarHistory("%" + my_car_history.getPart_Name() + "%");
            model.addAttribute("key_my_car_history_listas", Collections.emptyList());
            return "/mycarhistory/post_get_mycarhistories_th";
    }*/
}

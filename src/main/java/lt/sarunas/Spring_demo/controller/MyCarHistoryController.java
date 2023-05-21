package lt.sarunas.Spring_demo.controller;

import lt.sarunas.Spring_demo.my_car_history.CarHistory;
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
    public @ResponseBody List<CarHistory> getAllMyCarHistory(){
        return myCarHistoryService.getAllMyCarHistory();
    }

    // http://localhost:8080/mycarhistory/1 - > konkrečios eilutės pagal ID atvaizdavimas
    @GetMapping(path = "/{id}")
    public @ResponseBody CarHistory getMy_car_historyById (@PathVariable int id){
        return myCarHistoryService.getMy_car_historyById(id);
    }

    // http://localhost:8080/mycarhistory/customised/1 - ----------nepaigta
//    @GetMapping(path = "/customised/{id}")
//    public @ResponseBody CarHistory getMyCarHistoryByIdCustomised (@PathVariable int id){
//        return myCarHistoryService.getMyCarHistoryByIdCustomised(id);
//    }

    // http://localhost:8080/mycarhistory/test
    @GetMapping(path = "/test")
    public String getTestPage(){
        return "test_page";
    }

    // MAIN HOME PAGE LINK:
    // http://localhost:8080/mycarhistory/home
    @GetMapping(path = "/home")
    public String getHomePage(){return "home_page";
    }

    // http://localhost:8080/mycarhistory/firstpage/1
    @GetMapping(path = "/firstpage/{id}")
    public String getMyCarHistoryDetailsToFirstPage(Model model, @PathVariable int id){

        CarHistory carHistory = myCarHistoryService.getMy_car_historyById(id);
        model.addAttribute("part_name", carHistory.getPart_Name());
        model.addAttribute("quantity", carHistory.getQuantity());
        model.addAttribute("part_price", carHistory.getPart_Price());
        model.addAttribute("repair_cost", carHistory.getRepair_Cost());
        model.addAttribute("service_name", carHistory.getService_Name());
        model.addAttribute("date", carHistory.getDate());
            return "/test/firstpage";
    }

    // http://localhost:8080/mycarhistory/firstpage/all
    @GetMapping(path = "/firstpage/all")
    public String getAllMyCarHistoryFirstPageList (Model model){
        List<CarHistory> my_car_histories =  myCarHistoryService.getAllMyCarHistory();
        model.addAttribute("key_my_car_histories_list", my_car_histories);
        return "/test/firstpage_mycarhistory_list";
    }

    // HTML CSS styles implementation
    // http://localhost:8080/mycarhistory/mycarhistory/1
    @GetMapping(path = "/mycarhistory/{id}") //----NEVEIKIA NUORODA SU LENTELE. TAISYTI
    public  String getMyCarHistory(@PathVariable int id, Model model){
            CarHistory carHistory = myCarHistoryService.getMy_car_historyById(id);
            model.addAttribute("myCarHistory", carHistory);
            return "/mycarhistory/mycarhistory_th";
    }

    // http://localhost:8080/mycarhistory/mycarhistory/all
    @GetMapping(path = "/mycarhistory/all")
    public String getAllMyCarHistory (Model model){
            List<CarHistory> my_car_histories = myCarHistoryService.getAllMyCarHistory();
            model.addAttribute("key_mycarhistories_list", my_car_histories);
            return "/mycarhistory/mycarhistories_th";
    }

    // http://localhost:8080/mycarhistory/getandpost
    @GetMapping(value = "/mycarhistory/getandpost")
    public String getMyCarHistoryByName(Model model) {
        model.addAttribute("myCarHistory", new CarHistory());
        model.addAttribute("myCarHistories", Collections.emptyList());
        return "/mycarhistory/post_get_mycarhistories_th";
    }

    // ----------------NEPAVYKSTA PALEISTI FUNKCIONALUMO, KAD SUVEDUS Į PAIEŠKĄ PVZ "OIL", IŠMESTŲ VISKĄ KĄ RANDA SU OIL.
    @PostMapping(value = "/mycarhistory/getandpost")
    public String postMyCarHistoryByNameLike(Model model, @ModelAttribute(value = "myCarHistory") CarHistory carHistory) {
        List<CarHistory> myCarHistories = myCarHistoryService.getMyCarHistoryByNameLike(carHistory.getPart_Name());
        model.addAttribute("myCarHistories", myCarHistories);
        return "/mycarhistory/post_get_mycarhistories_th";
    }

    // http://localhost:8080/mycarhistory/name/oil
//    @GetMapping(path = "/name/{name}")
//    public @ResponseBody List<CarHistory> getMyCarHistoryByNameLike(@PathVariable String name){
//        return myCarHistoryService.getMyCarHistoryByNameLike("%" + name + "%");
//        //SELECT * FROM my_car_history WHERE Part_Name Like 'double'
//    }

    // http://localhost:8080/mycarhistory/name/query/double
   // @GetMapping(path = "/name/query/double")
   // public @ResponseBody List<CarHistory> getMyCarHistoriesByQueryLike(@PathVariable String name){

    //}
}

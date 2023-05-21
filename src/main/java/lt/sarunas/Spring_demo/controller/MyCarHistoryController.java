package lt.sarunas.Spring_demo.controller;

import lt.sarunas.Spring_demo.model.CarExpenses;
import lt.sarunas.Spring_demo.repository.entities.CarHistory;
import lt.sarunas.Spring_demo.service.MyCarHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // MAIN HOME PAGE LINK:
    // http://localhost:8080/mycarhistory/home
    @GetMapping(path = "/home")
    public String getHomePage() {
        return "home_page";
    }

    // http://localhost:8080/mycarhistory/mycarhistory/1
    @GetMapping(path = "/mycarhistory/{id}")
    public String getMyCarHistory(@PathVariable int id, Model model) {
        CarHistory carHistory = myCarHistoryService.getMy_car_historyById(id);
        model.addAttribute("myCarHistory", carHistory);
        return "/mycarhistory/mycarhistory_th";
    }

    // http://localhost:8080/mycarhistory/mycarhistory/all1
    @GetMapping(path = "/mycarhistory/all1")
    public String getAllMyCarHistory(Model model) {
        List<CarHistory> allMyCarHistory = myCarHistoryService.getAllMyCarHistory();
        model.addAttribute("myCarHistories", allMyCarHistory);
        return "/mycarhistory/mycarhistories_th";
    }

    // http://localhost:8080/mycarhistory/mycarhistory/getandpost
    @GetMapping(value = "/mycarhistory/getandpost")
    public String getMyCarHistoryByName(Model model) {
        model.addAttribute("myCarHistory", new CarHistory());
        model.addAttribute("myCarHistories", Collections.emptyList());
        return "/mycarhistory/post_get_mycarhistories_th";
    }

    @PostMapping(value = "/mycarhistory/getandpost")
    public String postMyCarHistoryByNameLike(Model model, @ModelAttribute(value = "myCarHistory") CarHistory carHistory) {
        List<CarHistory> myCarHistories = myCarHistoryService.getMyCarHistoryByNameLike(carHistory.getPart_Name());
        model.addAttribute("myCarHistories", myCarHistories);
        return "/mycarhistory/post_get_mycarhistories_th";
    }

    // http://localhost:8080/mycarhistory/mycarhistory/expenses
    @GetMapping(value = "/mycarhistory/expenses")
    public String getCarExpenses(Model model) {
        CarExpenses carExpenses = myCarHistoryService.getMyCarExpenses();
        model.addAttribute("carExpenses", carExpenses);
        return "/mycarhistory/carExpenses";
    }

    @PostMapping(value = "/mycarhistory/add-new-expenses")
    public String addNewExpenses(Model model, @ModelAttribute(value = "myCarHistory") CarHistory carHistory) {
        myCarHistoryService.insertNewExpense(carHistory);
        List<CarHistory> allMyCarHistory = myCarHistoryService.getAllMyCarHistory();
        model.addAttribute("myCarHistories", allMyCarHistory);
        model.addAttribute("myCarHistory", new CarHistory());
        return "/mycarhistory/add_mycarhistories_th";
    }

    @DeleteMapping(value = "/mycarhistory/delete-expenses")
    public String deleteExpenses(Model model, @ModelAttribute(value = "myCarHistory") CarHistory carHistory) {
        myCarHistoryService.deleteHistoryEntry(carHistory.getId());
        List<CarHistory> allMyCarHistory = myCarHistoryService.getAllMyCarHistory();
        model.addAttribute("myCarHistories", allMyCarHistory);
        model.addAttribute("myCarHistory", new CarHistory());
        return "/mycarhistory/add_mycarhistories_th";
    }

    // http://localhost:8080/mycarhistory/mycarhistory/all
    @GetMapping(path = "/mycarhistory/all")
    public String getAllExpenses(Model model) {
        List<CarHistory> allMyCarHistory = myCarHistoryService.getAllMyCarHistory();
        model.addAttribute("myCarHistories", allMyCarHistory);
        model.addAttribute("myCarHistory", new CarHistory());
        return "/mycarhistory/add_mycarhistories_th";
    }
}

package lt.sarunas.Car.controller;

import lt.sarunas.Car.model.CarExpenses;
import lt.sarunas.Car.repository.entities.CarHistory;
import lt.sarunas.Car.service.MyCarHistoryService;
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

    // MAIN HOME PAGE LINK:
    // http://localhost:8080/mycarhistory/home
    @GetMapping(path = "/home")
    public String getHomePage() {
        return "home_page";
    }

    // http://localhost:8080/mycarhistory/1
    @GetMapping(path = "/{id}")
    public String getMyCarHistory(@PathVariable int id, Model model) {
        CarHistory carHistory = myCarHistoryService.getMy_car_historyById(id);
        model.addAttribute("myCarHistory", carHistory);
        return "/mycarhistory/mycarhistory_th";
    }

    // http://localhost:8080/mycarhistory/all1
    @GetMapping(path = "/all1")
    public String getAllMyCarHistory(Model model) {
        List<CarHistory> allMyCarHistory = myCarHistoryService.getAllMyCarHistory();
        model.addAttribute("myCarHistories", allMyCarHistory);
        return "/mycarhistory/mycarhistories_th";
    }

    // http://localhost:8080/mycarhistory/getandpost
    @GetMapping(value = "/getandpost")
    public String getMyCarHistoryByName(Model model) {
        model.addAttribute("myCarHistory", new CarHistory());
        model.addAttribute("myCarHistories", Collections.emptyList());
        return "/mycarhistory/post_get_mycarhistories_th";
    }

    @PostMapping(value = "/getandpost")
    public String postMyCarHistoryByNameLike(Model model, @ModelAttribute(value = "myCarHistory") CarHistory carHistory) {
        List<CarHistory> myCarHistories = myCarHistoryService.getMyCarHistoryByNameLike(carHistory.getPart_Name());
        model.addAttribute("myCarHistories", myCarHistories);
        return "/mycarhistory/post_get_mycarhistories_th";
    }

    // http://localhost:8080/mycarhistory/expenses
    @GetMapping(value = "/expenses")
    public String getCarExpenses(Model model) {
        CarExpenses carExpenses = myCarHistoryService.getMyCarExpenses();
        model.addAttribute("carExpenses", carExpenses);
        return "/mycarhistory/carExpenses";
    }

    @PostMapping(value = "/add-new-expenses")
    public String addNewExpenses(Model model, @ModelAttribute(value = "myCarHistory") CarHistory carHistory) {
        myCarHistoryService.insertNewExpense(carHistory);
        List<CarHistory> allMyCarHistory = myCarHistoryService.getAllMyCarHistory();
        model.addAttribute("myCarHistories", allMyCarHistory);
        model.addAttribute("myCarHistory", new CarHistory());
        return "/mycarhistory/add_mycarhistories_th";
    }

    // http://localhost:8080/mycarhistory/delete-expenses
    @PostMapping(value = "/delete-expenses")
    public String deleteExpenses(Model model, @ModelAttribute(value = "myCarHistory") CarHistory carHistory) {
        myCarHistoryService.deleteHistoryEntry(carHistory.getId());
        List<CarHistory> allMyCarHistory = myCarHistoryService.getAllMyCarHistory();
        model.addAttribute("myCarHistories", allMyCarHistory);
        model.addAttribute("myCarHistory", new CarHistory());
        return "/mycarhistory/add_mycarhistories_th";
    }

    // http://localhost:8080/mycarhistory/all
    @GetMapping(path = "/all")
    public String getAllExpenses(Model model) {
        List<CarHistory> allMyCarHistory = myCarHistoryService.getAllMyCarHistory();
        model.addAttribute("myCarHistories", allMyCarHistory);
        model.addAttribute("myCarHistory", new CarHistory());
        return "/mycarhistory/add_mycarhistories_th";
    }

    // http://localhost:8080/mycarhistory/search
    @GetMapping(value = "/search")
    public String showSearchForm(Model model) {
        model.addAttribute("myCarHistory", new CarHistory());
        return "/mycarhistory/update_mycarhistory_th";
    }

    @PostMapping(value = "/search")
    public String searchByNumber(Model model, @RequestParam("id") int searchNumber) {
        CarHistory carHistory = myCarHistoryService.getMy_car_historyById(searchNumber);
        if (carHistory != null) {
            model.addAttribute("myCarHistory", carHistory);
        } else {
            model.addAttribute("myCarHistory", new CarHistory());
        }
        return "/mycarhistory/update_mycarhistory_th";
    }

    // http://localhost:8080/mycarhistory/update/{id}
    @GetMapping(path = "/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        CarHistory carHistory = myCarHistoryService.getMy_car_historyById(id);
        model.addAttribute("myCarHistory", carHistory);
        return "/mycarhistory/update_mycarhistory_th";
    }

    @PostMapping(value = "/update/{id}")
    public String updateExpenses(@PathVariable int id, @ModelAttribute("myCarHistory") CarHistory updatedCarHistory, Model model) {
        CarHistory existingCarHistory = myCarHistoryService.getMy_car_historyById(id);
        if (existingCarHistory != null) {
            existingCarHistory.setPart_Name(updatedCarHistory.getPart_Name());
            existingCarHistory.setQuantity(updatedCarHistory.getQuantity());
            existingCarHistory.setPart_Price(updatedCarHistory.getPart_Price());
            existingCarHistory.setRepair_Cost(updatedCarHistory.getRepair_Cost());
            existingCarHistory.setService_Name(updatedCarHistory.getService_Name());
            existingCarHistory.setDate(updatedCarHistory.getDate());
            myCarHistoryService.updateHistoryEntry(existingCarHistory);
        }
        model.addAttribute("message", "Data records updated successfully!");
        return "/mycarhistory/update_mycarhistory_th";
    }
}

package lt.sarunas.Spring_demo.service;

import lt.sarunas.Spring_demo.model.CarExpenses;
import lt.sarunas.Spring_demo.repository.entities.CarHistory;
import lt.sarunas.Spring_demo.repository.CarHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyCarHistoryService {

    @Autowired
    private CarHistoryRepository myCarHistoryRepository;

    public List<CarHistory> getAllMyCarHistory() {

        return (List<CarHistory>) myCarHistoryRepository.findAll();
    }

    public CarHistory getMy_car_historyById(int id) {

        /*try {
            return myCarHistoryRepository.findById(id).get();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        } */ // 2 variantas su catch metodu

        if (myCarHistoryRepository.findById(id).isPresent()) {
            return myCarHistoryRepository.findById(id).get();
        }
        return new CarHistory("Info do not exist by this id: " + id);
    }

    public List<CarHistory> getMyCarHistoryByNameLike(String name) {
        return myCarHistoryRepository.getMyCarHistoryQueryNameLike("%" + name + "%");
    }

    public void insertNewExpense(CarHistory carHistory) {
        myCarHistoryRepository.save(carHistory);
    }

    public void deleteHistoryEntry(int id) {
        myCarHistoryRepository.deleteById(id);
    }

    public CarExpenses getMyCarExpenses() {
        List<CarHistory> allHistory = getAllMyCarHistory();
        CarExpenses expenses = new CarExpenses();

        double totalPartsPrice = 0;
        double totalRepairPrice = 0;

        for (CarHistory history : allHistory) {
            totalPartsPrice = totalPartsPrice + (history.getPart_Price() * history.getQuantity());
            totalRepairPrice = totalRepairPrice + history.getRepair_Cost();
        }
        expenses.setTotalPartsPrice(totalPartsPrice);
        expenses.setTotalRepairPrice(totalRepairPrice);
        return expenses;
    }

    public void updateHistoryEntry(CarHistory carHistory) {
        myCarHistoryRepository.save(carHistory);
    }
}

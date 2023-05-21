package lt.sarunas.Spring_demo.service;

import lt.sarunas.Spring_demo.my_car_history.CarHistory;
import lt.sarunas.Spring_demo.my_car_history.CarHistoryRepository;
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

        if (myCarHistoryRepository.findById(id).isPresent()){
            return myCarHistoryRepository.findById(id).get();
        }
        return new CarHistory("Info do not exist by this id: " + id);
    }

    public List<CarHistory> getMyCarHistoryByNameLike(String name) {
        return myCarHistoryRepository.getMyCarHistoryQueryNameLike("%" + name + "%");
    }
}

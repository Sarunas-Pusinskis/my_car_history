package lt.sarunas.Spring_demo.service;

import lt.sarunas.Spring_demo.my_car_history.My_car_history;
import lt.sarunas.Spring_demo.my_car_history.My_car_history_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MyCarHistoryService {

    @Autowired
    private My_car_history_Repository myCarHistoryRepository;

    public List<My_car_history> getAllMyCarHistory() {

        return (List<My_car_history>) myCarHistoryRepository.findAll();
    }

    public My_car_history getMy_car_historyById(int id) {

        /*try {
            return myCarHistoryRepository.findById(id).get();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        } */ // 2 variantas su catch metodu

        if (myCarHistoryRepository.findById(id).isPresent()){
            return myCarHistoryRepository.findById(id).get();
        }
        return new My_car_history("Info do not exist by this id: " + id);
    }
}

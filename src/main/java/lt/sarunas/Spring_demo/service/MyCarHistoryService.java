package lt.sarunas.Spring_demo.service;

import lt.sarunas.Spring_demo.my_car_history.My_car_history;
import lt.sarunas.Spring_demo.my_car_history.My_car_history_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyCarHistoryService {

    @Autowired
    private My_car_history_Repository myCarHistoryRepository;

    public List<My_car_history> getAllMyCarHistory() {
        return (List<My_car_history>) myCarHistoryRepository.findAll();
    }
}

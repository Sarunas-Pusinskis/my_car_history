package lt.sarunas.Spring_demo.bootstrap;

import lt.sarunas.Spring_demo.my_car_history.My_car_history_Repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData  implements CommandLineRunner {

    private My_car_history_Repository myCarHistoryRepository;
    public BootStrapData(My_car_history_Repository myCarHistoryRepository){
        this.myCarHistoryRepository = myCarHistoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println();
        myCarHistoryRepository.findAll().forEach(System.out::println); // visi įrašai
        System.out.println("\n================\n");
        System.out.println(myCarHistoryRepository.findById(1).get() + "\n"); // randa konkretų įrašą
        System.out.println(myCarHistoryRepository.count()); // rodo kiek yra įrašų
    }
}

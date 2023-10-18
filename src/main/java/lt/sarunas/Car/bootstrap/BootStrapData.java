package lt.sarunas.Car.bootstrap;

import lt.sarunas.Car.repository.CarHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    @Autowired
    private CarHistoryRepository myCarHistoryRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println();
        myCarHistoryRepository.findAll().forEach(System.out::println); // visi įrašai
    }
}

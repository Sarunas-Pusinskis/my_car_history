package lt.sarunas.Spring_demo.repository;

import lt.sarunas.Spring_demo.repository.entities.CarHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarHistoryRepository extends CrudRepository<CarHistory, Integer> {
    @Query(value = "SELECT * FROM my_car_history WHERE part_name LIKE :name", nativeQuery = true)
    List<CarHistory> getMyCarHistoryQueryNameLike(@Param("name") String myCarHistoryName);
}

package lt.sarunas.Spring_demo.my_car_history;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarHistoryRepository extends CrudRepository<CarHistory, Integer> {

//    Optional<CarHistory> findByMyCarHistoryNumber(int id);

//   Iterable<CarHistory> findByPart_NameContaining(String name);

    @Query (value = "SELECT * FROM my_car_history WHERE part_name LIKE :name", nativeQuery = true)
    List<CarHistory> getMyCarHistoryQueryNameLike(@Param("name") String myCarHistoryName);
}

package lt.sarunas.Car.service;

import lt.sarunas.Car.repository.entities.CarHistory;
import lt.sarunas.Car.repository.CarHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MyCarHistoryServiceTest {

    @Mock
    private CarHistoryRepository carHistoryRepository;

    private MyCarHistoryService myCarHistoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        myCarHistoryService = new MyCarHistoryService();
        myCarHistoryService.myCarHistoryRepository = carHistoryRepository;
    }

    @Test
    public void testGetAllMyCarHistory() {
        List<CarHistory> carHistories = new ArrayList<>();
        when(carHistoryRepository.findAll()).thenReturn(carHistories);

        List<CarHistory> result = myCarHistoryService.getAllMyCarHistory();
        assertEquals(carHistories, result);
    }

    @Test
    public void testGetMy_car_historyById() {
        int id = 1;
        CarHistory carHistory = new CarHistory();
        when(carHistoryRepository.findById(id)).thenReturn(Optional.of(carHistory));

        CarHistory result = myCarHistoryService.getMy_car_historyById(id);
        assertEquals(carHistory, result);
    }

    @Test
    public void testGetMy_car_historyById_NotFound() {
        int id = 1;
        when(carHistoryRepository.findById(id)).thenReturn(Optional.empty());

        CarHistory result = myCarHistoryService.getMy_car_historyById(id);
        assertEquals("Info do not exist by this id: " + id, result.getPart_Name());
    }

    @Test
    public void testGetMyCarHistoryByNameLike() {
        String name = "part";
        List<CarHistory> carHistories = new ArrayList<>();
        when(carHistoryRepository.getMyCarHistoryQueryNameLike("%" + name + "%")).thenReturn(carHistories);

        List<CarHistory> result = myCarHistoryService.getMyCarHistoryByNameLike(name);
        assertEquals(carHistories, result);
    }

    @Test
    public void testInsertNewExpense() {
        CarHistory carHistory = new CarHistory();
        myCarHistoryService.insertNewExpense(carHistory);
        verify(carHistoryRepository).save(carHistory);
    }

    @Test
    public void testDeleteHistoryEntry() {
        int id = 1;
        myCarHistoryService.deleteHistoryEntry(id);
        verify(carHistoryRepository).deleteById(id);
    }

    @Test
    public void testUpdateHistoryEntry() {
        CarHistory carHistory = new CarHistory();
        myCarHistoryService.updateHistoryEntry(carHistory);
        verify(carHistoryRepository).save(carHistory);
    }
}

package lt.sarunas.Car.controller;

import lt.sarunas.Car.model.CarExpenses;
import lt.sarunas.Car.repository.entities.CarHistory;
import lt.sarunas.Car.service.MyCarHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MyCarHistoryControllerTest {

    @Mock
    private MyCarHistoryService myCarHistoryService;

    @Mock
    private Model model;

    private MyCarHistoryController myCarHistoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        myCarHistoryController = new MyCarHistoryController();
        myCarHistoryController.myCarHistoryService = myCarHistoryService;
    }

    @Test
    public void testGetHomePage() {
        String viewName = myCarHistoryController.getHomePage();
        assertEquals("home_page", viewName);
    }

    @Test
    public void testGetMyCarHistory() {
        int id = 1;
        CarHistory carHistory = new CarHistory();
        when(myCarHistoryService.getMy_car_historyById(id)).thenReturn(carHistory);

        String viewName = myCarHistoryController.getMyCarHistory(id, model);
        assertEquals("/mycarhistory/mycarhistory_th", viewName);
        verify(myCarHistoryService).getMy_car_historyById(id);
        verify(model).addAttribute("myCarHistory", carHistory);
    }

    @Test
    public void testGetAllMyCarHistory() {
        List<CarHistory> carHistories = new ArrayList<>();
        when(myCarHistoryService.getAllMyCarHistory()).thenReturn(carHistories);

        String viewName = myCarHistoryController.getAllMyCarHistory(model);
        assertEquals("/mycarhistory/mycarhistories_th", viewName);
        verify(myCarHistoryService).getAllMyCarHistory();
        verify(model).addAttribute("myCarHistories", carHistories);
    }

    @Test
    public void testGetMyCarHistoryByName() {
        MyCarHistoryController myCarHistoryController = new MyCarHistoryController();
        Model model = Mockito.mock(Model.class);
        CarHistory carHistory = new CarHistory();
        List<CarHistory> carHistories = Collections.emptyList();

        when(model.addAttribute("myCarHistory", carHistory)).thenReturn(model);
        when(model.addAttribute("myCarHistories", carHistories)).thenReturn(model);

        String viewName = myCarHistoryController.getMyCarHistoryByName(model);

        verify(model).addAttribute("myCarHistory", carHistory);
        verify(model).addAttribute("myCarHistories", carHistories);
        assertEquals("/mycarhistory/post_get_mycarhistories_th", viewName);
    }

    @Test
    public void testPostMyCarHistoryByNameLike() {
        MyCarHistoryController myCarHistoryController = new MyCarHistoryController();
        Model model = Mockito.mock(Model.class);

        CarHistory carHistory = new CarHistory();
        carHistory.setPart_Name("TestPartName");
        List<CarHistory> carHistories = new ArrayList<>();

        MyCarHistoryService myCarHistoryService = Mockito.mock(MyCarHistoryService.class);
        when(myCarHistoryService.getMyCarHistoryByNameLike("TestPartName")).thenReturn(carHistories);

        myCarHistoryController.myCarHistoryService = myCarHistoryService;

        String viewName = myCarHistoryController.postMyCarHistoryByNameLike(model, carHistory);

        verify(myCarHistoryService).getMyCarHistoryByNameLike("TestPartName");
        verify(model).addAttribute("myCarHistories", carHistories);
        assertEquals("/mycarhistory/post_get_mycarhistories_th", viewName);
    }

    @Test
    public void testGetCarExpenses() {
        CarExpenses carExpenses = new CarExpenses();
        when(myCarHistoryService.getMyCarExpenses()).thenReturn(carExpenses);

        String viewName = myCarHistoryController.getCarExpenses(model);
        assertEquals("/mycarhistory/carExpenses", viewName);
        verify(myCarHistoryService).getMyCarExpenses();
        verify(model).addAttribute("carExpenses", carExpenses);
    }

    @Test
    public void testAddNewExpenses() {
        CarHistory carHistory = new CarHistory();

        doNothing().when(myCarHistoryService).insertNewExpense(carHistory);
        when(myCarHistoryService.getAllMyCarHistory()).thenReturn(new ArrayList<>());

        String viewName = myCarHistoryController.addNewExpenses(model, carHistory);
        assertEquals("/mycarhistory/add_mycarhistories_th", viewName);

        verify(myCarHistoryService).insertNewExpense(carHistory);
        verify(myCarHistoryService).getAllMyCarHistory();
        verify(model).addAttribute("myCarHistories", new ArrayList<>());
        verify(model).addAttribute("myCarHistory", new CarHistory());
    }

    @Test
    public void testDeleteExpenses() {
        int id = 1;
        CarHistory carHistory = new CarHistory();
        carHistory.setId(id);

        when(myCarHistoryService.getMy_car_historyById(id)).thenReturn(carHistory);
        when(myCarHistoryService.getAllMyCarHistory()).thenReturn(new ArrayList<>());

        String viewName = myCarHistoryController.deleteExpenses(model, carHistory);
        assertEquals("/mycarhistory/add_mycarhistories_th", viewName);
        verify(myCarHistoryService).deleteHistoryEntry(carHistory.getId());
        verify(myCarHistoryService).getAllMyCarHistory();
        verify(model).addAttribute("myCarHistories", new ArrayList<>());
        verify(model).addAttribute("myCarHistory", new CarHistory());
    }

    @Test
    public void testGetAllExpenses() {
        MyCarHistoryController myCarHistoryController = new MyCarHistoryController();
        Model model = Mockito.mock(Model.class);

        List<CarHistory> carHistories = new ArrayList<>();

        MyCarHistoryService myCarHistoryService = Mockito.mock(MyCarHistoryService.class);
        when(myCarHistoryService.getAllMyCarHistory()).thenReturn(carHistories);

        myCarHistoryController.myCarHistoryService = myCarHistoryService;
        String viewName = myCarHistoryController.getAllExpenses(model);

        verify(myCarHistoryService).getAllMyCarHistory();
        verify(model).addAttribute("myCarHistories", carHistories);
        verify(model).addAttribute("myCarHistory", new CarHistory());
        assertEquals("/mycarhistory/add_mycarhistories_th", viewName);
    }

    @Test
    public void testShowSearchForm() {
        String viewName = myCarHistoryController.showSearchForm(model);
        assertEquals("/mycarhistory/update_mycarhistory_th", viewName);
        verify(model).addAttribute("myCarHistory", new CarHistory());
    }

    @Test
    public void testSearchByNumber() {
        int searchNumber = 1;
        CarHistory carHistory = new CarHistory();
        when(myCarHistoryService.getMy_car_historyById(searchNumber)).thenReturn(carHistory);

        String viewName = myCarHistoryController.searchByNumber(model, searchNumber);
        assertEquals("/mycarhistory/update_mycarhistory_th", viewName);
        verify(myCarHistoryService).getMy_car_historyById(searchNumber);
        verify(model).addAttribute("myCarHistory", carHistory);
    }

    @Test
    public void testShowUpdateForm() {
        int id = 1;
        CarHistory carHistory = new CarHistory();
        when(myCarHistoryService.getMy_car_historyById(id)).thenReturn(carHistory);

        String viewName = myCarHistoryController.showUpdateForm(id, model);
        assertEquals("/mycarhistory/update_mycarhistory_th", viewName);
        verify(myCarHistoryService).getMy_car_historyById(id);
        verify(model).addAttribute("myCarHistory", carHistory);
    }

    @Test
    public void testUpdateExpenses() {
        int id = 1;
        CarHistory carHistory = new CarHistory();
        CarHistory updatedCarHistory = new CarHistory();
        when(myCarHistoryService.getMy_car_historyById(id)).thenReturn(carHistory);

        String viewName = myCarHistoryController.updateExpenses(id, updatedCarHistory, model);
        assertEquals("/mycarhistory/update_mycarhistory_th", viewName);
        verify(myCarHistoryService).getMy_car_historyById(id);
        verify(myCarHistoryService).updateHistoryEntry(carHistory);
        verify(model).addAttribute("message", "Data records updated successfully!");
    }
}

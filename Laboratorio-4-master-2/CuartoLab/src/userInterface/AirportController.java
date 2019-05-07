package userInterface;

import java.text.ParseException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Airport;
import model.Flight;

public class AirportController {

	Airport airport;
	
    @FXML
    private TableView<Flight> tableFlights;
    @FXML
    private TableColumn<Flight, String> tcTime;
    @FXML
    private TableColumn<Flight, String> tcAirline;
    @FXML
    private TableColumn<Flight, String> tcFlight;
    @FXML
    private TableColumn<Flight, String> tcTo;
    @FXML
    private TableColumn<Flight, Integer> tcGate;
    @FXML
    private TableColumn<Flight, String> tcDate;
    @FXML
    private Menu menuSearch;
    @FXML
    private MenuItem searchTime;
    @FXML
    private MenuItem searchAirline;
    @FXML
    private MenuItem searchFlight;
    @FXML
    private MenuItem searchTo;
    @FXML
    private MenuItem searchGate;
    @FXML
    private MenuItem searchDate;
    @FXML
    private TextField tfSearch;
    @FXML
    private TextField txt2;

    @FXML
    private Button btSearch;

    public void initialize() throws ParseException {
    	airport = new Airport();
    	tcTime.setCellValueFactory(new PropertyValueFactory<>("time"));
		tcAirline.setCellValueFactory(new PropertyValueFactory<>("airline"));
		tcFlight.setCellValueFactory(new PropertyValueFactory<>("flight"));
		tcTo.setCellValueFactory(new PropertyValueFactory<>("to"));
		tcGate.setCellValueFactory(new PropertyValueFactory<>("gate"));
		tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		tableFlights.setItems(airport.getFlights());
    }
    @FXML
    public void createFlights(ActionEvent event) throws ParseException {
    	int size = Integer.parseInt(txt2.getText());
    	airport.createFlights(size);
    	tableFlights.setItems(airport.getFlights());
    }
    
    @FXML
    public void SearchFlight(ActionEvent event) throws NullPointerException {
    	try {
    	String search = tfSearch.getText();
    	if(!search.equals("")) {
    		airport.searchByAirline(search);
    		tableFlights.setItems(airport.getFlights());
    	}else {
    		tfSearch.requestFocus();
    	}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    @FXML
    public void SearchTime(ActionEvent event) throws NullPointerException {
    	try {
    	String search = tfSearch.getText();
    	if(!search.equals("")) {
    		airport.searchBYTime(search);
    		tableFlights.setItems(airport.getFlights());
    	}else {
    		tfSearch.requestFocus();
    	}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    public void orderAirline(ActionEvent event) {
    	airport.orderByBrandName();
    	tableFlights.setItems(airport.getFlights());
    }

    @FXML
    public void orderDate(ActionEvent event) {
    	airport.orderByDate();
    	tableFlights.setItems(airport.getFlights());
    }

    @FXML
    public void orderFlight(ActionEvent event) {
    	airport.orderByFlight();
    	tableFlights.setItems(airport.getFlights());
    }
    @FXML
    public void orderGate(ActionEvent event) {
    	airport.orderByGate();
    	tableFlights.setItems(airport.getFlights());
    }

    @FXML
    public void orderTo(ActionEvent event) {
    	airport.orderByTo();
    	tableFlights.setItems(airport.getFlights());
    }
    @FXML
    public void orderFlights(ActionEvent event) {
    	airport.orderByTime();
    	tableFlights.setItems(airport.getFlights());
    }

}

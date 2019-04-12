package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Airport {
	private ObservableList<Flight> flights;
	private ArrayList<String> airlines, to, dates, times;
	private int[] gates;

	public Airport() {
		this.flights = FXCollections.observableArrayList();
		airlines = new ArrayList<>();
		to = new ArrayList<>();
		dates = new ArrayList<>();
		times = new ArrayList<>();
		gates = new int[50];
		generateGatesNumbers();
		loadFligths();
	}

	public ObservableList<Flight> getFlights() {
		return flights;
	}

	public void setFlights(ObservableList<Flight> fli) {
		this.flights = fli;
	}

	public void loadFligths() {
		String path = "";
		int line = 0;
		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				path = "data/airlines.txt";
				line = 0;
			} else if (i == 1) {
				path = "data/destinations.txt";
				line = 1;
			} else if (i == 2) {
				path = "data/dates.txt";
				line = 2;
			} else if (i == 3) {
				path = "data/times.txt";
				line = 3;
			}

			BufferedReader br = null;
			FileReader fr = null;
			try {
				fr = new FileReader(path);
				br = new BufferedReader(fr);

				String sCurrentLine;

				while ((sCurrentLine = br.readLine()) != null) {
					String[] parts = sCurrentLine.split(",");
					for (int j = 0; j < parts.length; j++) {
						if (line == 0)
							airlines.add(parts[j]);
						if (line == 1)
							to.add(parts[j]);
						if (line == 2)
							dates.add(parts[j]);
						if (line == 3)
							times.add(parts[j]);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();

					if (fr != null)
						fr.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public void createFlights(int size) {
		for (int i = 0; i < size; i++) {
			Flight fg = new Flight(times.get((int) Math.floor(Math.random() * airlines.size())), airlines.get((int) Math.floor(Math.random() * airlines.size())), generateFlightsNumber(),
					to.get((int) Math.floor(Math.random() * to.size())), dates.get((int) Math.floor(Math.random() * dates.size())),gates[(int) Math.floor(Math.random() * 50)]);
			flights.add(fg);
		}
	}
	
	public String generateFlightsNumber() {
		String msg="";
		int counter=0;
		int poop=0;
		for(int i=0; i<flights.size();i++) {
			counter++;
			poop = (int) Math.floor(Math.random() * airlines.size());
			msg = "AV"+counter+" "+counter+poop;
		}
		return msg;
	}
	
	public void generateGatesNumbers() {
		for (int i = 0; i < gates.length; i++) {
			gates[i] = i;
		}
	}
	
	// bubble method
	public void orderByBrandName() {
		for (int i = flights.size(); i > 0; i--) {
			for (int j = 0; j < i - 1; j++) {
				Flight f1 = (Flight) flights.get(j);
				Flight f2 = (Flight) flights.get(j + 1);

				if (f1.compareByBrandName(f2) > 0) {
					flights.set(j, f2);
					flights.set(j + 1, f1);
				}
			}
		}
	}
	// insertion method
	public void orderByTime() {
		for(int i=1;i<flights.size();i++) {
			Flight toIterate = (Flight) flights.get(i);
			boolean finished =false;
			
			for(int j=i;j>0 && !finished;j--) {
				Flight fromNow=(Flight)flights.get(j-1);
				if(fromNow.compareByTime(toIterate)>0) {
					flights.set(j, fromNow);
					flights.set(j-1, toIterate);
				}else {
					finished=true;
				}
			}
			}
		}
	// selection method
	public void orderByDate() {
		int init;
		for(init=0;init<flights.size();init++) {
			int lesser=init;
			Flight less = (Flight)flights.get(init);
			
			for(int i=init+1;i<flights.size();i++) {
				Flight f =(Flight)flights.get(i);
				if(f.compareByDate(less)<0) {
					less=f;
					lesser=i;
				}
			}
			if(lesser!=init) {
				Flight temp = (Flight)flights.get(init);
				flights.set(init, less);
				flights.set(lesser, temp);
			}
		}
	}
	// sequential
	public Flight searchByAirline(String name) {
		boolean ended=false;
		Flight toPaint=null;
		for(int i=0;i<flights.size() && !ended;i++) {
			if(flights.get(i).getAirline().equalsIgnoreCase(name)) {
				ended=true;
				toPaint = (Flight)flights.get(i);
				ObservableList<Flight> tt = FXCollections.observableArrayList();
				tt.add(toPaint);
				setFlights(tt);
				}
		}
		return toPaint;
	}
	public void orderByFlight() {
		for(int i=1;i<flights.size();i++) {
			Flight toIterate = (Flight) flights.get(i);
			boolean finished =false;
			
			for(int j=i;j>0 && !finished;j--) {
				Flight fromNow=(Flight)flights.get(j-1);
				if(fromNow.compareByFlight(toIterate)>0) {
					flights.set(j, fromNow);
					flights.set(j-1, toIterate);
				}else {
					finished=true;
				}
			}
			}
		}
	public void orderByGate() {
		for(int i=1;i<flights.size();i++) {
			Flight toIterate = (Flight) flights.get(i);
			boolean finished =false;
			
			for(int j=i;j>0 && !finished;j--) {
				Flight fromNow=(Flight)flights.get(j-1);
				if(toIterate.getGate()<fromNow.getGate()) {
					flights.set(j, fromNow);
					flights.set(j-1, toIterate);
				}else {
					finished=true;
				}
			}
			}
	}
	public void orderByTo() {
		for(int i=1;i<flights.size();i++) {
			Flight toIterate = (Flight) flights.get(i);
			boolean finished =false;
			
			for(int j=i;j>0 && !finished;j--) {
				Flight fromNow=(Flight)flights.get(j-1);
				if(fromNow.compareByTo(toIterate)>0) {
					flights.set(j, fromNow);
					flights.set(j-1, toIterate);
				}else {
					finished=true;
				}
			}
			}
	}
	// binary search
	public void searchBYTime(String time) {
		orderByTime();
		int init=0;
		boolean ended = false;
		int end= flights.size()-1;
		Flight toSearch = new Flight(time, "", "", "", "", 1);
		while(init<= end && !ended) {
			int middle=(init+end)/2;
			Flight mid = (Flight)flights.get(middle);
			if(mid.compareByTime(toSearch)==0) {
				ended=true;
				ObservableList<Flight> tt = FXCollections.observableArrayList();
				tt.add(mid);
				setFlights(tt);
			}else if(mid.compareByTime(toSearch)>0) {
				end = middle-1;
			}else {
				end= middle+1;
			}
		}
	}
		
}

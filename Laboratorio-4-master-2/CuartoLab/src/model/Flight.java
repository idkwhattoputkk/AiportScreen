package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Flight {
	private String hours, minutes, airline, flight, to, date;
	private int gate;
	private Date dateAndTime;

	public Flight(String hours, String minutes, String airline, String flight, String to, String date, int gate) throws ParseException {
		this.hours=hours;
		this.minutes=minutes;
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm aa" );
		dateAndTime = sdf.parse(date+" "+hours+":"+minutes);
		
		this.airline = airline;
		this.flight = flight;
		this.to = to;
		this.date = date;
		this.gate = gate;
	}
	
	public Date getDateAndTime() {
		return dateAndTime;
	}

	public String getFulldate() {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
        return sdf.format( dateAndTime );
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getFlight() {
		return flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getGate() {
		return gate;
	}

	public void setGate(int gate) {
		this.gate = gate;
	}

	/*
	 * 0 if the flights have the same brand name. -1 if the flight f's name is
	 * greater than the other one. 1 if flight f's name has a less value.
	 */
	public int compareByBrandName(Flight f) {
		int valueToComparate = airline.compareToIgnoreCase(f.airline);
		if (valueToComparate < 0) {
			valueToComparate = -1;
		} else if (valueToComparate == 0) {
			valueToComparate = 0;
		} else {
			valueToComparate = 1;
		}
		return valueToComparate;
	}

	/*
	 * 0 if the times are equal lexicographically; -1 if one time is bigger
	 * lexicographically than the other; 1 if one time's lexicographically lesser
	 * than the other;
	 */
	public int compareByTime(Flight toIterate) {
		int valueToComparate = hours.compareToIgnoreCase(toIterate.hours);
		if (valueToComparate < 0) {
			valueToComparate = -1;
		} else if (valueToComparate == 0) {
			valueToComparate = 0;
		} else {
			valueToComparate = 1;
		}
		return valueToComparate;

	}

	public int compareByDate(Flight less) {
		int valueToComparate = date.compareToIgnoreCase(less.date);
		if (valueToComparate < 0) {
			valueToComparate = -1;
		} else if (valueToComparate == 0) {
			valueToComparate = 0;
		} else {
			valueToComparate = 1;
		}
		return valueToComparate;
	}

	public int compareByFlight(Flight f) {
		int valueToComparate = flight.compareToIgnoreCase(f.flight);
		if (valueToComparate < 0) {
			valueToComparate = -1;
		} else if (valueToComparate == 0) {
			valueToComparate = 0;
		} else {
			valueToComparate = 1;
		}
		return valueToComparate;
	}

	public int compareByTo(Flight f) {
		int valueToComparate = to.compareToIgnoreCase(f.to);
		if (valueToComparate < 0) {
			valueToComparate = -1;
		} else if (valueToComparate == 0) {
			valueToComparate = 0;
		} else {
			valueToComparate = 1;
		}
		return valueToComparate;

	}
	public String toString() {
		return airline + flight + to + getFulldate() + gate;
	}

}

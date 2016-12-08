import java.util.ArrayList;
import java.util.Date;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A model for the date range for the main guest view
 * @author JLepere2
 * Version 1.1
 */
public class DateRangeReservationModel {

	private HotelManager hotelManager;
	private String dateFrom, dateTo;
	private ArrayList<ChangeListener> listeners;
	private boolean economicSelected;
	
	public DateRangeReservationModel(HotelManager theHotelManager) {
		this.hotelManager = theHotelManager;
		this.listeners = new ArrayList<>();
		this.dateFrom = MyDate.getDateString(MyDate.getDate(new Date()));
		this.dateTo = MyDate.getDateString(MyDate.getDate(new Date()));
		this.economicSelected = true;
	}
	
	/**
	 * Sets the date from of the date to.
	 * @param dateFrom true to set the date from, false to set the date to
	 * @param theDate the date to set
	 */
	public void setDate(boolean dateFrom, String theDate) {
		if (dateFrom) {
			this.dateFrom = theDate;
		} else {
			this.dateTo = theDate;
		}
		
		ChangeEvent e = new ChangeEvent(this);
		for (ChangeListener l : listeners) {
			l.stateChanged(e);
		}
	}
	
	public void setRoomSelected(boolean economicSelected) {
		this.economicSelected = economicSelected;
		
		ChangeEvent e = new ChangeEvent(this);
		for (ChangeListener l : listeners) {
			l.stateChanged(e);
		}
	}
	
	public String getDateRangeHeader() {
		String header = dateFrom + " - " + dateTo + ":\n";
		if (economicSelected) {
			header = "Economic Rooms From " + header;
		} else {
			header = "Luxurious Rooms From " + header;
		}
		return header;
	}
	
	public ArrayList<HotelRoom> getReservationAvailable(ArrayList<Reservation> resQueue) {
		ArrayList<HotelRoom> roomsAvailable = hotelManager.getAvailableRooms(resQueue, MyDate.getDate(dateFrom), MyDate.getDate(dateTo));
		ArrayList<HotelRoom> roomsToReturn = new ArrayList<>();
		if (economicSelected) {
			for (HotelRoom r : roomsAvailable) {
				if (r.getRoomType().equals(EconomicRoom.identifier)) {
					roomsToReturn.add(r);
				}
			}
		} else {
			for (HotelRoom r : roomsAvailable) {
				if (r.getRoomType().equals(LuxuriousRoom.identifier)) {
					roomsToReturn.add(r);
				}
			}
		}
		return roomsToReturn;
	}
	
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}
	
	public String getDateFrom() {
		return dateFrom;
	}
	
	public String getDateTo() {
		return dateTo;
	}
	
}

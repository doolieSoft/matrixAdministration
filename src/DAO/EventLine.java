package DAO;

 public class EventLine {
	String description;
	String date;
	public EventLine(String date, String description) {
		this.description= description;
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public  String getDate() {
		return date;
	}
}

package tkom.kkomar.przypominajka.parser.types;

public class Datetime {
	int year;
	int month;
	int day;
	int hour;
	int min;
	int sec;
	private int importantPart;
	
	public Datetime(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
		importantPart = 3;
	}
	
	public Datetime(int day, int month, int year, Time t) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = t.hour;
		this.min = t.min;
		this.sec = t.sec;
		importantPart = 6;
	}
	
	public Datetime(int day, int month, int year, int hour) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		importantPart = 4;
	}
	
	public Datetime(int day, int month, int year, int hour, int min) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.min = min;
		importantPart = 5;
	}
}

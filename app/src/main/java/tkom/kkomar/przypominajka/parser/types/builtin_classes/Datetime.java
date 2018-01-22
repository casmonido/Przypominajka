package tkom.kkomar.przypominajka.parser.types.builtin_classes;

import tkom.kkomar.przypominajka.exceptions.RuntimeException;

public class Datetime {
	Integer year;
	Integer month;
	Integer day;
	Integer hour;
	Integer min;
	Integer sec;
	private int importantPart = 1;
	
	public Datetime(Integer year, Integer month, Integer day) {
		this.day = day;
		this.month = month;
		this.year = year;
		importantPart = 3;
	}
	
	public Datetime(Integer year, Integer month, Integer day, Time t) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = t.hour;
		this.min = t.min;
		this.sec = t.sec;
		importantPart = 6;
	}
	
	public Datetime(Integer year, Integer month, Integer day, Integer hour) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		importantPart = 4;
	}
	
	public Datetime(Integer year, Integer month, Integer day, Integer hour, Integer min) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.min = min;
		importantPart = 5;
	}

	@Override
	public boolean equals(Object obj) {
		if (isSameType(obj))
			return compare((Datetime) obj) == 0;
		return false;
	}

	// this > other?
	public boolean greaterThan(Object obj) throws RuntimeException {
		if (isSameType(obj))
			return compare((Datetime) obj) == 1;
		throw new RuntimeException("Porównanie między obiektami różnych typów!");
	}

	public boolean lessThan(Object obj) throws RuntimeException {
		if (isSameType(obj))
			return compare((Datetime) obj) == -1;
		throw new RuntimeException("Porównanie między obiektami różnych typów!");
	}

	private boolean isSameType(Object obj)
	{
		if (obj == null) {
			return false;
		}
		if (!Time.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		return true;
	}

	private int compare(Datetime other)
	{
		if (this.year > other.year)
			return 1;
		if (this.year < other.year)
			return -1;
		if (this.month == null || other.month == null || importantPart == 1) //nonimportant
			return 0;
		if (this.month > other.month)
			return 1;
		if (this.month < other.month)
			return -1;
		if (this.day == null || other.day == null || importantPart == 2) //nonimportant
			return 0;
		if (this.day > other.day)
			return 1;
		if (this.day < other.day)
			return -1;
		if (this.hour == null || other.hour == null || importantPart == 3) //nonimportant
			return 0;
		if (this.hour > other.hour)
			return 1;
		if (this.hour < other.hour)
			return -1;
		//hour == hour
		if (this.min == null || other.min == null || importantPart == 4) //nonimportant
			return 0;
		if (this.min > other.min)
			return 1;
		if (this.min < other.min)
			return -1;
		if (this.sec == null || other.sec == null || importantPart == 5) //nonimportant
			return 0;
		if (this.sec > other.sec)
			return 1;
		if (this.sec < other.sec)
			return -1;
		return 0;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = (int) (53 * hash + ((this.year==null)?1:this.year));
		hash = (int) (53 * hash + ((this.month==null)?1:this.month));
		hash = (int) (53 * hash + ((this.day==null)?1:this.day));
		hash = (int) (53 * hash + ((this.hour==null)?1:this.hour));
		hash = (int) (53 * hash + ((this.min==null)?1:this.min));
		hash = (int) (53 * hash + ((this.sec==null)?1:this.sec));
		return hash;
	}
}

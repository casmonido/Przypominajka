package tkom.kkomar.przypominajka.parser.types.builtin_classes;

import tkom.kkomar.przypominajka.exceptions.RuntimeException;

public class Time {
	Integer hour;
	Integer min; //czasem -1
	Integer sec;

	public Time(Integer hour, Integer min, Integer sec) {
		this.hour = hour;
		this.min = min;
		this.sec = sec;
	}
	public Time(Integer hour, Integer min) {
		this.hour = hour;
		this.min = min;
	}
	public Time(Integer hour) {
		this.hour = hour;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (isSameType(obj))
			return compare((Time) obj) == 0;
		return false;
	}

	// this > other?
	public boolean greaterThan(Object obj) throws RuntimeException {
		if (isSameType(obj))
			return compare((Time) obj) == 1;
		throw new RuntimeException("Porównanie między obiektami różnych typów!");
	}

	public boolean lessThan(Object obj) throws RuntimeException {
		if (isSameType(obj))
			return compare((Time) obj) == -1;
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

	private int compare(Time other)
	{
		if (this.hour > other.hour)
			return 1;
		if (this.hour < other.hour)
			return -1;
		//hour == hour
		if (this.min == null || other.min == null) //nonimportant
			return 0;
		if (this.min > other.min)
			return 1;
		if (this.min < other.min)
			return -1;
		if (this.sec == null || other.sec == null) //nonimportant
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
	    hash = (int) (53 * hash + this.hour);
	    hash = (int) (53 * hash + this.min);
	    hash = (int) (53 * hash + this.sec);
	    return hash;
	}
}	

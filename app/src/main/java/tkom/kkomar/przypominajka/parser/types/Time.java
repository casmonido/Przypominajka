package tkom.kkomar.przypominajka.parser.types;

import java.util.Objects;

public class Time {
	int hour;
	int min; //czasem -1
	int sec;

	public Time(int hour, int min, int sec) {
		this.hour = hour;
		this.min = min;
		this.sec = sec;
	}
	public Time(int hour, int min) {
		this.hour = hour;
		this.min = min;
	}
	public Time(int hour) {
		this.hour = hour;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (isSameType(obj))
			return compare((Time) obj) == 0;
		return false;
	}

	// this > other?
	public boolean greaterThan(Object obj) {
		if (isSameType(obj))
			return compare((Time) obj) == 1;
		return false;
	}

	public boolean lessThan(Object obj) {
		if (isSameType(obj))
			return compare((Time) obj) == -1;
		return false;
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
		if (this.min > other.min)
			return 1;
		if (this.min < other.min)
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

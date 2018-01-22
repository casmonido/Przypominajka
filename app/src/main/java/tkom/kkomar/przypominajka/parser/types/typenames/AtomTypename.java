package tkom.kkomar.przypominajka.parser.types.typenames;

import tkom.kkomar.przypominajka.scanner.Atom;

public class AtomTypename implements Type {
	public AtomTypename(Atom n) {
		nameId = n;
	}
	private Atom nameId;
	
	public Atom getAtom() {
		return nameId;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	private String getName() {
		switch (nameId) {
		case typeInt:
			return "int";
		case typeDouble:
			return "double";
		case typeString:
			return "string";
		case typeBool:
			return "bool";
		case typeTime:
			return "time";
		case typeDatetime:
			return "datetime";
		case typeLocation:
			return "location";
		case typeWeather:
			return "weather";
		case typeNetInfo:
			return "netinfo";
		default:
			return "";
		}
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!AtomTypename.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final AtomTypename other = (AtomTypename) obj;
	    if ((this.nameId == null) ? (other.nameId != null) : !this.nameId.equals(other.nameId)) {
	        return false;
	    }
	    return true;
	}

	@Override
	public int hashCode() {
	    int hash = 3;
	    hash = 53 * hash + (this.nameId != null ? this.nameId.hashCode() : 0);
	    return hash;
	}

}

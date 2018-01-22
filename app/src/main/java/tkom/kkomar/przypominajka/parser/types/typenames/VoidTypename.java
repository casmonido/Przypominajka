package tkom.kkomar.przypominajka.parser.types.typenames;


public class VoidTypename implements Type {
	
	public static VoidTypename type = new VoidTypename();
	
	private VoidTypename() {}
	
	public String toString() {
		return "void";
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (obj instanceof VoidTypename) {
	        return true;
	    }
	    return false;
	}

	@Override
	public int hashCode() {
	    return 2;
	}

}

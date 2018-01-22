package tkom.kkomar.przypominajka.parser.types.typenames;


public class ArrayTypename implements Type {
	
	public static ArrayTypename type = new ArrayTypename();
	
	private ArrayTypename() {}
	
	public String toString() {
		return "[]";
	}
	
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (obj instanceof ArrayTypename) {
	        return true;
	    }
	    return false;
	}

	@Override
	public int hashCode() {
	    return 1;
	}

}

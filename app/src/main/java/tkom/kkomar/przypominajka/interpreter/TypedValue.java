package tkom.kkomar.przypominajka.interpreter;

import tkom.kkomar.przypominajka.parser.types.Type;

public class TypedValue {
	
	private Object v;
	private Type typ;
	
	
	public TypedValue(Object v, Type t) {
		this.v = v;
		this.typ = t;
	}
	
	public Object getValue() {
		return v;
	}
	
	public Type getType() {
		return typ;
	}

}
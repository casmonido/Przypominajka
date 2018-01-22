package tkom.kkomar.przypominajka.interpreter.nodes;

import tkom.kkomar.przypominajka.parser.types.builtin_classes.Datetime;
import tkom.kkomar.przypominajka.parser.types.builtin_classes.Location;
import tkom.kkomar.przypominajka.parser.types.builtin_classes.NetInfo;
import tkom.kkomar.przypominajka.parser.types.builtin_classes.Time;
import tkom.kkomar.przypominajka.parser.types.builtin_classes.Weather;
import tkom.kkomar.przypominajka.parser.types.typenames.AtomTypename;
import tkom.kkomar.przypominajka.parser.types.typenames.Type;
import tkom.kkomar.przypominajka.parser.types.typenames.VoidTypename;
import tkom.kkomar.przypominajka.scanner.Atom;
import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;

public class LiteralNode implements Node {

	public Object value;
	
	public LiteralNode(Object v) {
		value = v;
	}

	@Override
	public TypedValue evalNode(Environment env) {
		Atom at = getAtomType();
		Type t = at==null ? VoidTypename.type : new AtomTypename(at);
		return new TypedValue(value, t);
	}
	
	
	private Atom getAtomType() {
		if (value instanceof Long) 
			return Atom.typeInt;
		if (value instanceof Integer) 
			return Atom.typeInt;
		if (value instanceof Double) 
			return Atom.typeDouble;
		if (value instanceof String) 
			return Atom.typeString;
		if (value instanceof Boolean) 
			return Atom.typeBool;
		if (value instanceof Time)
			return Atom.typeTime;
		if (value instanceof Datetime)
			return Atom.typeDatetime;
		if (value instanceof Location)
			return Atom.typeLocation;
		if (value instanceof Weather)
			return Atom.typeWeather;
		if (value instanceof NetInfo)
			return Atom.typeNetInfo;
		return null;
	}
}

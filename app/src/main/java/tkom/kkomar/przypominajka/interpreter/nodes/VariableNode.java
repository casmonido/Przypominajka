package tkom.kkomar.przypominajka.interpreter.nodes;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

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
import tkom.kkomar.przypominajka.exceptions.RuntimeException;

public class VariableNode implements Node {
	public Node ident;
	public String attrib;
	public Node numVal;
	

	public VariableNode(Node i, Node v, String a) {
		ident = i;
		attrib = a;
		numVal = v;
	}

	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException  {
		TypedValue tv = ident.evalNode(env);
		if (tv == null)
			throw new RuntimeException("Zmienna nie została zainicjalizowana!");
		Object obj = tv.getValue();
		Type typ = tv.getType();
		if (numVal != null && !obj.getClass().isArray()) 
			throw new RuntimeException("Próba użycia operatora wyłuskania [] na obiekcie który nie jest tablicą!");
		if (numVal != null) {
			obj = ((Node [])obj)[(int)numVal.evalNode(env).getValue()];
			typ = ((Node) obj).evalNode(env).getType();
		}
		if (attrib != null) {
			Field f = null;
			try {
				if (obj.getClass().isArray() && "length".equals(attrib))
					return new TypedValue(new Long(Array.getLength(obj)), new AtomTypename(Atom.typeInt));
				f = obj.getClass().getDeclaredField(attrib);
			} catch (NoSuchFieldException e) {
				throw new RuntimeException("Atrybut nie występuje w obiekcie");
			} 
			f.setAccessible(true);
			try {
				obj = f.get(obj);
				Atom at = getAtomType(obj);
				typ = ((at==null) ? VoidTypename.type : new AtomTypename(at));
			} catch (IllegalAccessException e) {
				// nie powinno sie zdarzyc
				e.printStackTrace();
			}
		}
		return new TypedValue(obj, typ);
	}
	
	
	private Atom getAtomType(Object value) {
		if (value instanceof Long) 
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

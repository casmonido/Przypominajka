package tkom.kkomar.przypominajka.interpreter.nodes;

import java.util.List;

import tkom.kkomar.przypominajka.parser.types.builtin_classes.Datetime;
import tkom.kkomar.przypominajka.parser.types.builtin_classes.Location;
import tkom.kkomar.przypominajka.parser.types.builtin_classes.Time;
import tkom.kkomar.przypominajka.parser.types.typenames.AtomTypename;
import tkom.kkomar.przypominajka.scanner.Atom;
import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;
import tkom.kkomar.przypominajka.exceptions.RuntimeException;

public class ConstrNode implements Node {
	public Atom type;
	public List<Node> args;
	
	public ConstrNode(Atom t, List<Node> a) {
		type = t;
		args = a;
	}

	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException { 
		switch (type) { 
		case typeDatetime:
//			datetime(int day, int month, int year)
//			datetime(int day, int month, int year, time t)
//			datetime(int day, int month, int year, int hour)
//			datetime(int day, int month, int year, int hour, int min)
			if (args.size() == 3)
				return new TypedValue(new Datetime(
						(Integer)args.get(0).evalNode(env).getValue(),
						(Integer)args.get(1).evalNode(env).getValue(),
						(Integer)args.get(2).evalNode(env).getValue()),
						new AtomTypename(type));
			if (args.size() == 4 && args.get(3) instanceof Time)
				return new TypedValue(new Datetime(
						(Integer)args.get(0).evalNode(env).getValue(),
						(Integer)args.get(1).evalNode(env).getValue(),
						(Integer)args.get(2).evalNode(env).getValue(),
						(Time)args.get(3).evalNode(env).getValue()),
						new AtomTypename(type));
			if (args.size() == 4)
				return new TypedValue(new Datetime(
						(Integer)args.get(0).evalNode(env).getValue(), 
						(Integer)args.get(1).evalNode(env).getValue(), 
						(Integer)args.get(2).evalNode(env).getValue(),
						(Integer)args.get(3).evalNode(env).getValue()),
						new AtomTypename(type));
			if (args.size() == 5)
				return new TypedValue(new Datetime(
						(Integer)args.get(0).evalNode(env).getValue(), 
						(Integer)args.get(1).evalNode(env).getValue(), 
						(Integer)args.get(2).evalNode(env).getValue(),
						(Integer)args.get(3).evalNode(env).getValue(),
						(Integer)args.get(4).evalNode(env).getValue()),
						new AtomTypename(type));
		case typeLocation:
			return new TypedValue(new Location(
					(String)args.get(0).evalNode(env).getValue(), 
					(String)args.get(1).evalNode(env).getValue(), 
					(String)args.get(2).evalNode(env).getValue(),
					(String)args.get(3).evalNode(env).getValue(), 
					(String)args.get(4).evalNode(env).getValue()),
					new AtomTypename(type));
		case typeTime:
			if (args.size() == 1)
				return new TypedValue(new Time(
						(Integer)args.get(0).evalNode(env).getValue()),
						new AtomTypename(type));
			if (args.size() == 2)
				return new TypedValue(new Time(
						(Integer)args.get(0).evalNode(env).getValue(), 
						(Integer)args.get(1).evalNode(env).getValue()),
						new AtomTypename(type));
			return new TypedValue(new Time(
					(Integer)args.get(0).evalNode(env).getValue(), 
					(Integer)args.get(1).evalNode(env).getValue(), 
					(Integer)args.get(2).evalNode(env).getValue()),
					new AtomTypename(type));
		default:
			return null;
		}
	}
}

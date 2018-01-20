package tkom.kkomar.przypominajka.interpreter.nodes;


import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;

public class IdentNode implements Node {
	public String ident;

	public IdentNode(String i) {
		ident = i;
	}

	@Override
	public TypedValue evalNode(Environment env) {
		return env.resolve(ident);
	}
}

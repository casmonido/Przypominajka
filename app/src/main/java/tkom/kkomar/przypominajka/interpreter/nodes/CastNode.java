package tkom.kkomar.przypominajka.interpreter.nodes;

import tkom.kkomar.przypominajka.parser.types.typenames.AtomTypename;
import tkom.kkomar.przypominajka.scanner.Atom;
import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;
import tkom.kkomar.przypominajka.exceptions.RuntimeException;

public class CastNode implements Node {
	protected Atom typ;
	protected Node right;
	
	public CastNode(Atom typ,  Node r) {
		this.typ = typ;
		right = r;
	}

	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException { // jak rzutowac??
		return new TypedValue(right.evalNode(env).getValue(), new AtomTypename(typ));
		//xlsl
	}
}

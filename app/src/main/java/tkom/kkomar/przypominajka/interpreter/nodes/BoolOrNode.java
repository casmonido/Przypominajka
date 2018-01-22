package tkom.kkomar.przypominajka.interpreter.nodes;

import tkom.kkomar.przypominajka.scanner.Atom;
import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;
import tkom.kkomar.przypominajka.parser.types.typenames.AtomTypename;
import tkom.kkomar.przypominajka.exceptions.RuntimeException;

public class BoolOrNode implements Node {

	protected Node left;
	protected Node right;
	
	public BoolOrNode(Node l, Node r) {
		left = l;
		right = r;
	}

	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException {
		return new TypedValue(((boolean) left.evalNode(env).getValue()) ||
				((boolean) right.evalNode(env).getValue()), new AtomTypename(Atom.typeBool));
	}

}

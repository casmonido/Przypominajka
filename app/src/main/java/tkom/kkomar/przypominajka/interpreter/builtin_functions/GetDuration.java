package tkom.kkomar.przypominajka.interpreter.builtin_functions;

import tkom.kkomar.przypominajka.interpreter.nodes.Node;
import tkom.kkomar.przypominajka.parser.types.typenames.AtomTypename;
import tkom.kkomar.przypominajka.scanner.Atom;
import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;


public class GetDuration implements Node { //in days

	Node left;
	Node right;

	@Override
	public TypedValue evalNode(Environment env) {

		return new TypedValue(new Long(4), new AtomTypename(Atom.typeInt));
	}
}

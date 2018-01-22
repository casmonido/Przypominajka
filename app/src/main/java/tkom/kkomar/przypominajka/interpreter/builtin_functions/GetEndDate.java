package tkom.kkomar.przypominajka.interpreter.builtin_functions;

import tkom.kkomar.przypominajka.interpreter.nodes.Node;
import tkom.kkomar.przypominajka.parser.types.builtin_classes.Datetime;
import tkom.kkomar.przypominajka.scanner.Atom;
import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;
import tkom.kkomar.przypominajka.parser.types.typenames.AtomTypename;

public class GetEndDate implements Node {

	@Override
	public TypedValue evalNode(Environment env) {
		return new TypedValue(new Datetime(
				new Integer(2), new Integer(10), new Integer(2018)), new AtomTypename(Atom.typeDatetime));
	}

}

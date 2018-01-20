package tkom.kkomar.przypominajka.interpreter.builtin_functions;

import tkom.kkomar.przypominajka.interpreter.nodes.Node;
import tkom.kkomar.przypominajka.parser.types.AtomType;
import tkom.kkomar.przypominajka.parser.types.Datetime;
import tkom.kkomar.przypominajka.scanner.Atom;
import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;


public class GetCurrentDate implements Node {

	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException {
		return new TypedValue(new Datetime(
				new Long(2), new Long(10), new Long(2018)), new AtomType(Atom.typeDatetime));
	}


}

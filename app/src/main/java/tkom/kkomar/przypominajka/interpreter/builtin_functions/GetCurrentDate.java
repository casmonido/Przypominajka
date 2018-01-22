package tkom.kkomar.przypominajka.interpreter.builtin_functions;

import java.util.Calendar;

import tkom.kkomar.przypominajka.interpreter.nodes.Node;
import tkom.kkomar.przypominajka.parser.types.typenames.AtomTypename;
import tkom.kkomar.przypominajka.parser.types.builtin_classes.Datetime;
import tkom.kkomar.przypominajka.scanner.Atom;
import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;


public class GetCurrentDate implements Node {

	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException {
		Calendar today = Calendar.getInstance();
		return new TypedValue(new Datetime(
				today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)),
				new AtomTypename(Atom.typeDatetime));
	}


}

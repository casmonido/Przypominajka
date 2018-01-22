package tkom.kkomar.przypominajka.interpreter.builtin_functions;

import java.util.Calendar;

import tkom.kkomar.przypominajka.interpreter.nodes.Node;
import tkom.kkomar.przypominajka.parser.types.builtin_classes.Datetime;
import tkom.kkomar.przypominajka.parser.types.typenames.AtomTypename;
import tkom.kkomar.przypominajka.parser.types.builtin_classes.Time;
import tkom.kkomar.przypominajka.scanner.Atom;
import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;


public class GetCurrentTime implements Node {

	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException {
		Calendar today = Calendar.getInstance();
		return new TypedValue(new Time(
				today.get(Calendar.HOUR_OF_DAY), today.get(Calendar.MINUTE), today.get(Calendar.SECOND)),
				new AtomTypename(Atom.typeTime));
	}

}

package tkom.kkomar.przypominajka.interpreter.builtin_functions;


import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;
import tkom.kkomar.przypominajka.exceptions.RuntimeException;
import tkom.kkomar.przypominajka.interpreter.nodes.Node;
import tkom.kkomar.przypominajka.parser.types.ArrayType;


public class GetBirthdaysToday implements Node {

	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException {
		return new TypedValue(new String [] {"osoba1", "osoba2"}, ArrayType.type);
	}

}

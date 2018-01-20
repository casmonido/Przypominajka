package tkom.kkomar.przypominajka.interpreter.builtin_functions;

import tkom.kkomar.przypominajka.interpreter.nodes.Node;
import tkom.kkomar.przypominajka.parser.types.AtomType;
import tkom.kkomar.przypominajka.parser.types.Weather;
import tkom.kkomar.przypominajka.scanner.Atom;
import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;


public class GetWeatherForecast implements Node {

	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException {
		return new TypedValue(new Weather(), new AtomType(Atom.typeWeather));
	}

}

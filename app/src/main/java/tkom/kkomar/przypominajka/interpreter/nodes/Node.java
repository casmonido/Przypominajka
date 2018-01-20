package tkom.kkomar.przypominajka.interpreter.nodes;

import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;

public interface Node {

	public TypedValue evalNode(Environment env) throws tkom.kkomar.przypominajka.exceptions.RuntimeException;
	
}

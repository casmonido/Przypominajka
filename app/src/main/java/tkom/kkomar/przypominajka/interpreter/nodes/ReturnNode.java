package tkom.kkomar.przypominajka.interpreter.nodes;

import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;

public class ReturnNode implements Node {
	protected Node node;
	
	public ReturnNode(Node n) {
		node = n;
	}

	@Override
	public TypedValue evalNode(Environment env) throws tkom.kkomar.przypominajka.exceptions.RuntimeException {
		return node.evalNode(env); //whaaaaaaaa
	}

}

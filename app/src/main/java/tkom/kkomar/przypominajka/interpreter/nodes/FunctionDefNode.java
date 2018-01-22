package tkom.kkomar.przypominajka.interpreter.nodes;


import java.util.List;

import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedIdent;
import tkom.kkomar.przypominajka.interpreter.TypedValue;
import tkom.kkomar.przypominajka.parser.types.typenames.Type;
import tkom.kkomar.przypominajka.exceptions.RuntimeException;

public class FunctionDefNode implements Node {
	private Type retType;
	public List<TypedIdent> args; 
	public List<Node> body;
	private String name;

	public FunctionDefNode(Type retType, String name, List<TypedIdent> args, List<Node> body) {
		this.retType = retType;
		this.name = name;
		this.args = args;
		this.body = body;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException {
		for (Node n: body) {
			if (n instanceof ReturnNode)
				return n.evalNode(env);
			n.evalNode(env);
		}
		return null;
	}

	public Type getType() {
		return retType;
	}

}

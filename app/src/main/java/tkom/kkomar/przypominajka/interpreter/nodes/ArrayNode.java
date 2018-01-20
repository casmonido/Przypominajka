package tkom.kkomar.przypominajka.interpreter.nodes;

import java.util.List;

import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;
import tkom.kkomar.przypominajka.parser.types.ArrayType;


public class ArrayNode implements Node {

	protected Node [] value;
	
	public ArrayNode(List<Node> list) {
		value = list.toArray(new Node [list.size()]);
	}

	public ArrayNode(int num) {
		value = new Node [num];
	}
	
	@Override
	public TypedValue evalNode(Environment env) {
		return new TypedValue(value, ArrayType.type);
	}

}

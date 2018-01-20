package tkom.kkomar.przypominajka.interpreter.nodes;

import java.util.List;

import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;
import tkom.kkomar.przypominajka.exceptions.RuntimeException;

public class StartNode implements Node {
	public List<Node> earlyAssign;
	public Node left;
	public List<Node> right;
	
	public StartNode(List<Node> asgn, Node l, List<Node> r) {
		left = l;
		right = r;
		earlyAssign = asgn;
	}

	
	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException {
		for (Node r : earlyAssign)
			r.evalNode(env);
		if ((boolean)left.evalNode(env).getValue() == true)
			for (Node r : right)
				r.evalNode(env);
		return null;
	}

}

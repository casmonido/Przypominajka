package tkom.kkomar.przypominajka.interpreter.nodes;

import java.util.List;

import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;
import tkom.kkomar.przypominajka.exceptions.RuntimeException;

public class RepeatUntilNode implements Node {
	protected Node left;
	protected List<Node> right;
	
	public RepeatUntilNode(Node l, List<Node> r) {
		left = l;
		right = r;
	}

	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException {
		env.addLayer();
		while ((boolean)left.evalNode(env).getValue() == false)
			for (Node r : right)
				r.evalNode(env);
		env.removeLayer();
		return null;
	}
}

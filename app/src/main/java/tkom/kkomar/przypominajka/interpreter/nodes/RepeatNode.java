package tkom.kkomar.przypominajka.interpreter.nodes;

import java.util.List;

import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;
import tkom.kkomar.przypominajka.exceptions.RuntimeException;

public class RepeatNode implements Node {
	public Node left;
	public List<Node> right;
	
	public RepeatNode(Node l, List<Node> r) {
		left = l;
		right = r;
	}

	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException {
		int num;
		if (left.evalNode(env).getValue() instanceof Long)
			num = ((Long)left.evalNode(env).getValue()).intValue();
		else
			num = (int)left.evalNode(env).getValue();
		env.addLayer();
		for (int i = num; i >= 0; i--) {
			for (Node r : right)
				r.evalNode(env);
		}
		env.removeLayer();
		return null;
	}
}

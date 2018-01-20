package tkom.kkomar.przypominajka.interpreter.nodes;

import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;
import tkom.kkomar.przypominajka.exceptions.RuntimeException;

public class EqualsNode extends ComparisonNode {

	public EqualsNode(Node l, Node r) {
		super(l, r);
	}

	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException {
		return super.evalNode(env);
		// i dodatkowe
	}

	@Override
	protected boolean immediateCompareDouble(Double ll, Double rr) {
		return ll == rr;
	}

	@Override
	protected boolean immediateCompareInt(Integer ll, Integer rr) {
		return ll == rr;
	}

	@Override
	protected String getOperator() {
		return "==";
	}

}

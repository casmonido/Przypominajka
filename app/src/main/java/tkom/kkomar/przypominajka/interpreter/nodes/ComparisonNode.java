package tkom.kkomar.przypominajka.interpreter.nodes;

import tkom.kkomar.przypominajka.parser.types.typenames.AtomTypename;
import tkom.kkomar.przypominajka.scanner.Atom;
import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;
import tkom.kkomar.przypominajka.exceptions.RuntimeException;

public abstract class ComparisonNode implements Node {
	public Node left;
	public Node right;
	
	public ComparisonNode(Node l, Node r) {
		left = l;
		right = r;
	}
	
	
	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException {
		TypedValue l = left.evalNode(env);
		TypedValue r = right.evalNode(env);
		if (!l.getType().equals(r.getType()))
			throw new RuntimeException("Próba zastosowania operatora " + getOperator() +
					" między obiektami różnych typów: " +
					l.getType().toString() + " i " + r.getType().toString());
		if (!(l.getType() instanceof AtomTypename))
			throw new RuntimeException("Próba zastosowania operatora " + getOperator() +
					" do obiektów typu " + l.getType().toString());
		switch (((AtomTypename)l.getType()).getAtom()) {
		case typeInt:
			return new TypedValue(
					immediateCompareInt((Integer)l.getValue(), (Integer)r.getValue()),
					new AtomTypename(Atom.typeBool));
		case typeDouble:
			if (l.getValue() instanceof Double)
				return new TypedValue(
						immediateCompareDouble((Double) l.getValue(), (Double) r.getValue()), 
						new AtomTypename(Atom.typeBool));
		default:
			throw new RuntimeException("Próba zastosowania operatora " + getOperator() +
					" do obiektów typu " + l.getType().toString());
		}
	}
	
	
	protected abstract boolean immediateCompareDouble(Double ll, Double rr);
	protected abstract boolean immediateCompareInt(Integer ll, Integer rr);
	protected abstract String getOperator();
}

package tkom.kkomar.przypominajka.interpreter.nodes;

import tkom.kkomar.przypominajka.parser.types.AtomType;
import tkom.kkomar.przypominajka.scanner.Atom;
import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.interpreter.TypedValue;
import tkom.kkomar.przypominajka.exceptions.RuntimeException;


public abstract class UnaryOperatorNode implements Node {
	private VariableNode var;
	
	public UnaryOperatorNode(VariableNode var) {
		this.var = var;
	}
	
	@Override
	public TypedValue evalNode(Environment env) throws RuntimeException {
		TypedValue l = var.evalNode(env);
		if (l == null)
			throw new RuntimeException("Zmienna " + ((IdentNode)var.ident).ident + " nie została zainicjalizowana!");
		if (!(l.getType() instanceof AtomType))
			throw new RuntimeException("Próba zastosowania operatora " + getOperator() +
					" do obiektów typu " + l.getType().toString());
		switch (((AtomType)l.getType()).getAtom()) {
		case typeInt:
			env.bind(((IdentNode)var.ident).ident, 
					new TypedValue(immediateEvalInt((Integer)l.getValue()), new AtomType(Atom.typeInt)));
			return var.evalNode(env);
		case typeDouble:
			env.bind(((IdentNode)var.ident).ident, 
					new TypedValue(immediateEvalDouble((Double)l.getValue()), new AtomType(Atom.typeDouble)));
			return var.evalNode(env);
		default:
			throw new RuntimeException("Próba zastosowania operatora " + getOperator() +
					" do obiektów typu " + l.getType().toString());
		}
	}
	
	protected abstract Double immediateEvalDouble(Double ll);
	protected abstract Integer immediateEvalInt(Integer ll);
	protected abstract String getOperator();
}

package tkom.kkomar.przypominajka.scanner.tokens;

import tkom.kkomar.przypominajka.scanner.Atom;
import tkom.kkomar.przypominajka.scanner.TextPos;

public class IntToken extends BasicToken {
	
	Integer value;

	public IntToken(Atom a, TextPos tp, Integer v) {
		super(a, tp);
		value = v;
	}
	
	@Override
	public Integer getValue() {
		return value;
	}
	
	public String toString() {
		return super.toString() + "\n" + value.toString();
	}

}

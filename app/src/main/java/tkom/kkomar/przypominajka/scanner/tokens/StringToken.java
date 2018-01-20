package tkom.kkomar.przypominajka.scanner.tokens;

import tkom.kkomar.przypominajka.scanner.Atom;
import tkom.kkomar.przypominajka.scanner.TextPos;

public class StringToken extends BasicToken {
	
	String value;

	public StringToken(Atom a, TextPos tp, String v) {
		super(a, tp);
		value = v;
	}
	
	@Override
	public String getValue() {
		return value;
	}

	public String toString() {
		return super.toString() + "\n" + value;
	}
	
}

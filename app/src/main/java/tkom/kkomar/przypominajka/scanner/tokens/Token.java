package tkom.kkomar.przypominajka.scanner.tokens;

import tkom.kkomar.przypominajka.scanner.Atom;
import tkom.kkomar.przypominajka.scanner.TextPos;

public interface Token {

	public Atom getAtom();
	public TextPos getTextPos();
	public Object getValue();
}

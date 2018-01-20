package tkom.kkomar.przypominajka.scanner;

import tkom.kkomar.przypominajka.scanner.tokens.*;

public interface ScanInterface {

	public Token nextToken();
	
	public void printEndReport();
}

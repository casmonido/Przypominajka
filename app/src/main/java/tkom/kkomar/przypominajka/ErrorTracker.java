package tkom.kkomar.przypominajka;

import android.content.Context;

import tkom.kkomar.przypominajka.scanner.TextPos;

public class ErrorTracker {

	private int totalErrors = 0;
    private Context context;
    private StringBuilder errorMsg = new StringBuilder();

	
	public void scanError(TextPos startPos, String errorMsg) {
		totalErrors++;
        this.errorMsg.append(totalErrors + ". " + startPos.toString() + ": " + errorMsg + "\n");
	}
	
	public void parseError(String errorMsg) {
		totalErrors++;
        this.errorMsg.append(totalErrors + ". " + errorMsg + "\n");
	}

    public StringBuilder getErrorsMsg() {
        return errorMsg;
    }
	public int getErrorsNum() {
		return totalErrors;
	}
}

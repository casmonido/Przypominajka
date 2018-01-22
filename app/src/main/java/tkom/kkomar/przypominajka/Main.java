package tkom.kkomar.przypominajka;

import android.content.Context;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import tkom.kkomar.przypominajka.scanner.Scan;
import tkom.kkomar.przypominajka.scanner.Source;
import tkom.kkomar.przypominajka.parser.Parser;
import tkom.kkomar.przypominajka.interpreter.Environment;


public class Main {

    public static int parseAndRun(InputStream file, Context context) {
        ErrorTracker errTr = new ErrorTracker();
        Scan scan = new Scan(new Source(file), errTr);
        Parser parser = new Parser(scan, errTr);
        try {
            parser.start();
        } catch (Exception e) {
            Toast.makeText(context, "Exception: " + e.toString(), Toast.LENGTH_LONG).show();
            return 1;
        }
        if (errTr.getErrorsNum() != 0)
        {
            Toast.makeText(context, "Przed ponownym włączeniem popraw błędy: " +
                            errTr.getErrorsMsg().toString(),
                    Toast.LENGTH_LONG).show();
            return 1;
        }
        Environment e = new Environment();
        try {
            parser.run(e);
        } catch (tkom.kkomar.przypominajka.exceptions.RuntimeException re) {
            Toast.makeText(context, "Błąd wykonania: " + re.getMessage(),
                    Toast.LENGTH_LONG).show();
            re.printStackTrace();
            return 1;
        }
        return 0;
    }

}
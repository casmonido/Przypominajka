package tkom.kkomar.przypominajka;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import tkom.kkomar.przypominajka.scanner.Scan;
import tkom.kkomar.przypominajka.scanner.Source;
import tkom.kkomar.przypominajka.parser.Parser;
import tkom.kkomar.przypominajka.interpreter.Environment;


public class Main {

    public static int runParser (String filePath) {
        Scan scan = null;
        ErrorTracker errTr = new ErrorTracker();
        try {
            scan = new Scan(new Source(filePath), errTr);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("Błąd: nie udało się otworzeć pliku źródłowego.");
            return 1;
        }
        Parser parser = new Parser(scan, errTr);
        try {
            parser.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Environment e = new Environment();
        parser.run(e);
        return 1;
    }

}
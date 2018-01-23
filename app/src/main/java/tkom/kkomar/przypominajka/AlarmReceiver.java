package tkom.kkomar.przypominajka;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.parser.Parser;
import tkom.kkomar.przypominajka.scanner.Scan;
import tkom.kkomar.przypominajka.scanner.Source;

public class AlarmReceiver extends BroadcastReceiver {

    public static String fileNameStr = "fileName";
    public static String repeatEveryStr = "repeatEvery";
    public static String startMillisStr = "startMillis";

    public int parse(String title, Context context) throws IOException {

        int repeatEvery = 0;
        InputStream file = context.openFileInput(title);
        if (file == null)
            throw new IOException("Nie udało się otworzyć pliku!");
        ErrorTracker errTr = new ErrorTracker();
        Scan scan = new Scan(new Source(file), errTr);
        Parser parser = new Parser(scan, errTr);
        try {
            repeatEvery = parser.start();
            if (repeatEvery == 0)
                repeatEvery = 1; //duzo
        } catch (Exception e) {
            Toast.makeText(context, "Exception: " + e.toString(), Toast.LENGTH_LONG).show();
            return 1;
        }
        if (errTr.getErrorsNum() != 0)
        {
            Toast.makeText(context, "Przed ponownym włączeniem popraw błędy: " +
                            errTr.getErrorsMsg().toString(),
                    Toast.LENGTH_LONG).show();
            return 0;
        }
        file.close();
        return  repeatEvery;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        String title = "";
        Parser parser = null;
        Bundle b = intent.getExtras();
        if(b != null) {
            title = b.getString(fileNameStr);
        }
        try (InputStream file = context.openFileInput(title)) {
            if (file == null)
                return;
            ErrorTracker errTr = new ErrorTracker();
            Scan scan = new Scan(new Source(file), errTr);
            parser = new Parser(scan, errTr);
            parser.start();
        } catch (Exception fne) {
            Toast.makeText(context, "Exception: " + fne.toString(), Toast.LENGTH_LONG).show();
            System.out.println("Exception: " + fne.toString());
            fne.printStackTrace();
            return;
        }
        Environment e = new Environment();
        try {
            parser.run(e);
        } catch (tkom.kkomar.przypominajka.exceptions.RuntimeException re) {
            Toast.makeText(context, "Błąd wykonania: " + re.getMessage(),
                    Toast.LENGTH_LONG).show();
            System.out.println("Błąd wykonania: " + re.toString());
            re.printStackTrace();
        }
        Toast.makeText(context, "!!! alarm test receved", Toast.LENGTH_SHORT).show();
    }

    public static void cancelAlarm(Context context, String filename, int repeatEvery, long startMillis)
    {
        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtras(createBundle(filename, repeatEvery, startMillis));
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmMgr.cancel(alarmIntent);
    }

    private static Bundle createBundle(String filename, int repeatEvery, long startMillis)
    {
        Bundle b = new Bundle();
        b.putString(fileNameStr, filename);
        b.putInt(repeatEveryStr, repeatEvery);
        b.putLong(startMillisStr, startMillis);
        return b;
    }

    public static void setupAlarm(Context context, String filename, int repeatEvery, long startMillis)
    {
        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtras(createBundle(filename, repeatEvery, startMillis));
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, startMillis,
                1000 * 60 * repeatEvery, alarmIntent);
    }
}
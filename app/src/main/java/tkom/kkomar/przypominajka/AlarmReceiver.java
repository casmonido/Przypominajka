package tkom.kkomar.przypominajka;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import tkom.kkomar.przypominajka.interpreter.Environment;
import tkom.kkomar.przypominajka.parser.Parser;
import tkom.kkomar.przypominajka.scanner.Scan;
import tkom.kkomar.przypominajka.scanner.Source;

public class AlarmReceiver extends BroadcastReceiver {

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    public void parseAndRun(String title, Context context) throws IOException {
        int repeatEvery = 0;
        InputStream file = context.openFileInput(title);
        if (file == null)
            return;
        ErrorTracker errTr = new ErrorTracker();
        Scan scan = new Scan(new Source(file), errTr);
        Parser parser = new Parser(scan, errTr);
        try {
            repeatEvery = parser.start();
            if (repeatEvery == 0)
                repeatEvery = 1; //duzo
        } catch (Exception e) {
            Toast.makeText(context, "Exception: " + e.toString(), Toast.LENGTH_LONG).show();
            return;
        }
        if (errTr.getErrorsNum() != 0)
        {
            Toast.makeText(context, "Przed ponownym włączeniem popraw błędy: " +
                            errTr.getErrorsMsg().toString(),
                    Toast.LENGTH_LONG).show();
            return;
        }
        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        Bundle b = new Bundle();
        b.putString("fileName", title);
        intent.putExtras(b);

        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        // Set the alarm to start at 8:30 a.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        Toast.makeText(context, "repeatEvery : " + repeatEvery,
                Toast.LENGTH_LONG).show();
//        calendar.set(Calendar.HOUR_OF_DAY, 8);
//        calendar.set(Calendar.MINUTE, 30);

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * repeatEvery, alarmIntent);
        file.close();
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        String title = "";
        Parser parser = null;
        Bundle b = intent.getExtras();
        if(b != null)
            title = b.getString("fileName");
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
           // parser.run(e);
            if (parser.run(e))
                if (alarmMgr!= null)
                    alarmMgr.cancel(alarmIntent);
        } catch (tkom.kkomar.przypominajka.exceptions.RuntimeException re) {
            Toast.makeText(context, "Błąd wykonania: " + re.getMessage(),
                    Toast.LENGTH_LONG).show();
            System.out.println("Błąd wykonania: " + re.toString());
            re.printStackTrace();
        }
        Toast.makeText(context, "HEHEHE alarm test receved", Toast.LENGTH_SHORT).show();
    }
}
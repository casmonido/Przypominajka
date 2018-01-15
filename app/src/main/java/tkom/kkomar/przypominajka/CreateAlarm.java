package tkom.kkomar.przypominajka;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CreateAlarm extends AppCompatActivity {

    private EditText tekst = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tekst = (EditText) findViewById(R.id.sourcefile);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save("Note1.txt");
            }
        });

        tekst.setText(open("Note1.txt"));
    }

    public String open(String fileName) {
        String content = "";
        if (fileExists(fileName)) {
            try {
                InputStream in = openFileInput(fileName);
                if ( in != null) {
                    InputStreamReader tmp = new InputStreamReader( in );
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    StringBuilder buf = new StringBuilder();
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\n");
                    } in .close();
                    content = buf.toString();
                }
            } catch (java.io.FileNotFoundException e) {} catch (Throwable t) {
                Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return content;
    }

    public boolean fileExists(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    public void save(String fileName) {
        try {
            OutputStreamWriter out =
                    new OutputStreamWriter(openFileOutput(fileName, 0));
            out.write(tekst.getText().toString());
            out.close();
            Toast.makeText(this, "Note Saved!", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_choose_alarm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

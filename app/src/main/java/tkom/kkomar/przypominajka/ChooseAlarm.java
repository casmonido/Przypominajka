package tkom.kkomar.przypominajka;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class ChooseAlarm extends AppCompatActivity {


        private List <NotesBuilder> notesList = new LinkedList<> ();
        private NotesAdapter nAdapter;
        private RecyclerView notesRecycler;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_choose_alarm);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String newFile = addNewNote();
                    Toast.makeText(getApplicationContext(), "Dodano plik " + newFile, Toast.LENGTH_LONG).show();
//                    Intent myIntent = new Intent(ChooseAlarm.this, CreateAlarm.class);
//                    Bundle b = new Bundle();
//                    b.putString("filename", newFile); //Your id
//                    myIntent.putExtras(b);
//                    ChooseAlarm.this.startActivity(myIntent);
                }
            });

            notesRecycler = findViewById(R.id.notes);

            nAdapter = new NotesAdapter(notesList, new NotesAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(NotesBuilder nb) {
                    Intent myIntent = new Intent(ChooseAlarm.this, CreateAlarm.class);
                    Bundle b = new Bundle();
                    b.putString("fileName", nb.getTitle()); //Your id
                    myIntent.putExtras(b);
                    ChooseAlarm.this.startActivity(myIntent);
                }
            });
            RecyclerView.LayoutManager mLayoutManager =
                    new LinearLayoutManager(getApplicationContext());
            notesRecycler.setLayoutManager(mLayoutManager);
            notesRecycler.setItemAnimator(new DefaultItemAnimator());
            notesRecycler.setAdapter(nAdapter);
            RecyclerView.ItemDecoration dividerItemDecoration =
                    new DividerItemDecoration(this);
            notesRecycler.addItemDecoration(dividerItemDecoration);
            prepareNotes();
        }

        private void prepareNotes() {
            File directory;
            directory = getFilesDir();
            File[] files = directory.listFiles();
            String theFile;
            for (int f = 1; f <= files.length; f++) {
                theFile = "Note" + f + ".txt";
                NotesBuilder note = new NotesBuilder(theFile, open(theFile));
                notesList.add(note);
            }
        }

        public String addNewNote()
        {
            int num = getFilesDir().listFiles().length + 1;
            String theFile = "Note" + num + ".txt";
            NotesBuilder note = new NotesBuilder(theFile, open(theFile));
            notesList.add(note);
            return theFile;
        }

        public String open(String fileName) {
            String content = "";
            try {
                InputStream in = openFileInput(fileName);
                if (in != null) {
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
            return content;
        }
    }
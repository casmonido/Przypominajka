package tkom.kkomar.przypominajka.android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

import tkom.kkomar.przypominajka.AlarmReceiver;
import tkom.kkomar.przypominajka.R;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(NotesBuilder nb);
    }

    private List<NotesBuilder> notesList;
    private final OnItemClickListener listener;
    private ViewGroup parent;

    NotesAdapter(List<NotesBuilder> notesList, OnItemClickListener listener) {
        this.notesList = notesList;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NotesBuilder colorItem = notesList.get(position);
        holder.bind(colorItem, listener, parent);
    }

    // Return the size of dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return notesList.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, content;
        public Switch onoff;
        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            content = view.findViewById(R.id.content);
            onoff = view.findViewById(R.id.turnoff_on);
        }

        public void bind(final NotesBuilder item, final OnItemClickListener listener, final ViewGroup parent) {
            title.setText(item.getTitle());
            content.setText(item.getContent());
            onoff.setChecked(item.isOn);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

            onoff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked == true)
                    {
                        try {
                            AlarmReceiver main = new AlarmReceiver();
                            item.repeatEvery = main.parse(item.getTitle(), parent.getContext());
                        } catch (java.io.FileNotFoundException e) {} catch (Throwable t) {
                            Toast.makeText(parent.getContext(), "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        item.isOn = true;
                        item.startMillis = System.currentTimeMillis();
                        AlarmReceiver.cancelAlarm(parent.getContext(), item.filename,
                                item.repeatEvery, item.startMillis);
                    }
                    else
                    {
                        item.isOn = false;
                        AlarmReceiver.cancelAlarm(parent.getContext(), item.filename,
                                item.repeatEvery, item.startMillis);
                    }
                }
            });
        }
    }
}

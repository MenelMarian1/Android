package com.example.pierwszyandek.reminder;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pierwszyandek.R;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {

    private static final String TAG = "ReminderAdapter";

    private List<Reminder> reminders;
    private final ReminderViewModel reminderViewModel;

    public ReminderAdapter(ReminderViewModel reminderViewModel) {
        this.reminderViewModel = reminderViewModel;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View reminderView = inflater.inflate(R.layout.item_reminder, parent, false);

        return new ReminderAdapter.ViewHolder(reminderView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reminder reminder = reminders.get(position);

        TextView reminderItemTitle = holder.reminderItemTitle;
        TextView reminderItemNote = holder.reminderItemNote;
        TextView reminderItemDateTime = holder.reminderItemDateTime;
        Button reminderItemDeleteButton = holder.reminderItemDeleteButton;

        reminderItemTitle.setText(reminder.getReminderName());
        reminderItemNote.setText(reminder.getReminderInfo());

        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(reminder.getTime());

        reminderItemDateTime.setText(time.toInstant().atZone(ZoneOffset.systemDefault())
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));

        Calendar now = Calendar.getInstance();
        Log.d(TAG, String.format("onBindViewHolder: %s, %s", time, now));

        if (time.toInstant().isBefore(now.toInstant())) {
            reminderItemTitle.setPaintFlags(reminderItemTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            reminderItemNote.setPaintFlags(reminderItemNote.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            reminderItemDateTime.setPaintFlags(reminderItemDateTime.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            int color = holder.itemView.getContext().getColor(R.color.blue);

            reminderItemDateTime.setTextColor(color);
            reminderItemTitle.setTextColor(color);
            reminderItemNote.setTextColor(color);
        }

        reminderItemDeleteButton.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "DELETE " + reminder.getReminderName(), Toast.LENGTH_SHORT).show();
            reminderViewModel.deleteReminder(reminder);
            notifyDataSetChanged();
        });

        holder.itemView.setOnClickListener(
                (view) -> {

                    Intent editIntent = new Intent(view.getContext(), AddReminder.class);
                    Bundle bundle = new Bundle();

                    bundle.putInt("editing", reminder.getId());

                    editIntent.putExtras(bundle);

                    view.getContext().startActivity(editIntent);

                });
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
        notifyDataSetChanged();
        Log.d(TAG, "setReminders: save " + reminders.size());
    }

    @Override
    public int getItemCount() {
        return reminders == null ? 0 : reminders.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView reminderItemTitle;
        TextView reminderItemNote;
        TextView reminderItemDateTime;
        Button reminderItemDeleteButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            reminderItemTitle = itemView.findViewById(R.id.reminderItemTitle);
            reminderItemNote = itemView.findViewById(R.id.reminderItemNote);
            reminderItemDateTime = itemView.findViewById(R.id.reminderItemDateTime);
            reminderItemDeleteButton = itemView.findViewById(R.id.reminderItemDeleteButton);
        }
    }
}

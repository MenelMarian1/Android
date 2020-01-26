package com.example.pierwszyandek.contacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pierwszyandek.R;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private static final String TAG = "ContactAdapter";

    private List<Contact> contacts;
    private final ContactsViewModel contactsViewModel;



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        return new ContactAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);

        TextView contactItemName = holder.contactItemName;
        TextView contactItemNote = holder.contactItemNote;
        TextView contactItemSurname = holder.contactItemSurname;
        Button contactItemDeleteButton = holder.contactItemDeleteButton;
        Button callContactItemButton = holder.callContactItemButton;
        ImageView avatar = holder.contactAvatar;

        contactItemName.setText(contact.getContactName());
        contactItemNote.setText(contact.getContactNote());
        contactItemSurname.setText(contact.getContactSurname());


        contactItemDeleteButton.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "DELETE " + contact.getContactName(), Toast.LENGTH_SHORT).show();
            contactsViewModel.deleteContact(contact);
            notifyDataSetChanged();
        });

        callContactItemButton.setOnClickListener(view -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:123456789"));
            view.getContext().startActivity(callIntent);
        });

        holder.itemView.setOnClickListener(
                (view) -> {

                    Intent editIntent = new Intent(view.getContext(), AddContact.class);
                    Bundle bundle = new Bundle();

                    bundle.putInt("editing", contact.getId());

                    editIntent.putExtras(bundle);

                    view.getContext().startActivity(editIntent);

                });
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
        Log.d(TAG, "setContacts: save " + contacts.size());
    }

    @Override
    public int getItemCount() {
        return contacts == null ? 0 : contacts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        Button callContactItemButton;
        TextView contactItemName;
        TextView contactItemNote;
        TextView contactItemSurname;
        Button contactItemDeleteButton;
        ImageView contactAvatar;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            contactItemName = itemView.findViewById(R.id.contactItemName);
            contactItemSurname = itemView.findViewById(R.id.contactItemSurname);
            contactItemNote = itemView.findViewById(R.id.contactItemNote);
            contactItemDeleteButton = itemView.findViewById(R.id.contactItemDeleteButton);
            callContactItemButton = itemView.findViewById(R.id.callContactItemButton);
            contactAvatar = itemView.findViewById(R.id.contactAvatar);
        }
    }
}

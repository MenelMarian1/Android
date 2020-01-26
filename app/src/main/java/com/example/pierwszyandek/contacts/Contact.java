package com.example.pierwszyandek.contacts;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @Nullable
    private String contactName;

    private String contactSurname;

    private String number;

    private String contactNote;

    private String avatarPath;

    private boolean gender;

}

package com.example.pierwszyandek.reminder;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Reminder {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "remindername")
    private String reminderName;

    @ColumnInfo(name = "info")
    private String reminderInfo;

    @ColumnInfo(name = "time")
    private Long time;

    public Reminder(String reminderName, String reminderInfo, Long time) {
        setReminderName(reminderName);
        setReminderInfo(reminderInfo);
        setTime(time);
    }

    public int getId() {
        return this.id;
    }

    public String getReminderName() {
        return this.reminderName;
    }

    public String getReminderInfo() {
        return this.reminderInfo;
    }

    public Long getTime() {
        return this.time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReminderName(String reminderName) {
        this.reminderName = reminderName;
    }

    public void setReminderInfo(String reminderInfo) {
        this.reminderInfo = reminderInfo;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Reminder)) return false;
        final Reminder other = (Reminder) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$reminderName = this.getReminderName();
        final Object other$reminderName = other.getReminderName();
        if (this$reminderName == null ? other$reminderName != null : !this$reminderName.equals(other$reminderName))
            return false;
        final Object this$reminderInfo = this.getReminderInfo();
        final Object other$reminderInfo = other.getReminderInfo();
        if (this$reminderInfo == null ? other$reminderInfo != null : !this$reminderInfo.equals(other$reminderInfo))
            return false;
        final Object this$time = this.getTime();
        final Object other$time = other.getTime();
        if (this$time == null ? other$time != null : !this$time.equals(other$time)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Reminder;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $reminderName = this.getReminderName();
        result = result * PRIME + ($reminderName == null ? 43 : $reminderName.hashCode());
        final Object $reminderInfo = this.getReminderInfo();
        result = result * PRIME + ($reminderInfo == null ? 43 : $reminderInfo.hashCode());
        final Object $time = this.getTime();
        result = result * PRIME + ($time == null ? 43 : $time.hashCode());
        return result;
    }

    public String toString() {
        return "Reminder(id=" + this.getId() + ", reminderName=" + this.getReminderName() + ", reminderInfo=" + this.getReminderInfo() + ", time=" + this.getTime() + ")";
    }
}

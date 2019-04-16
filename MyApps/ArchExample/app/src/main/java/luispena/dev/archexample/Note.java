package luispena.dev.archexample;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {
    // Member variables will represent fields in the note_table
    // room will also generate the columns for these fields
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String description;
    private String title;
    private int priority;

    // id is missing from constructor because we dont want to pass it laer,
    // id will be generated automaticaly.
    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    // set the id here since we still want room set the id to the note object.
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public int getPriority() {
        return priority;
    }
}

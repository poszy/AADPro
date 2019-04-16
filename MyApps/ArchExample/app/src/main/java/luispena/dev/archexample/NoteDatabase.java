package luispena.dev.archexample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;
    public abstract NoteDao noteDao();

    // synchronized in this case will make sure only one thread at a time can access the database and perform operations on
    // it accordingly. this is so data does not mess up and remains persistant
    public static synchronized NoteDatabase getInstance(Context context){

        if(instance == null){
            // context, name of database class we want to build, and the name we want to give it.
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_Database")
                    // fallbacktodestructivemigration will delete our database and recreate it once the app restarts or crashes. this is to avoid
                    // and Illegal exception error.
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}

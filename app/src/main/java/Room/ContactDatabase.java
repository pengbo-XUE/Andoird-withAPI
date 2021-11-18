package Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import Models.Contact;
import Room.Dao.ContactDao;


@Database(
        entities = {Contact.class},
        version = 1,
        exportSchema = false)
// Be an abstract class that extends RoomDatabase.
public abstract class ContactDatabase extends RoomDatabase {
    public abstract ContactDao ContactDao();


    private static ContactDatabase contactDatabase;

    public static ContactDatabase getDbInstance(final Context context) {
        if(contactDatabase == null) {
            contactDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    ContactDatabase.class, "Contact_room.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return contactDatabase;
    }
}



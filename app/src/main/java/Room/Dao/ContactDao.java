package Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Models.Contact;
@Dao
public interface ContactDao {

    @Insert
    public void add(Contact... contacts);

    @Update
    public void update(Contact... contacts);

    @Delete
    public void delete(Contact... contacts);

    @Query("SELECT * FROM contacts WHERE id = :Id")
    Contact getContact(int Id);

    @Query("DELETE FROM contacts")
    public void clearTable();

    @Query("SELECT * FROM contacts")
    List<Contact> getAll();
}


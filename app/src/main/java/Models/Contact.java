package Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
/**
 * Contact class, used for representing a instance of a contact.
 * is Serializable for use when passing between activities.
 */
@Entity(tableName = "contacts")
public class Contact implements Comparable, Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int Id;
    @ColumnInfo(name = "firstName")
    public String firstName;
    @ColumnInfo(name = "lastName")
    public  String lastName;
    @ColumnInfo(name = "date")
    public String dateEntered;
    @ColumnInfo(name = "phone")
    public String phone;

    public Contact() {
    }
    @Ignore
    public Contact(String firstName, String lastName, String dateEntered, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateEntered = dateEntered;
        this.phone = phone;

    }

    @Ignore
    public Contact(int Id, String firstName, String lastName, String dateEntered, String phone) {
        this.Id = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateEntered = dateEntered;
        this.phone = phone;

    }
    @Ignore
    public String getDateEntered() {
        return dateEntered;
    }
    @Ignore
    public void setDateEntered(String dateEntered) {
        this.dateEntered = dateEntered;
    }
    @Ignore
    public String getLastName() {
        return lastName;
    }
    @Ignore
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Ignore
    public String getFirstName() {
        return firstName;
    }
    @Ignore
    public void setFirstName(String name) {
        this.firstName = name;
    }
    @Ignore
    public String getPhone() {
        return phone;
    }
    @Ignore
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Ignore
    @Override
    public String toString() {
        return "Contact{" + "name=" + firstName + ", phone=" + phone + '}';
    }

    @Ignore
    @Override
    public int compareTo(Object o) {
        return this.firstName.compareToIgnoreCase(((Contact)o).firstName);
    }
    @Ignore
    public int getId() {return this.Id;}
}
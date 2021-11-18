package Assignment2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Models.Contact;
/**
 * SearchActivity, gets user input and returns to ContactListActivity with a filtered list of contact
 */
public class SearchActivity extends AppCompatActivity {
    public enum RequestCode{
        VIEW_DETAIL_REQUEST_CODE,
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        List<Contact> allContacts =  ContactListActivity.db.ContactDao().getAll();
        final EditText name = findViewById(R.id.text_Name_entered);
        Button btnShowDetail  = findViewById(R.id.name_search_button);
        btnShowDetail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                ArrayList<Contact> contactList = new ArrayList<>();
                String name_txt = name.getText().toString();
                for (Contact contact: allContacts) {
                    if (contact.getLastName().toLowerCase().trim().equals(name_txt.toLowerCase().trim())  || contact.getFirstName().toLowerCase().trim().equals(name_txt.toLowerCase().trim()) )
                    {
                        contactList.add(contact);
                    }
                }
                Intent intent = new Intent(SearchActivity.this,ContactListActivity.class);
                intent.putExtra("index_of_contact", (Serializable)contactList);
                startActivityForResult(intent , RequestCode.VIEW_DETAIL_REQUEST_CODE.ordinal());
            }
        });
    }
}

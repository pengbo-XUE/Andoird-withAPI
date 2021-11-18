package Assignment2.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.List;

import Models.Contact;

import static Room.ContactDatabase.getDbInstance;
/**
 * DetailActivity, takes care of updating, deleting and opening the phone app with the corresponding phone number prefilled
 */

public class DetailActivity extends AppCompatActivity {
    int Id= -1;

    long time =System.currentTimeMillis();
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("contact detail");
        }
        final TextView txtFName = findViewById(R.id.editFName);
        final TextView txtLName = findViewById(R.id.editLName);
        final TextView txtPhone = findViewById(R.id.editPhone);
        ContactListActivity.db = getDbInstance(this);
        List<Contact> allContacts = ContactListActivity.db.ContactDao().getAll();
        Bundle b = getIntent().getExtras();
        if(b != null && b.containsKey("Id"))
        {
            Id= b.getInt("Id");
                Contact c = ContactListActivity.db.ContactDao().getContact(Id);
                if(c!= null)
                {
                    txtFName.setText(c.getFirstName());
                    txtLName.setText(c.getLastName());
                    txtPhone.setText(c.getPhone());
                }
        }
        Button btnDelete = findViewById(R.id.btn_detail_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Contact c: allContacts) {
                    if (c.Id == DetailActivity.this.Id)
                    {
                        ContactListActivity.db.ContactDao().delete(c);
                    }
                }
                Intent intent = new Intent(DetailActivity.this, ContactListActivity.class);
                startActivity(intent);
            }
        });
        Button btnCall = findViewById(R.id.call_button);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+txtPhone.getText().toString()));
                startActivity(intent);
            }
        });
        Button btnEdit= findViewById(R.id.btn_detail_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtFName.getText().toString().length()==0)
                {
                    txtFName.requestFocus();
                    txtFName.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!txtFName.getText().toString().matches("[a-zA-Z ]+"))
                {
                    txtFName.requestFocus();
                    txtFName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(txtLName.getText().toString().length()==0)
                {
                    txtLName.requestFocus();
                    txtLName.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!txtLName.getText().toString().matches("[a-zA-Z ]+"))
                {
                    txtLName.requestFocus();
                    txtLName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(txtPhone.getText().toString().length()==0)
                {
                    txtPhone.requestFocus();
                    txtPhone.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!txtPhone.getText().toString().matches("^[0-9]*$"))
                {
                    txtPhone.requestFocus();
                    txtPhone.setError("ENTER ONLY NUMBERS");
                }
                else
                {
                    for (Contact c: allContacts) {
                        if (c.Id == DetailActivity.this.Id)
                        {
                            c.setFirstName( txtFName.getText().toString());
                            c.setLastName(txtLName.getText().toString());
                            c.setPhone(txtPhone.getText().toString());
                            c.setDateEntered(String.valueOf(formatter.format(time)));
                            ContactListActivity.db.ContactDao().update(c);
                        }
                    }
                    Intent intent = new Intent(DetailActivity.this, ContactListActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("message","detail screen finished");
        setResult(RESULT_OK, resultIntent);
        super.onBackPressed();
    }

}

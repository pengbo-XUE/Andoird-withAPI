package Assignment2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

import Models.Contact;
import Models.RemoteContactDb;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static Room.ContactDatabase.getDbInstance;

    public class AddActivity  extends AppCompatActivity {

        private String TAG = this.getClass().getSimpleName();
        long time =System.currentTimeMillis();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add);
            if(getSupportActionBar() != null){
                getSupportActionBar().setTitle("contact detail");
            }

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.8:5000/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RemoteContactDb service = retrofit.create(RemoteContactDb.class);

            final TextView txtFName = findViewById(R.id.txt_add_Fname_value);
            final TextView txtLName = findViewById(R.id.txt_add_Lname_value);
            final TextView txtPhone = findViewById(R.id.txt_add_phone_value);
            ContactListActivity.db = getDbInstance(this);

            Button btnEdit= findViewById(R.id.btn_add);
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
                        Contact c = new Contact(txtFName.getText().toString(),txtLName.getText().toString(),formatter.format(time),txtPhone.getText().toString());
                        Call<Contact> contactCreate = service.ContactCreate(new Contact(0,txtFName.getText().toString(),txtLName.getText().toString(),formatter.format(time),txtPhone.getText().toString()));
                        contactCreate .enqueue(new Callback<Contact>(){
                            @Override
                            public void onResponse(Call<Contact> call, Response<Contact> response) {
                                Contact contact = response.body();
                                Log.d(TAG, contact .toString()+"onsucc");
                                return;
                            }
                            @Override
                            public void onFailure(Call<Contact> call, Throwable t) {
                                Log.d(TAG, t.toString()+"onFailure");
                                return;
                            }
                        });


                        ContactListActivity.db.ContactDao().add(new Contact(txtFName.getText().toString(),txtLName.getText().toString(),formatter.format(time),txtPhone.getText().toString()));
                        Intent intent = new Intent(AddActivity.this, ContactListActivity.class);
                        finish();
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

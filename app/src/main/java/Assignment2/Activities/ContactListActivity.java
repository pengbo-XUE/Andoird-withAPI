package Assignment2.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Lib.Hash;
import Models.Contact;
import Room.ContactDatabase;

import static Room.ContactDatabase.getDbInstance;
/**
 *  ContactListActivity, displays the hashed list using the hash library.
 *  when intent contains a list of contacts it will display that instead of the list from db
 */

public class ContactListActivity extends AppCompatActivity implements ListRecyclerViewAdapter.OnContactListener {

    private String TAG = this.getClass().getSimpleName();
    static  public boolean flag =true;
    private Hash hash;

    public static ContactDatabase db;
    List<Contact> allContacts;
    private RecyclerView recyclerViewMainList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        db = getDbInstance(this);
        if (flag == true)
        {
            initializeDb();
            flag = false;
        }
        allContacts = db.ContactDao().getAll();

        hash = new Hash();
        hash.buildHashTable(allContacts);
        List<Contact> list = hash.toList(false);

        allContacts = hash.toList(false);
        Bundle b = getIntent().getExtras();
        if(b != null && b.containsKey("index_of_contact"))
        {
            if ( b.get("index_of_contact") != null)
            {
                allContacts = (List<Contact>) b.get("index_of_contact");
            }
        }


        recyclerViewMainList = findViewById(R.id.recycler_view_main_list);
        ListRecyclerViewAdapter adapter = new ListRecyclerViewAdapter( (ArrayList<Contact>) allContacts , this);
        recyclerViewMainList.setAdapter(adapter);
        recyclerViewMainList.setLayoutManager(new LinearLayoutManager(this));
        setAllNavBtnClickListener();

        findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.a_z_sort_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<Contact> compareByName = (Contact c1, Contact c2) ->
                        c1.getFirstName().toLowerCase().compareTo( c2.getFirstName().toLowerCase() );
                List<Contact> contactList = hash.toList(false);
                contactList.sort(compareByName);
                Intent intent = new Intent(ContactListActivity.this, ContactListActivity.class);
                intent.putExtra("index_of_contact", (Serializable)contactList);
                startActivityForResult(intent , SearchActivity.RequestCode.VIEW_DETAIL_REQUEST_CODE.ordinal());

            }
        });

        findViewById(R.id.z_a_sort_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<Contact> compareByName = (Contact c1, Contact c2) ->
                        c2.getFirstName().toLowerCase().compareTo( c1.getFirstName().toLowerCase() );
                List<Contact> contactList = hash.toList(false);
                contactList.sort(compareByName);
                Intent intent = new Intent(ContactListActivity.this, ContactListActivity.class);
                intent.putExtra("index_of_contact", (Serializable)contactList);
                startActivityForResult(intent , SearchActivity.RequestCode.VIEW_DETAIL_REQUEST_CODE.ordinal());
            }
        });
    }

    private void initializeDb() {
        db.ContactDao().clearTable();
        db.ContactDao().add(new Contact("aohn","Smith","12-12-2012", "123 456 789"));
        db.ContactDao().add(new Contact("Aane","Doe", "12-12-2012","987 654 321"));
        db.ContactDao().add(new Contact("Yason","Bourne","12-12-2012", "4255345326346"));
        db.ContactDao().add(new Contact("Xames","Bond","12-12-2012", "0072355235"));
        db.ContactDao().add(new Contact("2ohn","Smith","12-12-2012", "123 456 789"));
        db.ContactDao().add(new Contact("Bane","Doe","12-12-2012", "987 654 321"));
        db.ContactDao().add(new Contact("Bason","Bourne","12-12-2012", "12345"));

    }

    private void navBtnClick(int key) {
        if (key < 0 || key > 26) {
            return;
        }
        int offset = hash.calcOffsetByKey(key);
        Log.d(TAG, "offset( " + key + ") = " + offset);

        ((LinearLayoutManager)recyclerViewMainList.getLayoutManager()).scrollToPositionWithOffset(offset, 0);
    }

    private void setAllNavBtnClickListener(){

        findViewById(R.id.btn_main_nav_ee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(0);
            }
        });
        findViewById(R.id.btn_main_nav_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(1);
            }
        });
        findViewById(R.id.btn_main_nav_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(2);
            }
        });
        findViewById(R.id.btn_main_nav_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(3);
            }
        });
        findViewById(R.id.btn_main_nav_d).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(4);
            }
        });
        findViewById(R.id.btn_main_nav_e).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(5);
            }
        });
        findViewById(R.id.btn_main_nav_f).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(6);
            }
        });
        findViewById(R.id.btn_main_nav_g).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(7);
            }
        });
        findViewById(R.id.btn_main_nav_h).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(8);
            }
        });
        findViewById(R.id.btn_main_nav_i).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(9);
            }
        });
        findViewById(R.id.btn_main_nav_j).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(10);
            }
        });
        findViewById(R.id.btn_main_nav_k).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(11);
            }
        });
        findViewById(R.id.btn_main_nav_l).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(12);
            }
        });
        findViewById(R.id.btn_main_nav_m).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(13);
            }
        });
        findViewById(R.id.btn_main_nav_n).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(14);
            }
        });
        findViewById(R.id.btn_main_nav_o).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(15);
            }
        });
        findViewById(R.id.btn_main_nav_p).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(16);
            }
        });
        findViewById(R.id.btn_main_nav_q).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(17);
            }
        });
        findViewById(R.id.btn_main_nav_r).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(18);
            }
        });
        findViewById(R.id.btn_main_nav_s).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(19);
            }
        });
        findViewById(R.id.btn_main_nav_t).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(20);
            }
        });
        findViewById(R.id.btn_main_nav_u).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(21);
            }
        });
        findViewById(R.id.btn_main_nav_v).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(22);
            }
        });
        findViewById(R.id.btn_main_nav_w).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(23);
            }
        });
        findViewById(R.id.btn_main_nav_x).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(24);
            }
        });
        findViewById(R.id.btn_main_nav_y).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(25);
            }
        });
        findViewById(R.id.btn_main_nav_z).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactListActivity.this.navBtnClick(26);
            }
        });
    }
    @Override
    public void onContactClick(int position) {
        Intent intent = new Intent(ContactListActivity.this, DetailActivity.class);
        List<Contact> sd= hash.toList(false);
        int id  = hash.toList(false).get(position).Id;
        intent.putExtra("Id",  hash.toList(false).get(position).Id);
        startActivity(intent);
    }

    class SliderGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";
        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }
    }
}

 /* db.ContactDao().add(new Contact("James","Bond","12-12-2012", "007"));
        db.ContactDao().add(new Contact("aaaan","Smith","12-12-2012", "123 456 789"));
        db.ContactDao().add(new Contact("Axxe","Doe","12-12-2012", "987 654 321"));
        db.ContactDao().add(new Contact("Cason ","Bourne","12-12-2012", ""));
        db.ContactDao().add(new Contact("Dames"," Bond","12-12-2012", "007"));
        db.ContactDao().add(new Contact("Eohn ","Smith","12-12-2012", "123 456 789"));
        db.ContactDao().add(new Contact("eane","Doe", "12-12-2012","987 654 321"));
        db.ContactDao().add(new Contact("jason","Bourne","12-12-2012", ""));
        db.ContactDao().add(new Contact("James","Bond", "12-12-2012","007"));
        db.ContactDao().add(new Contact("3ohn","Smith","12-12-2012", "123 456 789"));
        db.ContactDao().add(new Contact("#ane","Doe","12-12-2012", "987 654 321"));
        db.ContactDao().add(new Contact("mmson","Bourne","12-12-2012", ""));
        db.ContactDao().add(new Contact("Mames","Bond","12-12-2012" ,"007"));
        db.ContactDao().add(new Contact("Dames","Bond","12-12-2012", "007"));
        db.ContactDao().add(new Contact("Fohn","Smith","12-12-2012", "123 456 789"));
        db.ContactDao().add(new Contact("Eane","Doe","12-12-2012", "987 654 321"));
        db.ContactDao().add(new Contact("Hason","Bourne","12-12-2012", ""));
        db.ContactDao().add(new Contact("Hames","Bond","12-12-2012", "007"));
        db.ContactDao().add(new Contact("3ohn","Smith","12-12-2012", "123 456 789"));
        db.ContactDao().add(new Contact("#ane","Doe","12-12-2012", "987 654 321"));
        db.ContactDao().add(new Contact("Pason","Bourne","12-12-2012", ""));
        db.ContactDao().add(new Contact("Pames","Bond","12-12-2012", "007"));*/




package Assignment2.Activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * MainActivity, MainActivity of the project
 */

public class MainActivity extends AppCompatActivity {
    private int activeThemeResId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        activeThemeResId = R.style.AppTheme;
        modifyTheme();


        findViewById(R.id.theme_dark_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    activeThemeResId = R.style.DarkTheme;
                modifyTheme();
            }
        });

        findViewById(R.id.theme_light_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    activeThemeResId = R.style.AppTheme;
                modifyTheme();
            }
        });

        findViewById(R.id.View_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void modifyTheme(){
        setTheme(activeThemeResId);
        TypedValue typedValue = new TypedValue();

        getTheme().resolveAttribute(R.attr.colorBackground, typedValue, true);
        findViewById(R.id.Main_bg).setBackgroundColor(typedValue.data);

        getTheme().resolveAttribute(R.attr.colorButtonTextColor, typedValue, true);
        ((Button)findViewById(R.id.theme_dark_button)).setTextColor(typedValue.data);
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        ((Button)findViewById(R.id.theme_dark_button)).setBackgroundTintList(ColorStateList.valueOf(typedValue.data));
        getTheme().resolveAttribute(R.attr.colorTextHighEmphasis, typedValue, true);
        ((TextView)findViewById(R.id.textView)).setTextColor(typedValue.data);
        setActionBarStyle();
        getTheme().resolveAttribute(R.attr.statusBarBackground, typedValue, true);
        getWindow().setStatusBarColor(typedValue.data);
    }

    private void setActionBarStyle(){
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        if (activeThemeResId == R.style.DarkTheme) {
            actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorActionBarBackgroundDarkTheme)));
        } else {
            actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));
        }
    }

}

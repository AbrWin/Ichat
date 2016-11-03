package com.abrsoftware.ichat;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    Button registerBtn;
    TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textTitle = (TextView)findViewById(R.id.textTittle);
        textTitle.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/fjalla_on.otf"));
    }

    public void setToolbar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_login));
        setSupportActionBar(toolbar);
    }

}

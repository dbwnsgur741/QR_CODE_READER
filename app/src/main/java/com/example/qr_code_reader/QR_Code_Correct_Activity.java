package com.example.customlistview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.qr_code_reader.R;

public class QR_Code_Correct_Activity extends AppCompatActivity {

    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int code_index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.qr_code_correct );

        Toolbar toolbar = (Toolbar) findViewById( R.id.qr_code_correct_toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        sharedPreferences = getSharedPreferences( "NamSan",MODE_PRIVATE );
        editor = sharedPreferences.edit();

        imageButton1 = (ImageButton)findViewById( R.id.qr_code_correct_go_other_quiz);
        imageButton2 = (ImageButton)findViewById( R.id.qr_code_correct_go_main );
        Intent intent = getIntent();
        code_index = intent.getExtras().getInt( "code_index" );

        setAdd_Point();

        imageButton1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent( getApplicationContext(), com.example.customlistview.QR_Code_Default_Sub_Activity.class );
                startActivity( intent1 );
                finish();
            }
        } );

        imageButton2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    private void setAdd_Point(){
        int qr_code_manager = (int) Math.pow( 2,code_index );
        editor.putInt( "qr_code_manager",qr_code_manager+sharedPreferences.getInt( "qr_code_manager" ,0) );
        editor.putInt("myPoint",sharedPreferences.getInt( "myPoint" ,0)+200);
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

package joenut.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button btn1 = findViewById(R.id.button1);
        Button btn2= findViewById(R.id.button2);


        btn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent=new  Intent(MainMenu.this,SpeechToText.class);
                startActivity(intent);


            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent=new  Intent(MainMenu.this,GalWalActivity.class);
                startActivity(intent);


            }
        });
    }


    //Test
    //Test2


}

package joenut.myapplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {

    private Database db;
    private Button btn1;
    private Button btn2;
    private Button viewMemberBtn;
    private Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
//        viewMemberBtn = (Button) findViewById(R.id.view_member_btn);
        resetBtn = (Button) findViewById(R.id.button4);

        db = new Database(this);

        if(db.isEmpty()){
            btn1.setEnabled(false);
            resetBtn.setEnabled(false);
//            viewMemberBtn.setEnabled(false);
        }

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

//        viewMemberBtn.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                Intent intent = new  Intent(MainMenu.this, FamilyMember.class);
//                startActivity(intent);
//            }
//        });

        resetBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
               if( db.deleteAll()){
                   new AlertDialog.Builder(MainMenu.this)
                           .setTitle("สำเร็จ")
                           .setMessage("ลบข้อมูลเรียบร้อย")
                           .setIcon(android.R.drawable.ic_dialog_info)
                           .setPositiveButton("ตกลง", null).show();
               }else{
                   new AlertDialog.Builder(MainMenu.this)
                           .setTitle("ผิดพลาด")
                           .setMessage("ลบข้อมูลไม่สำเร็จ")
                           .setIcon(android.R.drawable.ic_dialog_alert)
                           .setPositiveButton("ตกลง", null).show();
               }

            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (!db.isEmpty()) {
            btn1.setEnabled(true);
            resetBtn.setEnabled(true);
//            viewMemberBtn.setEnabled(true);

        }else{
            btn1.setEnabled(false);
            resetBtn.setEnabled(false);
//            viewMemberBtn.setEnabled(false);
        }
    }
}

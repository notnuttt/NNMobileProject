package joenut.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class GalWalActivity extends AppCompatActivity {

    private ImageView selectedImage;
    private static final int PICK_IMAGE = 100;
    private final String IMAGE_DIRECTORY = "contents://media/internal/images/media";
    private EditText photoName;
    private Database db;
    private String cImagePath;
    private String cPersonName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gal_wal);

        selectedImage = (ImageView) findViewById(R.id.add_person_selectedImage);
        photoName = (EditText) findViewById(R.id.photoName);
        Button openGallery = (Button) findViewById(R.id.opengallery);
        Button addMemberBtn = (Button) findViewById(R.id.add_member_btn);
        db = new Database(this);

        openGallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        addMemberBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (db.insertImage(cImagePath, cPersonName)){
                    Toast.makeText(getApplicationContext(), "Successful!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Not successful!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            Uri uri = data.getData();
            String path = getPath(uri);
            cImagePath = path;
            String name = photoName.getText().toString();
            cPersonName = name;
            Toast.makeText(getApplicationContext(), path,Toast.LENGTH_SHORT).show();
            File imgFile = new  File(path);
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            selectedImage.setImageBitmap(myBitmap);
        }

    }


    public String getPath(Uri uri){
        if(uri == null) return null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection,null,null,null);
        if(cursor != null){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();

    }


}
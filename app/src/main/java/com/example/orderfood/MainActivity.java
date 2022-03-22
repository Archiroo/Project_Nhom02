package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TextView textView, textView1;
    EditText editText;
    ImageView imageView;
    Button button;
    MyDataBase DB;
    String nameDB;
    Bitmap imageDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        textView1 = (TextView) findViewById(R.id.textview1);
        editText = (EditText) findViewById(R.id.edittext);
        imageView = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.button);
        DB = new MyDataBase(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] img = byteArray.toByteArray();

                boolean insert = DB.insertdata(name, img);
                if(insert==true){
                    Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data Not Saved",
                            Toast.LENGTH_SHORT).show();
                }

                imageDB = DB.getImage(name);
                nameDB = DB.getName(name);
                imageView.setImageBitmap(imageDB);
                textView1.setText("The name entered by you is \n\n" + nameDB);
            }
        });
    }
}
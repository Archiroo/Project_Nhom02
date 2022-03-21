package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    // Khai báo biến
    TextView textview_Register, textview_forgot;
    EditText phonenumber, password;
    Button btn_Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//      Gán biến theo id trong giao diện
        textview_Register =findViewById(R.id.textViewSignUp);
        textview_forgot = findViewById(R.id.forgotPassword);

//       Chuyển sang giao diện đăng kí
        textview_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

//       Chuyển sang giao diện quên mật khẩu
        textview_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgotPassActivity.class));
            }
        });

//      Lấy giá trị biến trong giao diện
        phonenumber = (EditText) findViewById(R.id.input_Login_Phone);
        password = (EditText) findViewById(R.id.input_Login_Password);



    }
}

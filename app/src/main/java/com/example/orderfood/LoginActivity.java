package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    // Khai báo biến
    TextView textview_Register, textview_forgot;
    EditText phonenumber, password;
    Button Login_btnLogin;
    DBHelper database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//      Gán biến theo id trong giao diện
        textview_Register =findViewById(R.id.textViewSignUp);
        textview_forgot = findViewById(R.id.forgotPassword);
        phonenumber = findViewById(R.id.input_Login_Phone);
        password = findViewById(R.id.input_Login_Password);
        Login_btnLogin = findViewById(R.id.btnlogin);
        database = new DBHelper(this);


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

//      Viết sự kiện cho btn login
        Login_btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phonenumber.getText().toString();
                String pass = password.getText().toString();

                if(phone.equals("") || pass.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Bạn chưa nhập tài khoản hoặc mật khẩu.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean CheckAccount = database.Check_Account(phone, pass);
                    if(CheckAccount == true)
                    {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}

package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
//    Khai báo biến
    TextView textview_Login;
    EditText register_username, register_phone, register_pass, register_confirmpass;
    Button register_btn_register;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//      Chuyển sang giao diện đăng nhập
        textview_Login = findViewById(R.id.alreadyHaveAccount);
        textview_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

//      Lấy các giá trị cho biến ở giao diện
        register_username = (EditText) findViewById(R.id.input_Register_Username);
        register_phone = (EditText) findViewById(R.id.input_Register_Phone);
        register_pass = (EditText)  findViewById(R.id.input_Register_Pass);
        register_confirmpass = (EditText) findViewById(R.id.input_Register_ConfirmPass);
        register_btn_register = (Button) findViewById(R.id.btnRegister);
        database = new DBHelper(this);

//       Viết sự kiện cho button đăng kí
        register_btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = register_username.getText().toString();
                String phone = register_phone.getText().toString();
                String pass = register_pass.getText().toString();
                String repass = register_confirmpass.getText().toString();

                if(phone.equals("") || pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Bạn cần nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(repass)){
                        Boolean check_phonenumber = database.Check_Phonenumber(phone);
                        if(check_phonenumber==false){
                            Boolean insert = database.Insert_db_users(username, phone, pass);

                            if(insert==true)
                            {
                                Toast.makeText(RegisterActivity.this, "Đăng ký tài khoản thành công.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this, "Đăng ký tài khoản thất bại.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "Tài khoản này đã tồn tại, hãy quay lại trang đăng nhập.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}

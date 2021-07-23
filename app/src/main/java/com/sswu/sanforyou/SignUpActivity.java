package com.sswu.sanforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {

    //회원가입 버튼 : 회원가입을 완료하고 Map Fragment로 넘어감
    private Button btn_sign_up2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_sign_up2 = findViewById(R.id.btn_sign_up2);
        btn_sign_up2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MapFragment.class );
                startActivity(intent);
            }
        });
    }
}
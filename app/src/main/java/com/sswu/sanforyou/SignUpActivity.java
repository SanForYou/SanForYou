package com.sswu.sanforyou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증처리
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스
    private EditText mEtName, mEtEmail, mEtPwd, mEtPwd2; //회원가입 입력필드
    private Button btn_sign_up2, btn_cancel;  //회원가입 버튼, 돌아가기 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("SanForYou");

        mEtName = findViewById(R.id.et_name);
        mEtEmail = findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtPwd2 = findViewById(R.id.et_pwd2);

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {//취소 버튼 클릭 시 로그인 화면으로 돌아가기
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        btn_sign_up2 = findViewById(R.id.btn_sign_up2);
        btn_sign_up2.setOnClickListener(new View.OnClickListener() { //회원가입 버튼 클릭 시 회원가입 진행
            @Override
            public void onClick(View v) { //회원가입버튼 클릭 될때의 action

                //회원가입 처리 시작
                String strName = mEtName.getText().toString().trim();
                String strEmail = mEtEmail.getText().toString().trim();
                String strPwd = mEtPwd.getText().toString().trim();
                String strPwd2 = mEtPwd2.getText().toString().trim();

                if(!(strPwd.equals(strPwd2))){
                    Toast.makeText(SignUpActivity.this, "비밀번호를 정확히 입력하세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    System.out.println("여기까지 완료");

                    //mysql에 member 데이터 삽입 진행
                    sendRequest();

                    //FireBase Auth 진행
                    mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            System.out.println("여기까지 완료2");
                            if (task.isSuccessful()) {
                                System.out.println("isSuccesful~~");
                                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

                                UserAccount account = new UserAccount();
                                account.setName(strName);
                                account.setIdToken(firebaseUser.getUid());
                                account.setEmailId(firebaseUser.getEmail());
                                account.setPassword(strPwd);

                                System.out.println(strName + ", "+firebaseUser.getUid()+"," + firebaseUser.getEmail()+"," + strPwd);

                                //setValue : database에 insert!
                                mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
                                //firebase databse에 Display Name 추가하기
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(strName).build();
                                firebaseUser.updateProfile(profileUpdates);

                                Toast.makeText(SignUpActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignUpActivity.this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class );
                    startActivity(intent);
                }
            }
        });

    }

    public void sendRequest(){
        String url = "http://ec2-3-34-189-249.ap-northeast-2.compute.amazonaws.com/memberInsert.php";

        StringRequest request = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("insert error")) {
                    System.out.println("mysql: member register fail");
                } else {
                    System.out.println("mysql: member register succuess!");
                }
            }
        },
                new Response.ErrorListener() { //에러 발생시 호출될 리스너 객체
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();

                params.put("memberID", mEtEmail.getText().toString());
                params.put("memberName", mEtName.getText().toString());
                params.put("password", mEtPwd.getText().toString());
                return params;
            }
        };

        request.setShouldCache(false); //이전 결과 있어도 새로 요청하여 응답을 보여준다.
        AppHelper.requestQueue = Volley.newRequestQueue(this);// requestQueue 초기화 필수
        AppHelper.requestQueue.add(request);
    }
}
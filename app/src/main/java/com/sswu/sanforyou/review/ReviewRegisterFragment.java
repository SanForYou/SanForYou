package com.sswu.sanforyou.review;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sswu.sanforyou.AppHelper;
import com.sswu.sanforyou.R;
import android.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import com.sswu.sanforyou.MainActivity;

public class ReviewRegisterFragment extends Fragment {

    ReviewFragment reviewFragment = new ReviewFragment();

    private EditText mountainName;
    private EditText content;
    private RatingBar scope;
    private String userId;
    private Button registerButton;

    private FirebaseAuth mFirebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_register, container, false);


        mountainName = (EditText) view.findViewById(R.id.register_mountain_name);
        content = (EditText) view.findViewById(R.id.register_content);
        scope = (RatingBar) view.findViewById(R.id.register_ratingbar);
        registerButton = (Button) view.findViewById(R.id.register_button);

        //현재 로그인한 사용자를 writer로 띄우기
        mFirebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            userId= user.getEmail();
        } else {
            // No user is signed in
            System.out.println("user는 null");
        }

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sendRequest();

//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, reviewFragment).commit();
            }
        });

        return view;
    }

    public void sendRequest() {
        String url = "http://ec2-3-34-189-249.ap-northeast-2.compute.amazonaws.com/review_register.php";
        AppHelper.requestQueue = Volley.newRequestQueue(getActivity());

        StringRequest request = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("------------------------------response : <"+response+">");
                        if (response.equals("insert error")) {
                            System.out.println("insert fail");

                            showAlter();
                        } else {
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, reviewFragment).commit();

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

                mFirebaseAuth= FirebaseAuth.getInstance();
                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    params.put("writerID", user.getEmail());
                } else {
                    // No user is signed in
                    System.out.println("user는 null");
                }

                params.put("scope", Integer.toString((int) scope.getRating()));
                params.put("content", content.getText().toString());
                params.put("mountainName", mountainName.getText().toString());

                return params;
            }
        };

        request.setShouldCache(false); //이전 결과 있어도 새로 요청하여 응답을 보여준다.
        AppHelper.requestQueue.add(request);
    }

    private void showAlter(){
        AlertDialog.Builder alter = new AlertDialog.Builder(getActivity());
        alter.setTitle("error");
        alter.setMessage("산 이름이 정확하지 않습니다\n다시 시도해주십시오.");

        alter.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){
                // OK 버튼을 눌렸을 경우
                Toast.makeText(getActivity(),"Pressed OK",
                        Toast.LENGTH_SHORT).show();
            }
        });

        alter.show();
    }
}
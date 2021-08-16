package com.sswu.sanforyou.Info;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfoFragment extends Fragment {

    private ListView listview;
    private InfoAdapter adapter;
    private ArrayList<Info> infoArrayList;
    private ImageButton cart;
    private FirebaseAuth mFirebaseAuth;
    private TextView mountainName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        setHasOptionsMenu(true);

        listview = (ListView) view.findViewById(R.id.info_list_view);
        infoArrayList = new ArrayList<>();
        cart = (ImageButton) view.findViewById(R.id.bt_cart);
        mountainName = (TextView) view.findViewById(R.id.info_mountainName);

        if(AppHelper.requestQueue != null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }

        sendRequest();

        return view;
    }

    //산 정보 띄우기 sendRequest
    public void sendRequest() {
        String url = "http://ec2-3-34-189-249.ap-northeast-2.compute.amazonaws.com/mountainInfo.php";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("mountainInfo");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject item = jsonArray.getJSONObject(i);

                                String mountainName = item.getString("mountainName");
                                double latitude = item.getDouble("latitude");
                                double longitude = item.getDouble("longitude");
                                String address = item.getString("address");
                                String height = item.getString("height");
                                double scope = item.getDouble("scope");

                                Info informList = new Info(mountainName, scope, address, height, latitude, longitude);
                                infoArrayList.add(0, informList); //최신순으로 정렬
                            }
                            adapter = new InfoAdapter(getActivity(), infoArrayList);
                            listview.setAdapter(adapter);

                        } catch (JSONException e) {
                            System.out.println("--------" + e);
                        }
                    }
                },
                new Response.ErrorListener() { //에러 발생시 호출될 리스너 객체
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                return params;
            }
        };
        request.setShouldCache(false); //이전 결과 있어도 새로 요청하여 응답을 보여준다.
        AppHelper.requestQueue = Volley.newRequestQueue(getActivity());
        AppHelper.requestQueue.add(request);
    }

}
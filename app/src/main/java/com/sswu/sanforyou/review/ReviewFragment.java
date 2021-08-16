package com.sswu.sanforyou.review;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sswu.sanforyou.AppHelper;
import com.sswu.sanforyou.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReviewFragment extends Fragment {

    ReviewRegisterFragment fragment_review_register;

    private ListView listview;
    private ReviewAdapter adapter;
    private ArrayList<Review> reviews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_review, container, false);
        setHasOptionsMenu(true);

        listview = (ListView) view.findViewById(R.id.review_list_view);
        reviews = new ArrayList<>();

        if(AppHelper.requestQueue != null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }

        sendRequest();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.review_action_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId){
            case R.id.review_register_button:

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ReviewRegisterFragment reviewRegisterFragment = new ReviewRegisterFragment();
                fragmentTransaction.replace(R.id.container, reviewRegisterFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_review_register).commit();
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendRequest() {
        String url = "http://ec2-3-34-189-249.ap-northeast-2.compute.amazonaws.com/review.php";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        System.out.println(response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("reviews");

                            for(int i=0;i<jsonArray.length();i++){

                                JSONObject item = jsonArray.getJSONObject(i);

                                int reviewID = item.getInt("reviewID");
                                String writerID = item.getString("writerID");
                                int scope = item.getInt("scope");
                                String content = item.getString("content");
                                int likes = item.getInt("scope");
//                long images = item.getLong("images");
                                String mountainName = item.getString("mountainName");

                                Review review = new Review(reviewID, writerID, scope, content, likes, mountainName);

                                reviews.add(0, review); //최신순으로 정렬
                            }

                            adapter = new ReviewAdapter(reviews);
                            listview.setAdapter(adapter);
                            System.out.println("reviewList" + reviews);
                        } catch (JSONException e) {
                            System.out.println("---------------------------------------------" + e);
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
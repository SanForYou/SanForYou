package com.sswu.sanforyou.Info;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sswu.sanforyou.AppHelper;
import com.sswu.sanforyou.R;
import com.sswu.sanforyou.SignInActivity;
import com.sswu.sanforyou.review.Review;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfoAdapter extends BaseAdapter {
    Context context;
    ArrayList<Info> information;

    public InfoAdapter(Context context, ArrayList<Info> information) {
        this.information = information;
        this.context= context;
    }

    @Override
    public int getCount() {
        return information.size();
    }

    @Override
    public Object getItem(int position) {
        return information.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.info_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView mountainName = (TextView) convertView.findViewById(R.id.info_mountainName);
      //  TextView mountainURL = (TextView) convertView.findViewById(R.id.mountainImage);
        TextView scope = (TextView) convertView.findViewById(R.id.info_scope);
        TextView address = (TextView) convertView.findViewById(R.id.info_address);
        TextView latitude = (TextView) convertView.findViewById(R.id.info_latitude);
        TextView longitude = (TextView) convertView.findViewById(R.id.info_longitude);
        TextView height = (TextView) convertView.findViewById(R.id.info_height);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Info info = information.get(position);

        mountainName.setText(info.getMountainName());
      //  mountainURL.setText(info.getMountainURL());
        scope.setText(Double.toString(info.getScope()));
        address.setText(info.getAddress());
        latitude.setText(Double.toString(info.getLatitude()));
        longitude.setText(Double.toString(info.getLongitude()));
        height.setText(info.getHeight());

        /////산바구니 버튼
        ImageButton cart = (ImageButton) convertView.findViewById(R.id.bt_cart);
        cart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println("cart button click");
                cartRequest(info.getMountainName());
            }
        });

        return convertView;
    }

    //산바구니 담기 버튼 클릭시 실행되는 메서드
    public void cartRequest(String mountainName){
        String url = "http://ec2-3-34-189-249.ap-northeast-2.compute.amazonaws.com/insert_cart.php";
        AppHelper.requestQueue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
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
                FirebaseAuth mFirebaseAuth;
                mFirebaseAuth= FirebaseAuth.getInstance();
                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    params.put("memberID", user.getEmail());
                } else {
                    // No user is signed in
                    System.out.println("user는 null");
                }

                params.put("mountainName", mountainName);
                System.out.println("산바구니에 담을 산: "+mountainName);

                return params;
            }
        };

        request.setShouldCache(false); //이전 결과 있어도 새로 요청하여 응답을 보여준다.
        AppHelper.requestQueue.add(request);
    }

}
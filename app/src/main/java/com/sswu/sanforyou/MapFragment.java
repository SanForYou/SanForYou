package com.sswu.sanforyou;

import android.content.Context;
import android.content.SyncContext;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.DropBoxManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.MarkerIcons;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.android.volley.VolleyLog.TAG;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    Context context;
    private Marker marker = new Marker();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        com.naver.maps.map.MapFragment mapFragment =
                (com.naver.maps.map.MapFragment) getChildFragmentManager().findFragmentById(R.id.map_view);
        if (mapFragment == null) {
            mapFragment = com.naver.maps.map.MapFragment.newInstance();
            getChildFragmentManager().beginTransaction().add(R.id.map_view, mapFragment).commit();

        }
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        context = getActivity().getApplicationContext();
        //위도 얻어오기
        setupLat(context);
        setupLng(context);
        //경도 얻어오기
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

      /*  Marker marker = new Marker();
        //latitude, longitude에 값 받아서 넣기
        marker.setPosition(new LatLng(35.33945944691614, 127.72969658322664));
        marker.setIcon(OverlayImage.fromResource(R.drawable.marker_icon));
        marker.setWidth(100);
        marker.setHeight(100);
        marker.setMap(naverMap);*/
        //위치 및 각도 조정
        CameraPosition cameraPosition = new CameraPosition(
                new LatLng(37.5642135, 127.0016985),   // 위치 지정
                6,                                     // 줌 레벨
                0,                                       // 기울임 각도
                0                                     // 방향
        );
        naverMap.setCameraPosition(cameraPosition);
        //TODO: 마커아래 산 명칭 표시하기
    }


    // 위도 json 파싱

    public ArrayList<Double> setupLat(Context context) {
        String confirm = "confirm";
        //String id
        Log.d("함수호출확인", confirm);

        final double[] latGae = {0};       final double[] latGwan = {0};
        final double[] latDo = {0};        final double[] latBook = {0};
        final double[] latAh = {0};        final double[] latEung = {0};
        final double[] latIn = {0};        final double[] latJi = {0};

        ArrayList<Double> latList = new ArrayList<Double>();

        String url = "http://ec2-3-34-189-249.ap-northeast-2.compute.amazonaws.com/mountainStamp.php?writerID=%27testuser%27";
        //?writerID=" + id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                   Log.d("StringResponse", confirm);
                        String strResp = response;

                        try {
                            Log.d("try확인", confirm);
                            JSONArray jsonArray = new JSONArray(strResp);
                            for (int i = 0; i < jsonArray.length(); i++) {

                                Log.d("for문 속 확인", confirm);

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String mountainName = jsonObject.getString("mountainName");
                                String latitude = jsonObject.getString("latitude");

                                Log.d("latitude 확인", latitude);

                                if (mountainName.equals("계룡산")) latGae[0] += Double.parseDouble(latitude);
                                else if (mountainName.equals("관악산")) latGwan[0] += Double.parseDouble(latitude);
                                else if (mountainName.equals("도봉산")) latDo[0] += Double.parseDouble(latitude);
                                else if (mountainName.equals("북한산")) latBook[0] += Double.parseDouble(latitude);
                                else if (mountainName.equals("아차산")) latAh[0] += Double.parseDouble(latitude);
                                else if (mountainName.equals("응봉산")) latEung[0] += Double.parseDouble(latitude);
                                else if (mountainName.equals("인왕산")) latIn[0] += Double.parseDouble(latitude);
                                else if (mountainName.equals("지리산")) latJi[0] += Double.parseDouble(latitude);

                                latList.add(latGae[0]);       latList.add(latGwan[0]);
                                latList.add(latDo[0]);        latList.add(latBook[0]);
                                latList.add(latAh[0]);        latList.add(latEung[0]);
                                latList.add(latIn[0]);        latList.add(latJi[0]);

                                System.out.println("latList: " + latList);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "위도 조회 실패", Toast.LENGTH_SHORT).show();
                Log.d("tag", "OnErrorResponse"+String.valueOf(error));
            }
        }) {
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

        return latList;

    }

    //경도 json 파싱
    public ArrayList<Double> setupLng(Context context) {
        ArrayList<Double> lngList = new ArrayList<Double>();

        String url = "http://ec2-3-34-189-249.ap-northeast-2.compute.amazonaws.com/mountainStamp.php?writerID=%27testuser%27";
        //?writerID=" + id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    final double[] lngGae = {0};
                    final double[] lngGwan = {0};
                    final double[] lngDo = {0};
                    final double[] lngBook = {0};
                    final double[] lngAh = {0};
                    final double[] lngEung = {0};
                    final double[] lngIn = {0};
                    final double[] lngJi = {0};

                    @Override
                    public void onResponse(String response) {
                        String strResp = response;

                        try {
                            JSONArray jsonArray = new JSONArray(strResp);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String mountainName = jsonObject.getString("mountainName");
                                String longitude = jsonObject.getString("longitude");


                                if (mountainName.equals("계룡산")) lngGae[0] += Double.parseDouble(longitude);
                                else if (mountainName.equals("관악산")) lngGwan[0] += Double.parseDouble(longitude);
                                else if (mountainName.equals("도봉산")) lngDo[0] += Double.parseDouble(longitude);
                                else if (mountainName.equals("북한산")) lngBook[0] += Double.parseDouble(longitude);
                                else if (mountainName.equals("아차산")) lngAh[0] += Double.parseDouble(longitude);
                                else if (mountainName.equals("응봉산")) lngEung[0] += Double.parseDouble(longitude);
                                else if (mountainName.equals("인왕산")) lngIn[0] += Double.parseDouble(longitude);
                                else if (mountainName.equals("지리산")) lngJi[0] += Double.parseDouble(longitude);

                                lngList.add(lngGae[0]);
                                lngList.add(lngGwan[0]);
                                lngList.add(lngDo[0]);
                                lngList.add(lngBook[0]);
                                lngList.add(lngAh[0]);
                                lngList.add(lngEung[0]);
                                lngList.add(lngIn[0]);
                                lngList.add(lngJi[0]);
                                System.out.println("lngList확인" + lngList);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "경도 조회 실패", Toast.LENGTH_SHORT).show();
            }
        }) {
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

        return lngList;

    }

}
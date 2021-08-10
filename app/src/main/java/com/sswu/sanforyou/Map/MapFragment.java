package com.sswu.sanforyou.Map;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.sswu.sanforyou.MySingleton;
import com.sswu.sanforyou.R;
import com.sswu.sanforyou.review.Review;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    Context context;

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
/*        setupLat(context);
        setupLng(context);*/
        //경도 얻어오기
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

          setMarker(context, naverMap);

         CameraPosition cameraPosition = new CameraPosition(
                new LatLng(37.5642135, 127.0016985),   // 위치 지정
                6,                                     // 줌 레벨
                0,                                       // 기울임 각도
                0                                     // 방향
        );
        naverMap.setCameraPosition(cameraPosition);

    }


    public void setMarker(Context context, @NonNull NaverMap naverMap) {

        ArrayList<Double> latList = new ArrayList<Double>();
        ArrayList<Double> lngList = new ArrayList<>();

        String confirm = "confirm";
        //String id

        final double[] latGae = {0};
        final double[] latGwan = {0};
        final double[] latDo = {0};
        final double[] latBook = {0};
        final double[] latAh = {0};
        final double[] latEung = {0};
        final double[] latIn = {0};
        final double[] latJi = {0};

        final double[] lngGae = {0};
        final double[] lngGwan = {0};
        final double[] lngDo = {0};
        final double[] lngBook = {0};
        final double[] lngAh = {0};
        final double[] lngEung = {0};
        final double[] lngIn = {0};
        final double[] lngJi = {0};




        String url = "http://ec2-3-34-189-249.ap-northeast-2.compute.amazonaws.com/mountainStamp.php?writerID=%27jiwon%27";
        //?writerID=" + id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("StringResponse", confirm);
                        String strResp = response;

                        try {

                            JSONArray jsonArray = new JSONArray(strResp);
                            for (int i = 0; i < jsonArray.length(); i++) {


                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String mountainName = jsonObject.getString("mountainName");
                                String latitude = jsonObject.getString("latitude");
                                String longitude = jsonObject.getString("longitude");


                                if (mountainName.equals("계룡산")) {
                                    latGae[0] += Double.parseDouble(latitude);
                                    lngGae[0] += Double.parseDouble(longitude);
                                } else if (mountainName.equals("관악산")) {
                                    latGwan[0] += Double.parseDouble(latitude);
                                    lngGwan[0] += Double.parseDouble(longitude);
                                } else if (mountainName.equals("도봉산")) {
                                    latDo[0] += Double.parseDouble(latitude);
                                    lngDo[0] += Double.parseDouble(longitude);
                                } else if (mountainName.equals("북한산")) {
                                    latBook[0] += Double.parseDouble(latitude);
                                    lngBook[0] += Double.parseDouble(longitude);
                                } else if (mountainName.equals("아차산")) {
                                    latAh[0] += Double.parseDouble(latitude);
                                    lngAh[0] += Double.parseDouble(longitude);
                                } else if (mountainName.equals("응봉산")) {
                                    latEung[0] += Double.parseDouble(latitude);
                                    lngEung[0] += Double.parseDouble(longitude);
                                } else if (mountainName.equals("인왕산")) {
                                    latIn[0] += Double.parseDouble(latitude);
                                    lngIn[0] += Double.parseDouble(longitude);
                                } else if (mountainName.equals("지리산")) {
                                    latJi[0] += Double.parseDouble(latitude);
                                    lngJi[0] += Double.parseDouble(longitude);
                                }
                            }
                            latList.add(latGae[0]);
                            latList.add(latGwan[0]);
                            latList.add(latDo[0]);
                            latList.add(latBook[0]);
                            latList.add(latAh[0]);
                            latList.add(latEung[0]);
                            latList.add(latIn[0]);
                            latList.add(latJi[0]);

                            lngList.add(lngGae[0]);
                            lngList.add(lngGwan[0]);
                            lngList.add(lngDo[0]);
                            lngList.add(lngBook[0]);
                            lngList.add(lngAh[0]);
                            lngList.add(lngEung[0]);
                            lngList.add(lngIn[0]);
                            lngList.add(lngJi[0]);


                            while (latList.remove(Double.valueOf(0.0))) {
                            };

                            while (lngList.remove(Double.valueOf(0.0))) {

                            };


                                List<Marker> markers = new ArrayList<>();

                                for(int i = 0 ; i<latList.size();i++) {
                                    Marker marker = new Marker();
                                    double lat = latList.get(i);
                                    double lng = lngList.get(i);
                                    marker.setPosition(new LatLng(lat, lng));
                                    marker.setIcon(OverlayImage.fromResource(R.drawable.marker_icon));
                                    markers.add(marker);
                                }

                                    for(Marker marker: markers) {
                                        marker.setMap(naverMap);
                                    }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "위도 조회 실패", Toast.LENGTH_SHORT).show();
                Log.d("tag", "OnErrorResponse" + String.valueOf(error));
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }
}

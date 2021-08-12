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
                new LatLng(37.66147762135974, 126.99316130211811),   // 위치 지정
                9,                                     // 줌 레벨
                0,                                       // 기울임 각도
                0                                     // 방향
        );
        naverMap.setCameraPosition(cameraPosition);
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_MOUNTAIN,true);
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING,false);
        naverMap.setNightModeEnabled(true);
    }


    public void setMarker(Context context, @NonNull NaverMap naverMap) {

        ArrayList<Double> latList = new ArrayList<Double>();
        ArrayList<Double> lngList = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();

        String confirm = "confirm";
        //String id

        final double[] lat1 = {0};
        final double[] lat2 = {0};
        final double[] lat3 = {0};
        final double[] lat4 = {0};
        final double[] lat5 = {0};
        final double[] lat6 = {0};
        final double[] lat7 = {0};
        final double[] lat8 = {0};
        final double[] lat9 = {0};
        final double[] lat10 = {0};
        final double[] lat11 = {0};

        final double[] lng1 = {0};
        final double[] lng2 = {0};
        final double[] lng3 = {0};
        final double[] lng4 = {0};
        final double[] lng5 = {0};
        final double[] lng6 = {0};
        final double[] lng7 = {0};
        final double[] lng8 = {0};
        final double[] lng9 = {0};
        final double[] lng10 = {0};
        final double[] lng11 = {0};

        final String[] name1 = new String[0];




        String url = "http://ec2-3-34-189-249.ap-northeast-2.compute.amazonaws.com/mountainStamp.php?writerID=%27testuser%27";
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
                                    lat1[0] += Double.parseDouble(latitude);
                                    lng1[0] += Double.parseDouble(longitude);
                                    nameList.add("계룡산");
                                } else if (mountainName.equals("관악산")) {
                                    lat2[0] += Double.parseDouble(latitude);
                                    lng2[0] += Double.parseDouble(longitude);
                                    nameList.add("관악산");
                                } else if (mountainName.equals("도봉산")) {
                                    lat3[0] += Double.parseDouble(latitude);
                                    lng3[0] += Double.parseDouble(longitude);
                                    nameList.add("도봉산");
                                } else if (mountainName.equals("북한산")) {
                                    lat4[0] += Double.parseDouble(latitude);
                                    lng4[0] += Double.parseDouble(longitude);
                                    nameList.add("북한산");
                                } else if (mountainName.equals("아차산")) {
                                    lat5[0] += Double.parseDouble(latitude);
                                    lng5[0] += Double.parseDouble(longitude);
                                    nameList.add("아차산");
                                } else if (mountainName.equals("응봉산")) {
                                    lat6[0] += Double.parseDouble(latitude);
                                    lng6[0] += Double.parseDouble(longitude);
                                    nameList.add("응봉산");
                                } else if (mountainName.equals("인왕산")) {
                                    lat7[0] += Double.parseDouble(latitude);
                                    lng7[0] += Double.parseDouble(longitude);
                                    nameList.add("인왕산");
                                } else if (mountainName.equals("매봉산")) {
                                    lat8[0] += Double.parseDouble(latitude);
                                    lng8[0] += Double.parseDouble(longitude);
                                    nameList.add("매봉산");
                                } else if (mountainName.equals("봉화산")) {
                                    lat9[0] += Double.parseDouble(latitude);
                                    lng9[0] += Double.parseDouble(longitude);
                                    nameList.add("봉화산");
                                }else if (mountainName.equals("수락산")) {
                                    lat10[0] += Double.parseDouble(latitude);
                                    lng10[0] += Double.parseDouble(longitude);
                                    nameList.add("수락산");
                                }else if (mountainName.equals("안산")) {
                                    lat11[0] += Double.parseDouble(latitude);
                                    lng11[0] += Double.parseDouble(longitude);
                                    nameList.add("안산");
                                }
                            }
                            latList.add(lat1[0]);
                            latList.add(lat2[0]);
                            latList.add(lat3[0]);
                            latList.add(lat4[0]);
                            latList.add(lat5[0]);
                            latList.add(lat6[0]);
                            latList.add(lat7[0]);
                            latList.add(lat8[0]);

                            lngList.add(lng1[0]);
                            lngList.add(lng2[0]);
                            lngList.add(lng3[0]);
                            lngList.add(lng4[0]);
                            lngList.add(lng5[0]);
                            lngList.add(lng6[0]);
                            lngList.add(lng7[0]);
                            lngList.add(lng8[0]);



                            while (latList.remove(Double.valueOf(0.0))) {
                            };

                            while (lngList.remove(Double.valueOf(0.0))) {

                            };

                            System.out.println("nameList :" +nameList);


                                List<Marker> markers = new ArrayList<>();

                                for(int i = 0 ; i<latList.size();i++) {
                                    double lat = latList.get(i);
                                    double lng = lngList.get(i);
                                    Marker marker = new Marker();

                                    marker.setPosition(new LatLng(lat, lng));
                                    marker.setCaptionText(nameList.get(i));
                                    marker.setIcon(OverlayImage.fromResource(R.drawable.marker));
                                    marker.setWidth(Marker.SIZE_AUTO);
                                    marker.setHeight(Marker.SIZE_AUTO);
                                    marker.setIconPerspectiveEnabled(true);
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

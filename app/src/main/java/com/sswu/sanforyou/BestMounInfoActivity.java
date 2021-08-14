package com.sswu.sanforyou;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

/*산 상세정보 액티비티*/
public class BestMounInfoActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String INFO_SEQ = "INFO_SEQ";
    Context context;
    String memberID;
    String mountainName;

    MounInfoItem item;
    ImageView keepImage;

    /*산 정보를 보여주기 위해 사용자 이름과 산 정보 이름을 얻고 이를 기반으로 서버에서 산 정보를 조회하는 메소드를 호출*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info2);

        context = this;

        memberID = ((MyApp)getApplication()).getMemberID();
        mountainName = getIntent().getStringExtra(INFO_SEQ, 0);
        selectMounInfo(mountainName, memberID);
    }

    /*서버에서 산 정보 조회*/
    private void selectMounInfo(String mountainName, String memberID) {

    }

    /*서버에서 조회한 산 정보를 화면에 설정*/
    private void setView() {
        getSupportActionBar().setTitle(item.mountainName);

        ImageView infoImage = (ImageView) findViewById(R.id.mountainImage);
        setImage(infoImage, item.fileName);

        TextView nameText = (TextView) findViewById(R.id.mountainName);
        if (!StringLib.getInstance().isBlank(item.mountainName)) {
            nameText.setText(item.mountainName);
        }

        keepImage = (ImageView) findViewById(R.id.keep);
        keepImage.setOnClickListener(this);
        if (item.isKeep) {
            keepImage.setImageResource(R.drawable.filledhearticon);
        } else {
            keepImage.setImageResource(R.drawable.unfilledhearticon);
        }

        TextView score = (TextView) findViewById(R.id.score);
        if (!StringLib.getInstance().isBlank(item.score)) {
            score.setText(item.score);
        } else {
            score.setVisibility(View.GONE);
        }

        TextView address = (TextView) findViewById(R.id.address);
        if (!StringLib.getInstance().isBlank(item.address)) {
            address.setText(item.address);
        } else {
            address.setVisibility(View.GONE);
        }

        TextView height = (TextView) findViewById(R.id.height);
        if (!StringLib.getInstance().isBlank(item.height)) {
            address.setText(item.height);
        } else {
            address.setVisibility(View.GONE);
        }

        TextView latitude = (TextView) findViewById(R.id.latitude);
        if (!StringLib.getInstance().isBlank(item.latitude)) {
            address.setText(item.latitude);
        } else {
            address.setVisibility(View.GONE);
        }

        TextView longitude = (TextView) findViewById(R.id.longitude);
        if (!StringLib.getInstance().isBlank(item.longitude)) {
            address.setText(item.longitude);
        } else {
            address.setVisibility(View.GONE);
        }
    }

    /*즐겨찾기 버튼 동작*/
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.keep) {
            if (item.isKeep) {
                DialogLib.getInstance().showKeepDeleteDialog(context, keepHandler, memberID, item.mountainName);
                keepImage.setImageResource(R.drawable.hearticon);
            } else {
                DialogLib.getInstance().showKeepInsertDialog(context, keepHandler, memberID, item.mountainName);
            }
        }
    }

    Handler keepHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            item.isKeep = !item.isKeep;

            if (item.isKeep) {
                keepImage.setImageResource(R.drawable.filledhearticon);
            } else {
                keepImage.setImageResource(R.drawable.unfilledhearticon);
            }
        }
    };

    /*mountain1 수정필요*/
    /*산 이미지를 화면에 보여줌*/
    private void setImage(ImageView imageView, String fileName) {
        if (StringLib.getInstance().isBlank(fileName)){
            Picasso.get().load(R.drawable.mountain1).into(imageView);
        } else {
            Picasso.get().load(RemoteService.IMAGE_URL + fileName).into(imageView);
        }
    }

    protected void onPause() {
        super.onPause();
        ((MyApp) getApplication()).setMounInfoItem(item);
    }

}

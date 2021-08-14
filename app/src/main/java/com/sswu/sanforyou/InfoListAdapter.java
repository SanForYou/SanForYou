package com.sswu.sanforyou;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.ViewHolder> {

    private final String TAG = this.getClass().getSimpleName();
    private ArrayList<MounInfoItem> itemList;
    private Context context;
    private  int resource;
    private MemberInfoItem memberInfoItem;

    public InfoListAdapter(Context context, int resource, ArrayList<MounInfoItem> itemList) {
        this.context = context;
        this.resource = resource;
        this.itemList = itemList;

        memberInfoItem = ((MyApp) context.getApplicationContext()).getMemberInfoItem();
    }

    /*툭정 아이템의 변경사항을 적용하기 위해 기본 아이템을 새로운 아이템으로 변경*/
    public void setItem (MounInfoItem newItem) {
        for (int i=0; i < itemList.size(); i++) {
            MounInfoItem item = itemList.get(i);

            if (item.mountainName == newItem.mountainName) {
                itemList.set(i, newItem);
                notifyItemChanged(i);
                break;
            }
        }
    }

    /*현재 아이템 리스트에 새로운 아이템 리스트 추가*/
    public void addItemList(ArrayList<MounInfoItem> itemList) {
        this.itemList.addAll(itemList);
        notifyDataSetChanged();
    }

    /*즐겨찾기 상태 변경*/
    private void changeItemKeep(int mountainSeq, boolean keep) {
        for  (int i=0; i < itemList.size(); i++) {
            if (itemList.get(i).mountainSeq == mountainSeq) {
                itemList.get(i).isKeep = keep;
                notifyItemChanged(i);
                break;
            }
        }
    }

    /*아이템 크기 반환*/
    public int getItemCount() {
        return this.itemList.size();
    }

    /*뷰홀더를 생성하기 위해 자동으로 호출*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new ViewHolder(v);
    }

    /*뷰홀더와 아이템을 리스트 위치에 따라 연동*/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MounInfoItem item = itemList.get(position);
        MyLog.d(TAG, "getView " +item);

        if (item.isKeep) {
            holder.keep.setImageResource(R.drawable.filledhearticon);
        } else {
            holder.keep.setImageResource(R.drawable.unfilledhearticon);
        }

        holder.mountainName.setText(item.mountainName);

        setImage(holder.image, item.fileName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoLib.getInstance().goBestMounInfoActivity(context, item.mountainName);
            }
        });

        holder.keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.isKeep) {
                    DialogLib.getInstance().showKeepDeleteDialog(context, keepDeleteHandler, memberInfoItem.memberID, item.mountainName);
                } else {
                    DialogLib.getInstance().showKeepInsertDialog(context, keepInsertHandler, memberInfoItem.memberID, item.mountainName);
                }
            }
        });
    }

    /*이미지 설정*/
    private void setImage(ImageView imageView, String fileName) {
        if(StringLib.getInstance().isBlank(fileName)) {
            Picasso.get().load(R.drawable.).into(imageView);
        } else {
            Picasso.get().load(RemoteService.IMAGE_URL + fileName).into(imageView);
        }
    }

    /*즐겨찾기 추가가 성공한 경우를 처리하는 핸들러*/
    android.os.Handler keepInsertHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            changeItemKeep(msg.what, true);
        }
    };

    /*즐겨찾기 삭제가 성공한 경우를 처리하는 핸들러*/
    Handler keepDeleteHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            changeItemKeep(msg.what, false);
        }
    };

    /*아이템을 보여주기 위한 뷰홀더 클래스*/
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView mountainName;
        ImageView keep;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);
            mountainName = (TextView) itemView.findViewById(R.id.mountainName);
            keep = (ImageView) itemView.findViewById(R.id.keep);
        }
    }

}
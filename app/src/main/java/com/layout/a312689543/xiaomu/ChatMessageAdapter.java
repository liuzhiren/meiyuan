package com.layout.a312689543.xiaomu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.text.SimpleDateFormat;
import java.util.List;

import bean.ChatMessage;

/**
 * Created by a312689543 on 2016/3/29.
 */
public class ChatMessageAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List <ChatMessage> mDatas;
    public ChatMessageAdapter(Context context,List <ChatMessage> mDatas){
    mInflater=LayoutInflater.from(context);
        this.mDatas=mDatas;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public int getItemViewType(int position){
        ChatMessage chatMessage=mDatas.get(position);

        if(chatMessage.getType()== ChatMessage.Type.INCOMING){
            return 0;
        }
         return 1;
    }
    public int getViewTypeCount(){
        return 2;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessage=mDatas.get(position);
        ViewHolder viewHolder=null;
        if(convertView==null){
            //通过ItemType实现不同的布局
             if(getItemViewType(position)==0){
                 convertView=mInflater.inflate(R.layout.acceptmseeage,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.mDatas= (TextView) convertView.findViewById(R.id.accept_date);
            viewHolder.mMsg= (TextView) convertView.findViewById(R.id.accept_msg);}

        else {

                 convertView = mInflater.inflate(R.layout.sendmessage, parent, false);
                 viewHolder = new ViewHolder();
                 viewHolder.mDatas = (TextView) convertView.findViewById(R.id.send_date);
                 viewHolder.mMsg = (TextView) convertView.findViewById(R.id.send_msg);

             }
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
//设置数据
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        viewHolder.mDatas.setText(df.format(chatMessage.getDate()));
        viewHolder.mMsg.setText(chatMessage.getMsg());
        return convertView;
    }
    private final class ViewHolder{
        TextView mDatas;
        TextView mMsg;
    }
}

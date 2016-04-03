package com.layout.a312689543.xiaomu;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.LogRecord;
import bean.ChatMessage;
import gongjubao.Httputils;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawableResource(R.drawable.meiyuanyuan);
        initView();
        initDatas();
        initListener();
        inputMsg.postDelayed(new Runnable() {
            public void run() {
                mMsg.setSelection(mDatas.size() - 1);
            }
        }, 100);
    }

    private void initListener() {

       sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String toMsg = inputMsg.getText().toString();
                if (TextUtils.isEmpty(toMsg)) {
                    Toast.makeText(MainActivity.this, "发送消息不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                ChatMessage toMessage=new ChatMessage();
                toMessage.setDate(new Date());
                toMessage.setType(ChatMessage.Type.OUTCOMING);
                toMessage.setMsg(toMsg);
                mDatas.add(toMessage);
                mAdapter.notifyDataSetChanged();
                mMsg.setSelection(mDatas.size()-1);
                inputMsg.setText("");
                new Thread() {
                    @Override
                    public void run() {
                        ChatMessage formdMessage = Httputils.sendMessage(toMsg);
                        Message m=Message.obtain();
                        m.obj=formdMessage;
                        mHandler.sendMessage(m);
                    };
                }.start();
            }
        });
    }


    private void initDatas() {
        mDatas=new ArrayList<ChatMessage>();
        mAdapter =new ChatMessageAdapter(this,mDatas);
        mDatas.add(new ChatMessage("你好啊", ChatMessage.Type.INCOMING,new Date()));
        mMsg.setAdapter(mAdapter);
    }

    private void initView() {
        mMsg= (ListView) findViewById(R.id.ly_ListView);
        inputMsg= (EditText) findViewById(R.id.input_msg);
        sendMsg= (Button) findViewById(R.id.send_msg_button);
    }

    private ListView mMsg;
    private ChatMessageAdapter mAdapter;
    private List<ChatMessage> mDatas;
    private EditText inputMsg;
    private Button sendMsg;
    private Handler mHandler=new Handler() {

        public void handleMessage(android.os.Message msg){
            ChatMessage formMessage=(ChatMessage) msg.obj;
            mDatas.add(formMessage);
            mAdapter.notifyDataSetChanged();
            mMsg.setSelection(mDatas.size()-1);

        };
    };


}

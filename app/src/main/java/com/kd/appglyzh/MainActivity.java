package com.kd.appglyzh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.kd.appglyzh.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
     ActivityMainBinding binding;
     Typeface fontFace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        fontFace = Typeface.createFromAsset(getAssets(), "fonts/simsun.ttc");
        binding.setType(fontFace);
        EventBus.getDefault().register(this);
        start();
    }
    private void start(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getInfo();
            }
        },0,15*60*1000);
    }
    private void getInfo() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://115.220.4.68:8081/qxdata/QxService.svc/getqxo2data/20188001").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("TAG",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                   String iss =  response.body().string();
                   if(!TextUtils.isEmpty(iss)&&iss.length()>5){
                       Gson gson = new Gson();
                       Info info  = gson.fromJson(iss,Info.class);
                       EventBus.getDefault().post(info);
                   }else{
                       Log.i("TAG","获取到的数据为空，检查原地址。。。。。。。。。。。");
                   }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reciverInfo(Info info) {
        binding.setInfo(info);
    }
}

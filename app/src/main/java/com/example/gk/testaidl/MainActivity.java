package com.example.gk.testaidl;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * http://blog.csdn.net/qq_30379689/article/details/52253413
 * 两个工程
 * 先开启服务端的service
 * 然后再打开客户端绑定service 传送数据
 **/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startServer(View view) {
        Intent intent = new Intent(this, MyService.class);
        ComponentName componentName = startService(intent);
        Log.e("test", "服务开启状态:" + (componentName != null ? "成功" : "失败"));
    }

    public void stopServer(View view) {
        Intent intent = new Intent(this, MyService.class);
        boolean result = stopService(intent);
        Log.e("test", "服务关闭状态:" + (result ? "成功" : "失败"));
    }
}

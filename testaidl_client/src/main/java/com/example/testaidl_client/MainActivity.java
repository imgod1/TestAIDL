package com.example.testaidl_client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gk.testaidl.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface iMyAidlInterface;
    private EditText etxt_a;
    private EditText etxt_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initService();
        //进来就绑定服务端的服务
    }

    private void initService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.gk.testaidl", "com.example.gk.testaidl.MyService"));
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    private void initViews() {
        etxt_a = (EditText) findViewById(R.id.etxt_a);
        etxt_b = (EditText) findViewById(R.id.etxt_b);
    }

    public void onClick(View view) {
        if (isDataOk()) {
            int a = getNumberFromEditText(etxt_a);
            int b = getNumberFromEditText(etxt_b);
            try {
                int sum = iMyAidlInterface.add(a, b);
                Log.e("test", "client onClick result:" + sum);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "数据不能为空", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isDataOk() {
        String string_a = etxt_a.getText().toString();
        String string_b = etxt_b.getText().toString();
        if (!TextUtils.isEmpty(string_a) && !TextUtils.isEmpty(string_b)) {
            return true;
        }
        return false;
    }

    private int getNumberFromEditText(EditText editText) {
        String string = editText.getText().toString();
        if (!TextUtils.isEmpty(string)) {
            return Integer.parseInt(string);
        }
        return 0;
    }


    //连接通道
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("test", "client serviceConnection onServiceConnected");
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("test", "client serviceConnection onServiceDisconnected");
            iMyAidlInterface = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}

package com.example.gk.testaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 项目名称：TestAIDL
 * 类描述：
 * 创建人：gk
 * 创建时间：2017/4/5 16:08
 * 修改人：gk
 * 修改时间：2017/4/5 16:08
 * 修改备注：
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IBinder iBinder = new IMyAidlInterface.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int add(int a, int b) throws RemoteException {
            Log.e("test", "server iBinder add 收到请求,参数为:a:" + a + "\tb:" + b);
            return a + b;
        }
    };
}

package com.example.amsdemo.core;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.amsdemo.ProxyActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 主要功能是绕过系统AMS
 */
public final class AmsManager {

    public static void hookAMSService(final Context context) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        if (OSVersion.isAndroidOS29()) {
            Class<?> activityTaskManagerClass = Class.forName("android.app.ActivityTaskManager");
            //Reflective access to getService is forbidden when targeting API 29 and above
            //Method getServiceMethod = activityTaskManagerClass.getDeclaredMethod("getService");

            //拿到Binder机制的客户端
//            Object activityTaskManager = getServiceMethod.invoke(null);

            Field iActivityTaskManagerSingletonField = activityTaskManagerClass.getDeclaredField("IActivityTaskManagerSingleton");
            iActivityTaskManagerSingletonField.setAccessible(true);
            Object mActivityTaskManagerSingleton = iActivityTaskManagerSingletonField.get(null);
            Class mSingletonClass = Class.forName("android.util.Singleton");
            Object activityTaskManager = mSingletonClass.getDeclaredMethod("get").invoke(mActivityTaskManagerSingleton);


            Class mActivityTaskManagerClass = Class.forName("android.app.IActivityTaskManager");
            Object proxyInstance = Proxy.newProxyInstance(context.getClassLoader(), new Class[]{mActivityTaskManagerClass}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if ("startActivity".equals(method.getName())) {
                        Intent intent = new Intent(context, ProxyActivity.class);
                        Intent targetIntent = (Intent) args[2];
                        intent.putExtra("amsTest", targetIntent);
                        args[2] = intent;
                    }
                    return method.invoke(activityTaskManager, args);
                }
            });

//            Class mSingletonClass = Class.forName("android.util.Singleton");
            Field mInstanceField = mSingletonClass.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            mInstanceField.set(mActivityTaskManagerSingleton, proxyInstance);
        } else {
            Object mActivityManager = null;
            Object mActivityManagerSingleton = null;
            if (OSVersion.isAndroidOS_21_22_23_24_25()) {
                Class mActivityManagerClass = Class.forName("android.app.ActivityManagerNative");
                //拿函数 执行函数
                Method getDefaultMethod = mActivityManagerClass.getDeclaredMethod("getDefault");
                getDefaultMethod.setAccessible(true);
                //拿到Binder机制的客户端
                mActivityManager = getDefaultMethod.invoke(null);
                //拿成员变量
                Field gDefaultField = mActivityManagerClass.getDeclaredField("gDefault");
                gDefaultField.setAccessible(true);
                mActivityManagerSingleton = gDefaultField.get(null);
            } else if (OSVersion.isAndroidOS_26_27_28()) {
                Class mActivityManagerClass = Class.forName("android.app.ActivityManager");
                //拿函数 执行函数
                Method getServiceMethod = mActivityManagerClass.getMethod("getService");
                mActivityManager = getServiceMethod.invoke(null);

                Field iActivityManagerSingletonField = mActivityManagerClass.getDeclaredField("IActivityManagerSingleton");
                iActivityManagerSingletonField.setAccessible(true);
                mActivityManagerSingleton = iActivityManagerSingletonField.get(null);
            }
            Class mActivityManagerClass = Class.forName("android.app.IActivityManager");
            final Object finalActivityManager = mActivityManager;
            Object mActivityManagerProxy = Proxy.newProxyInstance(context.getClassLoader(), new Class[]{mActivityManagerClass}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if ("startActivity".equals(method.getName())) {
                        Intent intent = new Intent(context, ProxyActivity.class);
                        Intent targetIntent = (Intent) args[2];
                        intent.putExtra("amsTest", targetIntent);
                        args[2] = intent;
                    }
                    return method.invoke(finalActivityManager, args);
                }
            });
            Class mSingletonClass = Class.forName("android.util.Singleton");
            Field mInstanceField = mSingletonClass.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            mInstanceField.set(mActivityManagerSingleton, mActivityManagerProxy);
        }
    }


    public static void hookActivityThread(final Context context) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (OSVersion.isAndroidOS_21_22_23_24_25()) {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Field activityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            activityThreadField.setAccessible(true);
            Object activityThread = activityThreadField.get(null);

            Field mHField = activityThreadClass.getDeclaredField("mH");
            mHField.setAccessible(true);
            Handler mHandler = (Handler) mHField.get(activityThread);
            Field mCallbackField = Handler.class.getDeclaredField("mCallback");
            mCallbackField.setAccessible(true);
            mCallbackField.set(mHandler, new HookActivityThreadCallback_21_22_23_24_25());
        } else if (OSVersion.isAndroidOS_26_27_28()) {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Method mActivityThreadMethod = activityThreadClass.getMethod("currentActivityThread");
            Object activityThread = mActivityThreadMethod.invoke(null);

            Field mHField = activityThreadClass.getDeclaredField("mH");
            mHField.setAccessible(true);
            Handler mHandler = (Handler) mHField.get(activityThread);
            Field mCallbackField = Handler.class.getDeclaredField("mCallback");
            mCallbackField.setAccessible(true);
            mCallbackField.set(mHandler, new HookActivityThreadCallback_26_27_28());
        } else if (OSVersion.isAndroidOS29()) {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Method mActivityThreadMethod = activityThreadClass.getMethod("currentActivityThread");
            Object activityThread = mActivityThreadMethod.invoke(null);

            Field mHField = activityThreadClass.getDeclaredField("mH");
            mHField.setAccessible(true);
            Handler mHandler = (Handler) mHField.get(activityThread);
            Field mCallbackField = Handler.class.getDeclaredField("mCallback");
            mCallbackField.setAccessible(true);
            mCallbackField.set(mHandler, new HookActivityThreadCallback_26_27_28());
        }
    }

    private static class HookActivityThreadCallback_26_27_28 implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 159) {
                try {
                    Object clientTransaction = msg.obj;
                    Class<?> clientTransactionClass = Class.forName("android.app.servertransaction.ClientTransaction");
                    Field mActivityCallbacksField = clientTransactionClass.getDeclaredField("mActivityCallbacks");
                    mActivityCallbacksField.setAccessible(true);
                    List mActivityCallbacks = (List) mActivityCallbacksField.get(clientTransaction);
                    if (mActivityCallbacks == null || mActivityCallbacks.size() == 0) {
                        return false;
                    }
                    Object launchActivityItem = mActivityCallbacks.get(0);
                    Class<?> launchActivityItemClass = Class.forName("android.app.servertransaction.LaunchActivityItem");
                    if (!launchActivityItemClass.isInstance(launchActivityItem)) {
                        return false;
                    }
                    Field mIntentField = launchActivityItemClass.getDeclaredField("mIntent");
                    mIntentField.setAccessible(true);
                    Intent proxyIntent = (Intent) mIntentField.get(launchActivityItem);
                    Intent targetIntent = proxyIntent.getParcelableExtra("amsTest");
                    if (targetIntent != null) {
                        mIntentField.set(launchActivityItem, targetIntent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            final ClientTransaction transaction = (ClientTransaction) msg.obj;
//            mTransactionExecutor.execute(transaction);
            return false;
        }
    }


    private static class HookActivityThreadCallback_21_22_23_24_25 implements Handler.Callback {

        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (100 == msg.what) {
                Object activityClientRecord = msg.obj;
                try {
                    Field intentField = activityClientRecord.getClass().getDeclaredField("intent");
                    intentField.setAccessible(true);
                    Intent intent = (Intent) intentField.get(activityClientRecord);
                    Intent targetIntent = intent.getParcelableExtra("amsTest");
                    if (targetIntent != null) {
                        intentField.set(activityClientRecord, targetIntent);
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }

}

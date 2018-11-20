package com.smart.desktop.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.common.utils.ToastUtils;
import com.github.moduth.blockcanary.BlockCanary;
import com.smart.desktop.BuildConfig;
import com.smart.desktop.common.executor.JobExecutor;
import com.smart.desktop.core.api.ApiRepository;
import com.squareup.leakcanary.LeakCanary;

import java.util.concurrent.Executor;

/**
 * 应用参数初始化
 *
 * @author 谭忠扬-YuriTam
 * @time 2017年02月14日
 */
public class App extends Application {

    //全局上下文
    public static Context sContext;
    //全局线程池
    public static Executor sExecutor;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();
        sExecutor = new JobExecutor();

        if (BuildConfig.DEBUG){
            //UI检测
            BlockCanary.install(sContext, new AppBlockCanaryContext()).start();
            //内存泄漏检测
            LeakCanary.install(this);
        }else {
            //异常捕捉
//            ApiCrashHandler.getInstance().init(sContext);
        }
        //初始化提示工具类
        ToastUtils.init(sContext);
        //初始数据操作库
        ApiRepository.getInstance().initDataSource(sContext);
        //注册监听每个activity的生命周期,便于堆栈式管理
        registerActivityLifecycleCallbacks(mCallbacks);
    }

    //创建单例模式
    private static class SingletonHolder{
        private static final App INSTANCE = new App();
    }

    //获取单例
    public static App getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * Activity 生命周期回调
     */
    private ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            AppManager.getInstance().addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {}

        @Override
        public void onActivityResumed(Activity activity) {}

        @Override
        public void onActivityPaused(Activity activity) {}

        @Override
        public void onActivityStopped(Activity activity) {}

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

        @Override
        public void onActivityDestroyed(Activity activity) {
            AppManager.getInstance().removeActivity(activity);
        }
    };
}

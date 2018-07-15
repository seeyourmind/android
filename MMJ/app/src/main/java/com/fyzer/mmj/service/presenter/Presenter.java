package com.fyzer.mmj.service.presenter;

import android.content.Intent;
import android.view.View;

/**
 * Created by XANXUS on 2017/11/21.
 */

public interface Presenter {
    // 对应于Activity的生命周期
    void onCreate();
    void onStop();
    // 绑定视图
    void attachView(View view);
    // 绑定Intent
    void attachIncomingIntent(Intent intent);
}

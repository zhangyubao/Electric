package com.lutai.electric;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lutai.electric.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by zhangYB on 2016/6/23.
 */
public class FlashActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadMain();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_flash;
    }


    private void loadMain() {
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        Logger.i("开启界面");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.i(e.getMessage() + "消息发送失败");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Intent intent = new Intent(mContext, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}

package com.weixiao.rxandroidpro;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Observable;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_root_view)
    RelativeLayout mRootView;

    @Bind(R.id.main_thread)
    Button mThreadButton;

    @Bind(R.id.main_async)
    Button mAsyncButton;

    @Bind(R.id.main_rx)
    Button mRxButton;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        // 线程运行
        mThreadButton.setOnClickListener(v -> {
            mThreadButton.setEnabled(false);
            longRunningOperation();
            Snackbar.make(mRootView, longRunningOperation(), Snackbar.LENGTH_INDEFINITE).show();
            mThreadButton.setEnabled(true);
        });

        // 异步运行
        mAsyncButton.setOnClickListener(v->{
            mAsyncButton.setEnabled(false);
            new MyAsyncTasks().execute();
        });

        // 使用IO线程处理, 主线程响应
        rx.Observable<String> observable = rx.Observable.create(new rx.Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(longRunningOperation());
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        // 响应式运行
        mRxButton.setOnClickListener(v->{
            mRxButton.setEnabled(false);
            observable.subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    mRxButton.setEnabled(true);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    Snackbar.make(mRootView, s, Snackbar.LENGTH_LONG).show();
                }
            });
        });

    }


    // 异步线程
    private class MyAsyncTasks extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            Snackbar.make(mRootView, s, Snackbar.LENGTH_LONG).show();
            mAsyncButton.setEnabled(true);
        }

        @Override
        protected String doInBackground(Void... params) {
            return longRunningOperation();
        }
    }


    // 长时间运行的任务
    private String longRunningOperation() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            Log.e("DEBUG", e.toString());
        }

        return "Complete!";
    }
}

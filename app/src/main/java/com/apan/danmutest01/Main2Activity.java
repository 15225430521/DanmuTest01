package com.apan.danmutest01;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import master.flame.danmaku.controller.IDanmakuView;

import master.flame.danmaku.danmaku.model.android.DanmakuContext;

import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;



public class Main2Activity extends Activity {

    private IDanmakuView mDanmakuView;
    private BaseDanmakuParser mParser;
    private DanmakuContext mContext;
    private DanMuUtils danMuUtils;


    String videoPath = "/storage/emulated/0/DCIM/Camera/3a2c13fc3ebafc4995ac2a306fb29278.mp4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();

        danMuUtils = DanMuUtils.getInstance();
        danMuUtils.init(mDanmakuView, 10);


        findVideo();
    }


    public void onClick1(View view) {
        danMuUtils.addDanmaku(false, "普通弹幕");
    }

    public void onClick2(View view) {
        danMuUtils.addDanmaku2(true, "图文弹幕", R.drawable.heart);
    }

    public void initView(){
        mDanmakuView = findViewById(R.id.sv_danmaku);

    }

    public void findVideo(){
        // VideoView
        VideoView mVideoView = (VideoView) findViewById(R.id.videoview);
        if (mVideoView != null) {
            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mVideoView.setVideoPath(videoPath);
        }

    }



    @Override
    protected void onPause() {
        super.onPause();
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDanmakuView != null) {
            // dont forget release!
            mDanmakuView.release();
            mDanmakuView = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mDanmakuView != null) {
            mDanmakuView.release();
            mDanmakuView = null;
        }
    }




}


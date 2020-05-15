package kiz.learnwithvel.lawofclassroom.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import kiz.learnwithvel.lawofclassroom.R;

public class LawService extends Service {

    private MediaPlayer mPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPlayer = MediaPlayer.create(this, R.raw.msc_classroom);
        mPlayer.setLooping(true);
        mPlayer.setVolume(3, 3);
        mPlayer.start();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }
}

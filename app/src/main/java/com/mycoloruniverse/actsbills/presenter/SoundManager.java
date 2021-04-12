package com.mycoloruniverse.actsbills.presenter;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import com.mycoloruniverse.actsbills.R;

public class SoundManager
{
    private static SoundManager instance;
    private SoundPool soundPool;
    private static int[] sm;

    public SoundManager getInstance(Context mContext) {
        if (instance == null) {
            instance = new SoundManager();
        }
        InitSound(mContext);
        return instance;
    }

    private SoundManager InitSound(Context mContext) {
        int maxStreams = 1;
        // Context mContext = getApplicationContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(maxStreams)
                    .build();
        } else {
            soundPool = new SoundPool(maxStreams, AudioManager.STREAM_MUSIC, 0);
        }

        sm = new int[1];
        // fill your sounds
        sm[0] = soundPool.load(mContext, R.raw.click, 1);
        // sm[1] = soundPool.load(mContext, R.raw.sound_2, 1);
        // sm[2] = soundPool.load(mContext, R.raw.sound_3, 1);
        return this;
    }

    public final void playSound(int sound) {
        soundPool.play(sm[sound], 1, 1, 1, 0, 1f);
    }

    public final void cleanUpIfEnd() {
        sm = null;
        soundPool.release();
        soundPool = null;
    }
}

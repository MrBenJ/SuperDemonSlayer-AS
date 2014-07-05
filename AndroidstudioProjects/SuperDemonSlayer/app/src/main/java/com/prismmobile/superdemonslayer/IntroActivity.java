package com.prismmobile.superdemonslayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

import java.util.Random;


public class IntroActivity extends Activity {
        public GestureDetector mGestureDetector;

        protected int damage;
        protected int demonHealth = 100;
/*	private SoundPool mSoundPool;
	private HashMap<Integer, Integer> mSoundPoolMap;
	private  Context mContext;
	 private  Vector<Integer> mAvailibleSounds = new Vector<Integer>();
	 private  Vector<Integer> mKillSoundQueue = new Vector<Integer>();
	 private  Handler mHandler = new Handler();
	 private AudioManager mAudioManager;
*/
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_intro);
            mGestureDetector = new GestureDetector(this).setBaseListener(mBaseListener);

        }
        @Override
        public boolean onGenericMotionEvent(MotionEvent event) {
            return mGestureDetector.onMotionEvent(event);
        }

        private GestureDetector.BaseListener mBaseListener = new GestureDetector.BaseListener() {
            @Override
            public boolean onGesture(Gesture gesture) {

                switch (gesture) {

                    case SWIPE_RIGHT:
                        attackRight();
                        demonHealth = demonHealth - damage;
                        if (demonHealth <= 0 ) {
                            userWins();
                        }
                        return true;
                    case SWIPE_LEFT:
                        attackLeft();
                        demonHealth = demonHealth - damage;
                        if (demonHealth <= 0 ) {
                            userWins();
                        }
                        return true;

                    default:
                        return false;

                }

            }
        };
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.intro, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

	/* private void playSwipe() {
        MediaPlayer swipeSound = MediaPlayer.create(this, R.raw.sword_swipe);
        swipeSound.start();
        swipeSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }
        });
    }
    */
        private int attackRight() {
            // TODO: add swipe right animation and sound
            //      playSwipe();

            // Generate a random number for damage
            Random randomGenerator = new Random();
            damage = randomGenerator.nextInt(8);
            TextView statusText = (TextView) findViewById(R.id.statusText);
            statusText.setText(damage + getString(R.string.damage_indicator));
            return damage;
        }

        private int attackLeft() {
            // TODO: add swipe left animation and sound
            //      playSwipe();

            // Generate a random number for damage
            Random randomGenerator = new Random();
            damage = randomGenerator.nextInt(12);
            TextView statusText = (TextView) findViewById(R.id.statusText);
            statusText.setText(damage + getString(R.string.damage_indicator));
            return damage;
        }

        private void userWins() {
            TextView statusText = (TextView) findViewById(R.id.statusText);
            ImageView demon = (ImageView) findViewById(R.id.Demon);
            demon.setVisibility(View.INVISIBLE);
            statusText.setText(getString(R.string.win_and_exit));
            MediaPlayer winSound = MediaPlayer.create(this, R.raw.fanfare);
            winSound.start();
            winSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.reset();
                    mp.release();
                }
            });
    /*        Intent intent = new Intent(this, storeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
    */
        }

}

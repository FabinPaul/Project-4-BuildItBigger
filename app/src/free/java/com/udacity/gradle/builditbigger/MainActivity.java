package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.network.EndpointsAsyncTask;
import com.udacity.jokeui.JokesActivity;


public class MainActivity extends AppCompatActivity {

    private EndpointsAsyncTask mEndpointAsyncTask;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        requestNewInterstitial();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mEndpointAsyncTask != null)
            mEndpointAsyncTask.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mEndpointAsyncTask != null)
            mEndpointAsyncTask.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mEndpointAsyncTask != null)
            mEndpointAsyncTask.onDestroy();
    }

    public void tellJoke(View view) {
//        JokesManager jokesManager = new JokesManager();
//        Toast.makeText(this, jokesManager.getJoke(), Toast.LENGTH_SHORT).show();
        mEndpointAsyncTask = new EndpointsAsyncTask(this, new EndpointsAsyncTask.Callback() {
            @Override
            public void tellJoke(final String joke) {
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        requestNewInterstitial();
                        JokesActivity.startActivity(MainActivity.this, joke);
                    }
                });
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    JokesActivity.startActivity(MainActivity.this, joke);
                }
            }
        });
        mEndpointAsyncTask.execute();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }


}

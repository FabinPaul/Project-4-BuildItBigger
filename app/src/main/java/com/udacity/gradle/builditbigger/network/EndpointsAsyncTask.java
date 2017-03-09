package com.udacity.gradle.builditbigger.network;

/**
 * Created by Fabin Paul, Eous Solutions Delivery on 3/9/2017 2:55 PM.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.backend.jokesApi.JokesApi;
import com.udacity.gradle.builditbigger.R;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static JokesApi sJokesApi = null;
    private Callback mCallback;
    private Context mContext;
    private ProgressDialog mProgressDialog;
    private boolean isRunning;
    private String mErrorMessage;

    public EndpointsAsyncTask(Context context, Callback callback) {
        this.mCallback = callback;
        this.mContext = context;
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(context.getString(R.string.joke_loading_msg));
        mErrorMessage = mContext.getString(R.string.no_jokes_avail);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        isRunning = true;
        if (sJokesApi == null) {  // Only do this once
            JokesApi.Builder builder = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            sJokesApi = builder.build();
        }

        isRunning = false;
        try {
            return sJokesApi.tellJoke().execute().getData();
        } catch (IOException e) {
            return mErrorMessage;
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        if (mCallback != null)
            mCallback.tellJoke(joke);
    }

    public interface Callback {
        void tellJoke(String joke);
    }

    public void onDestroy() {
        mCallback = null;
        mContext = null;
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
        mProgressDialog = null;
    }

    public void onResume() {
        if (isRunning)
            mProgressDialog.show();
    }

    public void onPause() {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }
}

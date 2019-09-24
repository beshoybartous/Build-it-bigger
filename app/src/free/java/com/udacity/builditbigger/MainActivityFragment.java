package com.udacity.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidjokesproviderlibrary.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.databinding.FragmentMainBinding;


/**
 * Activities that contain this fragment must implement the
 * {@link MainActivityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    FragmentMainBinding fragmentMainBinding;
    private final static String JOKE="GET_JOKE";
    InterstitialAd mInterstitialAd = null;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMainBinding = DataBindingUtil.inflate( inflater, R.layout.fragment_main, container, false );

        mInterstitialAd = new InterstitialAd (getContext());
        mInterstitialAd.setAdUnitId( getString( R.string.banner_ad_unit_id ) );
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                fragmentMainBinding.jokeProgressbar.setVisibility(View.VISIBLE);
                fetchJoke();
                getNewInterstitial();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                getNewInterstitial();
            }

        });

        getNewInterstitial();

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice( AdRequest.DEVICE_ID_EMULATOR )
                .build();
        fragmentMainBinding.adView.loadAd( adRequest );
        fragmentMainBinding.tellJokeBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    fragmentMainBinding.jokeProgressbar.setVisibility(View.VISIBLE);
                    fetchJoke();
                }

            }
        } );
        final View rootView = fragmentMainBinding.getRoot();
        return rootView;
    }

    public void fetchJoke(){
        new EndpointsAsyncTask(){
            @Override
            protected void onPostExecute(String result) {
                Intent intent=new Intent(getActivity(), JokeActivity.class);
                intent.putExtra( JOKE,result);
                startActivity(intent);
                fragmentMainBinding.jokeProgressbar.setVisibility(View.GONE);
            }
        }.execute(  );
    }
    private void getNewInterstitial() {
        AdRequest   adRequest = new AdRequest  .Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("2C14EB2C7DD0FB356590621D42ED9A1B")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
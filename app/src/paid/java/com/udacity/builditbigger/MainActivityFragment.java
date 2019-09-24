package com.udacity.builditbigger;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidjokesproviderlibrary.JokeActivity;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.databinding.FragmentMainBinding;


/**
 * A simple {@link Fragment} subclass.
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

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMainBinding = DataBindingUtil.inflate( inflater, R.layout.fragment_main, container, false );




        fragmentMainBinding.tellJokeBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentMainBinding.jokeProgressbar.setVisibility(View.VISIBLE);
                fetchJoke();
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
}
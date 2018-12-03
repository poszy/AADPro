package com.example.android.songs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.songs.content.SongUtils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SongDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SongDetailFragment extends Fragment {

    // SongItem includes the song title and detail.
    public SongUtils.Song mSong;

    private OnFragmentInteractionListener mListener;

    public SongDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSong = SongUtils.SONG_ITEMS.get(getArguments().getInt(SongUtils.SONG_ID_KEY));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.song_detail, container, false);
        if (mSong != null) {
            ((TextView) rootView.findViewById(R.id.song_detail))
                    .setText(mSong.details);
        }        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public static SongDetailFragment newInstance( int selectedSong){
        // This method recieves the selectedSong with the integer position list
        // creates the nudle with SONG_ID_KEY and slectedSong.
        // then uses setArguments(arguments) to set the arguments for the fragment
        SongDetailFragment fragment = new SongDetailFragment();
        Bundle arguments = new Bundle();

        arguments.putInt(SongUtils.SONG_ID_KEY, selectedSong);
        fragment.setArguments(arguments);

        return fragment;
    }
}

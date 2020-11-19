package com.example.labster;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

public class MainFragment extends Fragment implements View.OnClickListener{

    private final static int KEY_UPVOTES = 0;
    private final static int KEY_DOWNVOTES = 1;

    ImageView mIvJoshUp;
    ImageView mIvJoshDown;

    TextView mTvJoshUp;
    TextView mTvJoshDown;


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // IMAGE VIEWS
        mIvJoshUp = view.findViewById(R.id.iv_josh_upvote);
        mIvJoshDown = view.findViewById(R.id.iv_josh_downvote);

        mIvJoshUp.setOnClickListener(this);
        mIvJoshDown.setOnClickListener(this);

        // TEXT VIEWS
        mTvJoshUp = view.findViewById(R.id.tv_josh_upvote);
        mTvJoshDown = view.findViewById(R.id.tv_josh_downvote);
    }

    @Override
    public void onClick(View view) {

        String base_url = "https://pck.sites.tjhsst.edu";
        TextView targetTV = null;
        int key = -1;

        switch (view.getId()) {
            case R.id.iv_josh_downvote:
                base_url += "/downvoteById?id=0";
                targetTV = mTvJoshDown;
                key = KEY_DOWNVOTES;
                break;
            case R.id.iv_josh_upvote:
                base_url += "/upvoteById?id=0";
                targetTV = mTvJoshUp;
                key = KEY_UPVOTES;
                break;
        }

        MyResponseHelper myResponseHelper = new MyResponseHelper(targetTV, key);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                base_url,
                myResponseHelper,
                myResponseHelper
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
    protected class MyResponseHelper implements
            Response.Listener<String>, Response.ErrorListener {

        TextView mTarget;
        int mUpDownKey;

        public MyResponseHelper (TextView tv, int key) {
            mTarget = tv;
            mUpDownKey = key;
        }

        @Override
        public void onResponse(String response) {
            Log.i("TARGET",response);
            Gson gson = new GsonBuilder().create();
            Ratings ratings = gson.fromJson(response, Ratings.class);

            if (mUpDownKey==KEY_UPVOTES) {
                mTarget.setText(String.valueOf(ratings.upvotes));
            } else if (mUpDownKey==KEY_DOWNVOTES){
                mTarget.setText(String.valueOf(ratings.downvotes));
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
        }
    }
}

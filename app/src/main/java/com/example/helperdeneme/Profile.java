package com.example.helperdeneme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Profile extends Fragment {

    ImageView image_profile, options;
    TextView followers, performedJobs, calledHelpers,nameSurname,service,info,phoneNumber,mail;
    Button editProfile;
    FirebaseUser firebaseUser;
    String profileId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_profile, container, false);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        SharedPreferences pref= getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        profileId= pref.getString("profileid", "none");

        image_profile= view.findViewById(R.id.image_profile);
        //options= view.findViewById(R.id.);
        followers= view.findViewById(R.id.txtFollowertCount);
        performedJobs= view.findViewById(R.id.txtJobsCount);
        calledHelpers= view.findViewById(R.id.txtHelpersCount);
        nameSurname= view.findViewById(R.id.txtProfileNameSurname);
        service= view.findViewById(R.id.txtService);
        info= view.findViewById(R.id.txtInfo);
        phoneNumber= view.findViewById(R.id.txtPhone);
        mail= view.findViewById(R.id.txtMailP);
        editProfile = view.findViewById(R.id.btnEditProfile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btn = editProfile.getText().toString();
                if (btn.equals("Edit Profile")){

                }else if (btn.equals("follow")){

                }
            }
        });

        return view;
    }
}
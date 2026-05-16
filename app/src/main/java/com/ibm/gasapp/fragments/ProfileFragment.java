package com.ibm.gasapp.fragments;

import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ibm.gasapp.R;
import com.ibm.gasapp.activitys.SignInActivity;
import com.ibm.gasapp.activitys.VerifyPhoneActivity;
import com.ibm.gasapp.modules.User;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    private TextView tvFillName,tvPhone ,tvGender;
    private Button btnLogOff;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         tvFillName =view.findViewById(R.id.profile_tv_name);
         tvPhone =view.findViewById(R.id.profile_tv_phone);
         tvGender =view.findViewById(R.id.profile_tv_gender);
        btnLogOff =view.findViewById(R.id.profile_btn_logOff);

         getUser(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());

         btnLogOff.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 FirebaseAuth.getInstance().signOut();
                 startActivity(new Intent(getActivity(),SignInActivity.class));
             }
         });

    }

    private void getUser(String userPhone) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    User user = dataSnapshot.getValue(User.class);

                    if (user.getPhone().equals(userPhone)) {
                        tvFillName.setText(user.getFullName());
                        tvPhone.setText(user.getPhone());
                        tvGender.setText(user.getGender());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Error: ", error.toException());

            }
        });
    }
}
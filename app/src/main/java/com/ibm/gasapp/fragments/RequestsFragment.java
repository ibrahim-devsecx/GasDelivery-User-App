package com.ibm.gasapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ibm.gasapp.activitys.MapsRequestActivity;
import com.ibm.gasapp.adapters.RequestsAdapter;
import com.ibm.gasapp.modules.Request;
import com.ibm.gasapp.interfaces.setOnClickListenerMap;
import com.ibm.gasapp.databinding.FragmentRequestsBinding;

import java.util.ArrayList;

public class RequestsFragment extends Fragment {

    private FragmentRequestsBinding binding;
    private static final String TAG = "RequestsFragment";
    private FirebaseAuth mAuth;
    RequestsAdapter requestsAdapter;

    public RequestsFragment() {
        // Required empty public constructor
    }

    public static RequestsFragment newInstance() {
        RequestsFragment fragment = new RequestsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRequestsBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        getRequestsLlist();


        binding.swiperefreshRequests.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRequestsLlist();

                binding.swiperefreshRequests.setRefreshing(false);
            }
        });

        binding.btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickWhatsApp("+970567066353");

            }
        });
    }

    private void getRequestsLlist() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("request");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Request> requestArrayList = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Request request = dataSnapshot.getValue(Request.class);

                    String currentUser = mAuth.getCurrentUser().getPhoneNumber();

                    if (request.getMobileNumber().equals(currentUser)) {
                        LatLng latLng = new LatLng(request.getLatitude(), request.getLongitude());
                        Log.d(TAG, "onDataChange: " + request.getMobileNumber());
                        requestArrayList.add(request);
                    }
                }
                binding.progressBar.setVisibility(View.GONE);
                setRequestRecyclerAabter(requestArrayList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Error: ", error.toException());

            }
        });
    }

    private void setRequestRecyclerAabter(ArrayList<Request> arrayList) {
        RequestsAdapter requestsAdapter = new RequestsAdapter(getActivity(), arrayList, new setOnClickListenerMap() {
            @Override
            public void onClickMap(double latitude, double longitude) {

                Intent intentMap = new Intent(getActivity(), MapsRequestActivity.class);
                intentMap.putExtra("latitude", latitude);
                intentMap.putExtra("longitude", longitude);
                startActivity(intentMap);
            }
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);

        binding.rvRequests.setLayoutManager(layoutManager);
        binding.rvRequests.setAdapter(requestsAdapter);
    }

    public void onClickWhatsApp(String moble) {


        String phoneNumberWithCountryCode = moble;
        String message = "مرحبا انا مستخدم تطبيق Gas Delivery لدي استفسار";

        startActivity(
                new Intent(Intent.ACTION_VIEW,
                        Uri.parse(
                                String.format("https://api.whatsapp.com/send?phone=%s&text=%s", phoneNumberWithCountryCode, message)
                        )
                )
        );

    }
}
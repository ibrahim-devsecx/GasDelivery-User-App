package com.ibm.gasapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibm.gasapp.databinding.FragmentConfirmRequestDialogBinding;


public class ConfirmRequestDialogFragment extends DialogFragment {

    private FragmentConfirmRequestDialogBinding binding;
    private static SetOnClickListenerDialod listenerDialod;

    public ConfirmRequestDialogFragment() {
        // Required empty public constructor
    }

    public static ConfirmRequestDialogFragment newInstance(SetOnClickListenerDialod listener) {
        listenerDialod = listener ;
        ConfirmRequestDialogFragment fragment = new ConfirmRequestDialogFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConfirmRequestDialogBinding.inflate(getLayoutInflater(), container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listenerDialod.onClickConfirmation();
            }
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerDialod.onClickCancel();
            }
        });

    }

    public interface SetOnClickListenerDialod {
        void onClickConfirmation();

        void onClickCancel();
    }
}
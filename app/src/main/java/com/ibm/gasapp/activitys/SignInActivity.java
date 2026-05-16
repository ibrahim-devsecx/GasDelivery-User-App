package com.ibm.gasapp.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ibm.gasapp.R;
import com.ibm.gasapp.modules.Request;
import com.ibm.gasapp.modules.User;

public class SignInActivity extends AppCompatActivity {

    TextInputEditText etPhone;
    AppCompatButton btnSignIn, btnSignUp;
    public static boolean CHECK;

    private static final String TAG = "SignInActivity";

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        etPhone = findViewById(R.id.signIn_et_phone);
        btnSignIn = findViewById(R.id.signIn_btn_signIn);
        btnSignUp = findViewById(R.id.signIn_btn_signUp);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = "+97";
                String number = etPhone.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    etPhone.setError(getString(R.string.valid_number_is_required));
                    etPhone.requestFocus();
                    return;
                }

                String phoneNumber = code + number;
                isRegistered(phoneNumber);


            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CHECK = false;
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
    }

    private void isRegistered(String userPhone) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean registerCheck = false;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    User user = dataSnapshot.getValue(User.class);

                    if (user.getPhone().equals(userPhone)) {
                        Intent intent = new Intent(SignInActivity.this, VerifyPhoneActivity.class);
                        intent.putExtra("phoneNumber", userPhone);
                        CHECK = true;
                        startActivity(intent);
                    return;} else {
                        etPhone.setError(getString(R.string.un_Registered));
                        Toast.makeText(getApplicationContext(), getString(R.string.un_Registered), Toast.LENGTH_LONG).show();
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
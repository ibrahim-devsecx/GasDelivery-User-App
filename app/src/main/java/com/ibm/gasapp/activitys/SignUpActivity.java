package com.ibm.gasapp.activitys;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ibm.gasapp.R;
import com.ibm.gasapp.modules.Request;
import com.ibm.gasapp.modules.User;

import java.util.UUID;

public class SignUpActivity extends AppCompatActivity {

    ImageView imgGallery;
    TextView accountSignIn;
    EditText etFullName, etPhone;
    Button btnSignIn;
    RadioButton rBtnMention, rBtnFemale;

    Uri imageUri;



    private static final String TAG = "SignUpActivity";

    private DatabaseReference databaseReference;


    public static final int RESULT_GALLERY = 0;
    private boolean check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        imgGallery = findViewById(R.id.sign_up_img_gallery);

        etFullName = findViewById(R.id.sign_up_et_name);
        etPhone = findViewById(R.id.sign_up_et_phone);

        btnSignIn = findViewById(R.id.sign_up_btn_sign_in);

        rBtnMention = findViewById(R.id.sign_up_rbtn_mention);
        rBtnFemale = findViewById(R.id.sign_up_rbtn_female);

        accountSignIn = findViewById(R.id.sign_up_tv_account_sign_in);



        imgGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        accountSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullName = etFullName.getText().toString();
                String phone = etPhone.getText().toString();

                if (phone.isEmpty() || phone.length() < 10) {
                    etPhone.setError("Valid number is required");
                    etPhone.requestFocus();
                    return;
                }
                String gender = "female";
                if (rBtnMention.isChecked()) {
                    gender = getString(R.string.mention);

                } else {
                    gender = getString(R.string.female);

                }

                String phoneNumber = "+97" + phone;

                User user = new User("imageUri", fullName, phoneNumber, gender);
                isRegistered(user);


            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_GALLERY);
    }


    private void isRegistered(User user) {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    User user = dataSnapshot.getValue(User.class);

                    if (user.getPhone().equals(user.getPhone())) {
                        check = true;
                    } else {
                        check = false;
                    }

                }

                if (true) {
                    Intent intent = new Intent(SignUpActivity.this, VerifyPhoneActivity.class);
                    intent.putExtra("userData", user);
                    startActivity(intent);
                } else {
                    etPhone.setError(getString(R.string.number_already_exists));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Error: ", error.toException());

            }
        });
    }


    private String getFileExtension(Uri imageUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(imageUri));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_GALLERY:
                if (null != data) {
                    imageUri = data.getData();
                    imgGallery.setImageURI(imageUri);
                    //Do whatever that you desire here. or leave this blank

                }
                break;
            default:
                break;
        }
    }
}
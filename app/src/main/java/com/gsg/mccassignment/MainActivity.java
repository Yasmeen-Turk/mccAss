package com.gsg.mccassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewUser;
    EditText edUserName;
    EditText edAddress;
    EditText edNumber;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewUser = findViewById(R.id.pic_imageview);
        edUserName = findViewById(R.id.username_edittext);
        edAddress = findViewById(R.id.address_edittext);
        edNumber = findViewById(R.id.phone_edittext);
    }
    public void saveToFirebase(View v) {
        String userName = edUserName.getText().toString();
        String address = edAddress.getText().toString();
        String number = edNumber.getText().toString();

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("userName", userName);
        user.put("address", address);
        user.put("number", number);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });
        Intent userInformation = new Intent(getApplicationContext(), UserInformation.class);
        startActivity(userInformation);
    }
}
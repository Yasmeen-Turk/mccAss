package com.gsg.mccassignment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class UserInformation extends AppCompatActivity {

    TextView tvName;
    TextView tvAddress;
    TextView tvNumber;

     public static FirebaseDatabase database;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
     DatabaseReference mDatabase;
     Map<String, String> userMap;
    //String TAG = this.getClass().getName().toUpperCase();
     String USERS = "users";
    String fname, address, phone,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        tvName = findViewById(R.id.username_textview);
        tvAddress = findViewById(R.id.address_textview);
        tvNumber = findViewById(R.id.phone_textview);



      db.collection(USERS).get()
              .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                  @Override
                  public void onSuccess(QuerySnapshot documentSnapshots) {
                      if (! (documentSnapshots.isEmpty())){
                          for (DocumentSnapshot documentSnapshot : documentSnapshots){
                              id = documentSnapshot.getId();
                              fname = documentSnapshot.getString("userName");
                              address = documentSnapshot.getString("address");
                              phone = documentSnapshot.getString("number");

                              Log.e("tag",fname.toString());

                              tvName.setText(fname);
                              tvAddress.setText(address);
                              tvNumber.setText(phone);
                          }

                      }
                  }
              });
////        DatabaseReference rootRef = FirebaseDatabase.getInstance().c.getReference();
//        DatabaseReference userRef = rootRef.child(USERS);
//
//        tvName = findViewById(R.id.username_textview);
//        tvAddress = findViewById(R.id.address_textview);
//        tvNumber = findViewById(R.id.phone_textview);
//
//        userRef.addValueEventListener(new ValueEventListener() {
//
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot keyId : dataSnapshot.getChildren()) {
//                    //if (keyId.child("userName").getValue().equals(tvName)) {
//
//                        fname = keyId.child("userName").getValue(String.class);
//                        address = keyId.child("address").getValue(String.class);
//                        phone = keyId.child("number").getValue(String.class);
//                        break;
//                    //}
//                }
//                tvName.setText(fname);
//                tvAddress.setText(address);
//                tvNumber.setText(phone);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.w(TAG, "Failed to read value.", databaseError.toException());
//            }
//        });
    }
}



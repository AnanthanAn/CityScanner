package com.example.cityscanner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.cityscanner.POJO.NearbyItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NearbyListActivity extends AppCompatActivity {

    private static final String KEY_SHOP_NAME = "shop_name";
    private static final String KEY_ADDRESS = "shop_address";
    private static final String KEY_LATTITUDE = "lattitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_RATING = "rating";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_AVAIL_ITEMS = "avail_items";
    private static final String SEARCH_KEY = "SEARCH_KEY";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference dataRef = db.collection("data");

    ArrayList<NearbyItem> nearbyItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_list);
        Intent intent = getIntent();
        String SearchKey = intent.getStringExtra(SEARCH_KEY);

        Log.i(SEARCH_KEY,SearchKey);

        if(SearchKey == null || SearchKey ==""){
            Toast.makeText(getApplicationContext(),"No shops are available. Sorry",Toast.LENGTH_LONG).show();
        }

        nearbyItems = new ArrayList<>();

        final ProgressDialog progressDialog =new ProgressDialog(NearbyListActivity.this);
        progressDialog.setMessage("Loding...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();

        dataRef.whereArrayContains(KEY_AVAIL_ITEMS,SearchKey)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            String shopName = String.valueOf(documentSnapshot.get(KEY_SHOP_NAME));
                            String shopAdress = String.valueOf(documentSnapshot.get(KEY_ADDRESS));
                            String shopphone = String.valueOf(documentSnapshot.get(KEY_PHONE));

                            nearbyItems.add(new NearbyItem(shopName,shopAdress,shopphone));
                            Log.i("arayyyyyyyyyyyyyyyyyy", String.valueOf(nearbyItems.get(0).getmShopName()));

                            mAdapter.notifyDataSetChanged();
                        }
                        progressDialog.dismiss();

                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                    }
                });

        //nearbyItems.add(new NearbyItem("demo","demooooo","98765"));


        recyclerView = findViewById(R.id.nearbyRecyclerView);
        recyclerView.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(this);
        mAdapter = new NearbyAdapter(nearbyItems);

        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setAdapter(mAdapter);


    }

}

package com.example.cityscanner;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class FirestoreEntryActivity extends AppCompatActivity {

    private static final String KEY_SHOP_NAME = "shop_name";
    private static final String KEY_ADDRESS = "shop_address";
    private static final String KEY_LATTITUDE = "lattitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_RATING = "rating";
    private static final String KEY_AVAIL_ITEMS = "avail_items";

    EditText ET_shop_name,ET_shop_address,ET_lat,ET_long,ET_avail_items;
    TextView datasw;
    Button bt_register;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference dataRef = db.collection("data");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore_entry);

        ET_shop_name = findViewById(R.id.et_shop_name);
        ET_shop_address = findViewById(R.id.et_address);
        ET_lat = findViewById(R.id.et_lat);
        ET_long = findViewById(R.id.et_log);
        ET_avail_items = findViewById(R.id.et_avail_items);
        bt_register = findViewById(R.id.bt_register_shop);
        datasw = findViewById(R.id.datashow);

    }

    public void saveData(View v){

        //String shop_name = ET_shop_name.getText().toString().trim();
        //String shop_address = ET_shop_address.getText().toString().trim();
        //float lat = Float.parseFloat(ET_lat.getText().toString().trim());
        //float log = Float.parseFloat(ET_long.getText().toString().trim());
        //String log = ET_long.getText().toString().trim();
        String avail_item = ET_avail_items.getText().toString().trim();

        dataRef.whereArrayContains(KEY_AVAIL_ITEMS,avail_item)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            String shopName = String.valueOf(documentSnapshot.get(KEY_SHOP_NAME));
                            String shopAdress = String.valueOf(documentSnapshot.get(KEY_ADDRESS));

                            data += "Shop Name : "+shopName +"\nShop Address : "+ shopAdress;
                        }
                        datasw.setText(data);
                    }
                });

        //GeoPoint geoPoint = new GeoPoint(lat,log);

        //Map<String,Object> shop = new HashMap<>();
        //shop.put(KEY_SHOP_NAME,shop_name);
        //shop.put(KEY_ADDRESS,shop_address);
        //shop.put("loc",geoPoint);
//        shop.put(KEY_LATTITUDE,lat);
//        shop.put(KEY_LONGITUDE,log);
        //shop.put(KEY_AVAIL_ITEMS,avail_item);



//        db.collection("data").add(shop)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(getApplicationContext(),"Saved successfully",Toast.LENGTH_SHORT).show();
//
//                    }
//                });


    }
}

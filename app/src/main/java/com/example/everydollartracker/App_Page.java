package com.example.everydollartracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App_Page extends AppCompatActivity {
    public static   User thisUser= new User("a","b");
    public static UserFireStore a;
    public static int count =0;
    static List<InExStore> inExArray = new ArrayList<>();
    static FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    static String userID = firebaseUser.getUid();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseDatabase db2 = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,  R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        ////Test code to load array frome firebase here
        // test ver 1
        //db.collection("users2").document(userID);
        /*
        FirebaseFirestore.getInstance().collection("users")
        .document(userID).get()
        .addOnCompleteListener(new
        OnCompleteListener<DocumentSnapshot>(){
        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task){
        DocumentSnapshot document=task.getResult();
            UserFireStore a = document.toObject(UserFireStore.class);
        UserFireStore user=document.toObject(UserFireStore.class);
        inExArray.addAll(UserFireStore.inExStoreArrayList);
        }
        });*/
        //inExArray= a.getInExStores();
        //inExArray.addAll(inExArray2);
        //test ver 2
        // load array


            FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
            CollectionReference usersRef = rootRef.collection("users");
            DocumentReference userIdRef = usersRef.document(userID);
        if(count==0) {
            userIdRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        UserFireStore indexes = document.toObject(UserFireStore.class);
                        inExArray.addAll(indexes.getList());

                    }
                    count++;
                }
            });

        }



        //db.collection("test");

        if(count>0) {
            Map<String, Object> user = new HashMap<>();
            user.put("inExArray", inExArray);

            // save data to firebase
            db.collection("users").document(userID).set(user);
            count++;
        }
        ///////////
        /*addInOrEx((double)61,"INCOME", "07/28/2021","SALARY", "full time");
        addInOrEx((double)32,"INCOME", "07/28/2021","BONUS", "full time");
        addInOrEx((double)79,"hello", "07/25/2021","NEEDS", "PART time");
        addInOrEx((double)32,"INCOME", "07/28/2021","BONUS", "full time");
        addInOrEx((double)59,"EXPENSES", "07/25/2021","NEEDS", "PART time");
        addInOrEx((double)61,"INCOME", "07/28/2021","SALARY", "full time");
        addInOrEx((double)32,"INCOME", "07/28/2021","BONUS", "full time");
        addInOrEx((double)79,"EXPENSES", "07/25/2021","NEEDS", "PART time");
        addInOrEx((double)32,"INCOME", "07/28/2021","ALLOWANCE", "full time");
        addInOrEx((double)59,"EXPENSES", "07/25/2021","SAVINGS", "PART time");
        addInOrEx((double)50,"EXPENSES", "07/25/2021","NEEDS", "PART time");
        addInOrEx((double)100,"EXPENSES", "07/25/2021","WANTS", "PART time");*/
    }
    public static void addInOrEx(double amount,String type, String date,String source, String note) {
        InExStore newInEx = new InExStore(amount, type, date, source, note);
        inExArray.add(newInEx);
    }
    // create each user by user ID to FireStore
    private void createNewUser() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        User newUser = new User(firebaseUser.getDisplayName(), firebaseUser.getUid());

        db.collection("users").document(firebaseUser.getUid()).set(newUser);

    }


}

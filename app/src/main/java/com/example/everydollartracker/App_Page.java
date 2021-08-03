package com.example.everydollartracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;
import static com.example.everydollartracker.Graphs.e;

public class App_Page extends AppCompatActivity {
    public static   User thisUser= new User("a","b");
    public static UserFireStore a;
    static List<InExStore> inExArray = new ArrayList<InExStore>();
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
        /*FirebaseFirestore.getInstance().collection("users")
        .document(userID).get()
        .addOnCompleteListener(new
        OnCompleteListener<DocumentSnapshot>(){
        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task){
        DocumentSnapshot document=task.getResult();
            UserFireStore a = document.toObject(UserFireStore.class);
        //UserFireStore user=document.toObject(UserFireStore.class);
        //inExArray.addAll(user.getInExStores());
        }
        });*/
        //inExArray= a.getInExStores();
        //inExArray.addAll(inExArray2);
        //test ver 2
        // load array

        /*FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference usersRef = rootRef.collection("users");
        DocumentReference userIdRef = usersRef.document(userID);
        userIdRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    List<InExStore> inexs = document.toObject(UserFireStore.class).inExStoreArrayList;
                    //inExArray.clear();
                    //inExArray.addAll(inexs);
                }
            }
        });*/



        //db.collection("test");


        Map<String, Object> user = new HashMap<>();
        user.put("inExArray", inExArray);

        // save data to firebase
        db.collection("users").document(userID)                .set(user);
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

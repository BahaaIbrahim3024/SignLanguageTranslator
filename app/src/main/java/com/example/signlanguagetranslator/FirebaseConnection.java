package com.example.signlanguagetranslator;

import android.util.Log;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class FirebaseConnection extends Object {

public  DocumentModel f;
public   List<DocumentModel> data=new ArrayList<>();

    FirebaseFirestore db=FirebaseFirestore.getInstance();
    public List<DocumentModel> getDtata(){
        db.collection("/Videos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete( Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot doc:task.getResult()){
                        Log.d("bah : ",doc.getId()+" ==> "+doc.getData());
                        f=new DocumentModel(doc.get("description").toString(),doc.get("tag").toString(),doc.get("video_id").toString());
                        data.add(f);

                    }
                    System.out.println("");
                }
                else{
                    Log.d("bah","Error getting documents",task.getException());
                }
            }
        });
        return  data;

    }
}

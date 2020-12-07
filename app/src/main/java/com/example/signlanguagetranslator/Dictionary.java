package com.example.signlanguagetranslator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Dictionary extends AppCompatActivity  implements SearchView.OnQueryTextListener{



    public  static List<DocumentModel> docList=new ArrayList<>();
    private RecyclerView recyclerView;
    private DocumentAdapter documentAdapter;
    private DocumentModel f;
    private SearchView searchKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        searchKey=(SearchView) findViewById(R.id.searchV);

        documentAdapter = new DocumentAdapter(docList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(documentAdapter);

        searchKey.setOnQueryTextListener(this);

        getDtata();
    }

    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    public void getDtata(){
        db.collection("/Videos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete( Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot doc:task.getResult()){
                        f=new DocumentModel(doc.get("description").toString(),doc.get("tag").toString(),doc.get("video_id").toString());
                        docList.add(f);
                        documentAdapter.notifyDataSetChanged();

                    }
                }
                else{
                    Log.d("bah","Error getting documents",task.getException());
                }
            }
        });
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        documentAdapter.filter(s,docList);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        documentAdapter.filter(s,docList);

        return true;
    }




}

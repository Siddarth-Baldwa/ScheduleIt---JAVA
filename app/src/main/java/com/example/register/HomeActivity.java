package com.example.register;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import android.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ExtendedFloatingActionButton floatingActionButton;

    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String onlineUserID;

    private ProgressDialog loader;

    // Variables for Updation

    private String key = "";
    private String task;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_home);

       /* setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("To-Do List");*/

        recyclerView =  findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setReverseLayout(true);
        //linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        loader = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        onlineUserID = mUser.getUid();
        String meeting = getIntent().getStringExtra("meetings");
        reference = FirebaseDatabase.getInstance().getReference().child("tasks").child(onlineUserID).child(meeting);

        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

    }

    private void addTask(){
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        View myView = inflater.inflate(R.layout.input_file, null);
        myDialog.setView(myView);

        final AlertDialog dialog = myDialog.create();
        dialog.setCancelable(false);

        final EditText task = myView.findViewById(R.id.task);
        Button save = myView.findViewById(R.id.saveBtn);
        Button cancel = myView.findViewById(R.id.cancelBtn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTask = task.getText().toString().trim();
                String id = reference.push().getKey();


                if (TextUtils.isEmpty(mTask)) {
                    task.setError("Task is Required");
                    return;
                }

                else{
                    loader.setMessage("Adding your Data");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    Model model =  new Model(mTask,id);
                    reference.child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(com.example.register.HomeActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });
                    dialog.dismiss();
                }
            }

        });
        dialog.show();
    }

    //-----------------------------------------------------------------------------------------------------------------

    @Override
    protected void onStart(){
        super.onStart();

        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>()
                .setQuery(reference,Model.class)
                .build();

        FirebaseRecyclerAdapter<Model, MyViewHolder> adapter = new FirebaseRecyclerAdapter<Model, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Model model) {

                holder.setTask(model.getTask());

                //Updation related Code

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        key = getRef(holder.getAdapterPosition()).getKey();
                        task = model.getTask();
                        updateTask();
                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieved_layout,parent,false);
                return new MyViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

   public static class MyViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTask(String task){
            TextView taskTextView = mView.findViewById(R.id.taskTv);
            taskTextView.setText(task);
        }



    }
    public void updateTask(){
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.update_data,null);
        myDialog.setView(view);

        AlertDialog dialog = myDialog.create();

        EditText mTask = view.findViewById(R.id.mEditTextTask);


        mTask.setText(task);
        mTask.setSelection(task.length());


        Button delButton = view.findViewById(R.id.btnDelete);
        Button updateButton = view.findViewById(R.id.btnUpdate);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = mTask.getText().toString().trim();

                Model model = new Model(task,key);

                reference.child(key).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(com.example.register.HomeActivity.this,"Data has been Updated Successfully", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            String err = task.getException().toString();
                            Toast.makeText(com.example.register.HomeActivity.this, "Update Failed" + err, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.dismiss();
            }

        });

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                reference.child(key).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(com.example.register.HomeActivity.this,"Task Deleted", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            String err = task.getException().toString();
                            Toast.makeText(com.example.register.HomeActivity.this, "Failed to Delete the Task" + err, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
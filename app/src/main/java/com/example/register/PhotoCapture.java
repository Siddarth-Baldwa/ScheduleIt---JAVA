package com.example.register;
/*
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PhotoCapture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_capture);
    }
}
*/

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class PhotoCapture extends AppCompatActivity {

    private Button camera;
    private Button upload;
    private Button gallery;
    private Bitmap image;
    private Uri image1;
    private Button DisplayImageButton;
    EditText description;

    String Database_Path = "All_Image_Uploads_Database";
    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    /* protected void onRestart() {
         super.onRestart();
         setContentView(R.layout.activity_photo_capture);

         camera.setOnClickListener(view->{
             Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             startActivityForResult(camera, 0);
         });

         upload.setOnClickListener(view->{
             upload();
         });

     } */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_capture);
        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://register-14b86.appspot.com/image");
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);


        camera = findViewById(R.id.camera);
        upload = findViewById(R.id.upload);
        gallery = findViewById(R.id.gallery);
        description = findViewById(R.id.description);

        camera.setOnClickListener(view -> {
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera, 0);
        });

        gallery.setOnClickListener(view -> {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, 1);
        });

        upload.setOnClickListener(view -> {
            upload();
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == 0) && resultCode == RESULT_OK) {
            image = (Bitmap) data.getExtras().get("data");
            ImageView photo = findViewById(R.id.photo);
            if (null == photo) {
                Log.e("Error", "Ouh! there is no child view with R.id.imageView ID within my parent view View.");
            }
            photo.setImageBitmap(image);
        }

        if ((requestCode == 1) && resultCode == RESULT_OK) {
            image1 = data.getData();
            ImageView photo = findViewById(R.id.photo);
            try {
                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(),image1);
                photo.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (null == photo) {
                Log.e("Error", "Ouh! there is no child view with R.id.imageView ID within my parent view View.");
            }
        }
    }
    private void upload() {
        final ProgressBar p = findViewById(R.id.progressbar);

        p.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        final String random = UUID.randomUUID().toString();
        StorageReference imageRef = storageReference;

        byte[] b = stream.toByteArray();
        imageRef.putBytes(b)
                .addOnSuccessListener(taskSnapshot -> {
                        p.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(uri-> {
                            Uri downloadUrl = uri;
                        String TempImageName = description.getText().toString().trim();
                        Toast.makeText(PhotoCapture.this, "Photo Uploaded", Toast.LENGTH_SHORT).show();
                        ImageUploadInfo imageUploadInfo = new ImageUploadInfo(TempImageName,downloadUrl.toString());
                        // Getting image upload ID.
                        String ImageUploadId = databaseReference.push().getKey();
                        // Adding image upload id s child element into databaseReference.
                        databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                        });
                })
                .addOnFailureListener(e->{
                        p.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(PhotoCapture.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                });
    }
}
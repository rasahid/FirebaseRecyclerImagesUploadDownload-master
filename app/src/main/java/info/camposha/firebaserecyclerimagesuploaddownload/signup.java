package info.camposha.firebaserecyclerimagesuploaddownload;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class signup extends AppCompatActivity {
    Button btn_register;
    EditText name,email,password;
    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog progress;
    ImageView logo;
    EditText phone;
    private int PReqCode=1;
    private Uri mImageUri;
    private int PICK_IMAGE_REQUEST=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        phone=findViewById(R.id.phone);
        logo=findViewById(R.id.logo);
        btn_register=findViewById(R.id.btn_register);
        auth=FirebaseAuth.getInstance();
        Toast.makeText(getApplicationContext()," please Click on profile to choose Image",Toast.LENGTH_LONG).show();
       logo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(Build.VERSION.SDK_INT>=22)
               {
                   openGallary();
                   openpermassion();


               }
               else
               {

                   openGallary();

               }




           }
       });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pro();
                final String Name=name.getText().toString().trim();
                final String Email=email.getText().toString().trim();
                String Password=password.getText().toString().trim();
                String Phone=phone.getText().toString().trim();


                if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Name) || TextUtils.isEmpty(Password))
                {
                    progress.dismiss();
                    Toast.makeText(getApplicationContext(),"Empty",Toast.LENGTH_SHORT).show();
                }
                else if (Password.length()<6)
                {
                    progress.dismiss();
                    Toast.makeText(getApplicationContext(),"Password lenght error",Toast.LENGTH_SHORT).show();
                }

                else if (Phone.length()<9)
                {
                    progress.dismiss();
                    Toast.makeText(getApplicationContext(),"phone_NUMBER lenght error",Toast.LENGTH_SHORT).show();
                }
                else
                {
                     creatuseraccount(Email,Password,Name,Phone);

                }
            }
        });
    }

    private void creatuseraccount(final String email, String password, final String name, final String phone) {

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {

                    reference= FirebaseDatabase.getInstance().getReference("Users").child(auth.getUid());
                    HashMap<String ,String > hashMap=new HashMap<>();

                    hashMap.put("email",email);
                    hashMap.put("id",auth.getUid());
                    hashMap.put("phone_NO",phone);
                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                progress.dismiss();
                                Toast.makeText(getApplicationContext(),"Successfuly registered",Toast.LENGTH_SHORT).show();
                                uploadO(mImageUri,name,phone,auth.getCurrentUser());


                            }
                            else
                            {
                                progress.dismiss();
                                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                else
                {
                    progress.dismiss();
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void uploadO(Uri mImageUri, final String name,final String Email, final FirebaseUser currentUser) {

        StorageReference mstorage= FirebaseStorage.getInstance().getReference().child("user_info");
        final StorageReference imagefilepath=mstorage.child(mImageUri.getLastPathSegment());
        imagefilepath.putFile(mImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                imagefilepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        UserProfileChangeRequest profileupdate= new UserProfileChangeRequest.Builder()
                       .setDisplayName(name)

                        .setPhotoUri(uri)

                        .build();
                        currentUser.updateProfile(profileupdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        Toast.makeText(getApplicationContext(),"uploaded",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(signup.this,Main2Activity.class);
                                      
                                        startActivity(intent);

                                    }
                                });
                    }
                });

            }
        });
    }


    void pro()
    {
        progress=new ProgressDialog(this);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setMessage("please wait");
        progress.setTitle("Loding");
        progress.setCancelable(false);
        progress.show();
    }

    private void openpermassion()
    {
        if(ContextCompat.checkSelfPermission(signup.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)

        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(signup.this,Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                Toast.makeText(signup.this,"please accept for reqired permission",Toast.LENGTH_LONG).show();
            }
            else
            {
                ActivityCompat.requestPermissions(signup.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PReqCode);

            }


        }
        else


            openGallary();



    }
    private void    openGallary() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(logo);
        }
    }







    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

}
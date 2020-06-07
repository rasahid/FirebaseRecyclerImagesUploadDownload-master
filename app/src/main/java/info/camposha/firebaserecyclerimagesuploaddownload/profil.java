package info.camposha.firebaserecyclerimagesuploaddownload;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

public class profil extends AppCompatActivity {
TextView email,phone1,name;
ImageView image;
    FirebaseAuth auth;
    FirebaseUser currentuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        email=(TextView)findViewById(R.id.email);
        phone1=(TextView)findViewById(R.id.phones);
        name=(TextView)findViewById(R.id.name);
        image=(ImageView)findViewById(R.id.image);
        auth=FirebaseAuth.getInstance();
        currentuser=auth.getCurrentUser();


        get_userData();


    }
    private void get_userData()
    {

        ImageView image= findViewById(R.id.image);

        email.setText(currentuser.getEmail());

        name.setText(currentuser.getDisplayName());
        phone1.setText(currentuser.getPhoneNumber());


        Glide.with(this).load(currentuser.getPhotoUrl()).into(image);


    }
}

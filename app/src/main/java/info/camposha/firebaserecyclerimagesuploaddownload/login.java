package info.camposha.firebaserecyclerimagesuploaddownload;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import info.camposha.firebaserecyclerimagesuploaddownload.View.MainActivity;

public class login extends AppCompatActivity {
  Button btn_login;
    EditText email_log,password_log;
    FirebaseAuth auth;
    ProgressDialog progress;
    ViewFlipper imageslider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login=findViewById(R.id.btn_login);
        email_log=findViewById(R.id.email_log);
        password_log=findViewById(R.id.password_log);
       /* int image[]={R.drawable.bed1,R.drawable.bed2,R.drawable.bed1};
        imageslider=(ViewFlipper)findViewById(R.id.imageslider);
        for(int images: image)
        {
            imageviews(images);
        }

        */
        auth=FirebaseAuth.getInstance();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String email=email_log.getText().toString().trim();
                final String pass=password_log.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass))
                {

                    Toast.makeText(login.this,"Empty",Toast.LENGTH_SHORT).show();
                }




                else
                {
                    auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {

                                Toast.makeText(getApplicationContext(), "Successfuly Loged In", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(login.this, Main2Activity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();


                            }
                            if (email.equals((Object)"admin") && pass.equals((Object)"123123")) {
                                Intent in1=new Intent(login.this,admin.class);
                                startActivity(in1);
                                return;
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "wrong Email & Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }


    public void signup1(View view) {
        Intent in=new Intent(this,signup.class);
        startActivity(in);
    }
   /* void imageviews(int images1)
    {

        ImageView imageView= new ImageView(this);
        imageView.setBackgroundResource(images1);
        imageslider.addView(imageView);
        imageslider.setFlipInterval(4000);
        imageslider.setAutoStart(true);
        imageslider.setInAnimation(this,android.R.anim.slide_in_left);
        imageslider.setOutAnimation(this,android.R.anim.slide_out_right);


    }
      */
}
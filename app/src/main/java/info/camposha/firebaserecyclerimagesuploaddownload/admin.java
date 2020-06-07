package info.camposha.firebaserecyclerimagesuploaddownload;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import info.camposha.firebaserecyclerimagesuploaddownload.View.ItemsActivity;
import info.camposha.firebaserecyclerimagesuploaddownload.View.MainActivity;
import info.camposha.firebaserecyclerimagesuploaddownload.View.UploadActivity;

public class admin extends AppCompatActivity {
    private Button openActivityBtn,openUploadActivityBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        openActivityBtn = findViewById ( R.id.openActivityBtn );
        openUploadActivityBtn = findViewById ( R.id.openUploadActivityBtn );

        openActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(admin.this, ItemsActivity.class);
                startActivity(i);
            }
        });
        openUploadActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(admin.this, UploadActivity.class);
                startActivity(i);
            }
        });

    }


    public void books(View view) {


        Intent a=new Intent(admin.this,user_data.class);
        startActivity(a);
    }
}

package info.camposha.firebaserecyclerimagesuploaddownload;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class contuct extends AppCompatActivity {
    EditText editTextSubject, editTextMessage;
    Button send;
    private final String emailToSend = "rashid.11700362@lpu.in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contuct);

        editTextSubject = findViewById(R.id.editText2);
        editTextMessage = findViewById(R.id.editText3);
        send = findViewById(R.id.button1);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editTextMessage.getText().toString().trim();
                String subject = editTextSubject.getText().toString().trim();
                if (TextUtils.isEmpty(message) || TextUtils.isEmpty(subject))
                {
                    Toast.makeText(getApplicationContext(),"Empty",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailToSend});
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    intent.putExtra(Intent.EXTRA_TEXT, message);
                    intent.setType("message/rfc822");
                    startActivity(Intent.createChooser(intent, "Choose an Email client"));
                }
            }
        });
    }
}
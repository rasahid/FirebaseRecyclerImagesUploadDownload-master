package info.camposha.firebaserecyclerimagesuploaddownload;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import info.camposha.firebaserecyclerimagesuploaddownload.Model.bookings;

public class booking extends AppCompatActivity {
    EditText Fname;
    EditText name;
    EditText email;
    EditText phone;
    RadioButton male;
    RadioButton female;
    RadioButton national;
    RadioButton international;
    EditText persons;
    TextView na,da;
    long maxid=0;
    int i,b=1;
    bookings bookingss;

    Button btn_register;
    FirebaseDatabase fdatabase;
    DatabaseReference databaseReferenc;

bookings book;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Fname = (EditText) findViewById(R.id.Fname);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        na=(TextView)findViewById(R.id.na);
        da=(TextView)findViewById(R.id.de);

        national = (RadioButton) findViewById(R.id.national);
        international = (RadioButton) findViewById(R.id.international);
        persons = (EditText) findViewById(R.id.persons);
        btn_register=(Button)findViewById(R.id.btn_register);
        book=new bookings();
        fdatabase = FirebaseDatabase.getInstance();
        ref = fdatabase.getReference("booking");
        ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {

                    maxid=(dataSnapshot.getChildrenCount());

                }

            }

            public void onCancelled(DatabaseError databaseError) {
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getvalues();

             ref.child(String.valueOf(maxid+1)).setValue(book);

                Toast.makeText(booking.this,"uploaded",Toast.LENGTH_LONG).show();



            }
        });
        Intent i = getIntent();
        String a = i.getExtras().getString("name");
        String b = i.getExtras().getString("desY");

        na.setText(a);
        da.setText(b);


    }


    private void getvalues() {
        String gender1 = " ";
        if (male.isChecked()) {
            gender1 = "Male";
        }
        if (female.isChecked()) {
            gender1 = "Femal";
        }
        String nationalitys = " ";
        if (national.isChecked()) {
            nationalitys = "national";
        }
        if (international.isChecked()) {
            nationalitys = "international";
        }
        book.setFname(Fname.getText().toString());
        book.setName(name.getText().toString());
        book.setEmail(email.getText().toString());
        book.setPhone(phone.getText().toString());
        book.setGender(gender1.toString());
        book.setNationality(nationalitys.toString());
        book.setPersons(persons.getText().toString());
        book.setPerson(na.getText().toString());

    }
}

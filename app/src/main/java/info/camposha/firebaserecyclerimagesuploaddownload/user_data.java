package info.camposha.firebaserecyclerimagesuploaddownload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import info.camposha.firebaserecyclerimagesuploaddownload.Model.bookings;

public class user_data extends AppCompatActivity {
    ListView list;
   FirebaseDatabase database;
   DatabaseReference ref;
   int i;
   long maxed=0;
   ArrayList<String> booking;
   bookings book;
   ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        list=(ListView)findViewById(R.id.list);
        database=FirebaseDatabase.getInstance();
        book=new bookings();


        ref=database.getReference("booking");
        booking=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<String>(this,R.layout.user_info,R.id.name,booking);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
               book=ds.getValue(bookings.class);
               booking.add("Full Name : "+book.getFname().toString()+"\nMonths : " + book.getName().toString()+ "\nGender : " + book.getGender().toString()+ "\nNationality : " + book.getNationality().toString()+ "\nPhone : " + book.getPhone().toString());

                }
                list.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}

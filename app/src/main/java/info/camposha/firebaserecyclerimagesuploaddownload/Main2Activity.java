package info.camposha.firebaserecyclerimagesuploaddownload;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import info.camposha.firebaserecyclerimagesuploaddownload.Model.Teacher;
import info.camposha.firebaserecyclerimagesuploaddownload.View.ItemsActivity;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
 private DrawerLayout drawer1;
 private ActionBarDrawerToggle toggle;
      List<Teacher> main_teachers;
 private NavigationView navigationView;
 FirebaseAuth auth;
 FirebaseUser currentuser;
  Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        drawer1=(DrawerLayout)findViewById(R.id.drawer1);
        toolbar=(Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        auth=FirebaseAuth.getInstance();
        currentuser=auth.getCurrentUser();

        NavigationView navigationView=(NavigationView)findViewById(R.id.navigation);
        getdata_if_user();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(this,drawer1,toolbar,R.string.open,R.string.close);
        drawer1.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


    }

    public void search(View view) {
        Intent in= new Intent(this, ItemsActivity.class);
        startActivity(in);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.massage) {
            Toast.makeText(getApplicationContext(), "massage", Toast.LENGTH_SHORT).show();
           // startActivity(new Intent(Main2Activity.this, massgae.class));
        } else if (id == R.id.contuct) {
            Toast.makeText(getApplicationContext(), "Contact", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Main2Activity.this,contuct.class));

        } else if (id == R.id.about) {
            Toast.makeText(getApplicationContext(), "About", Toast.LENGTH_SHORT).show();
         startActivity(new Intent(Main2Activity.this,about.class));
        }else if (id == R.id.signout) {
            Toast.makeText(getApplicationContext(), " signout", Toast.LENGTH_SHORT).show();
            if (id == R.id.signout) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Main2Activity.this, login.class));
                finish();
            }
            // startActivity(new Intent(Main2Activity.this,about.class));
        }

        close();
        return true;

    }

    private void  close()
    {
        drawer1.closeDrawer(GravityCompat.START);
    }
    private void open()
    {
        drawer1.openDrawer(GravityCompat.START);


    }

    @Override
    public void onBackPressed() {

       if(drawer1.isDrawerOpen(GravityCompat.START))
        {
           close();
        }
    super.onBackPressed();
    }


    private void getdata_if_user()
    {
        NavigationView navigationView=(NavigationView)findViewById(R.id.navigation);
        View headerView=navigationView.getHeaderView(0);
        ImageView nav_mageView=headerView.findViewById(R.id.nav_imageView);
        TextView nav_name=headerView.findViewById(R.id.nav_name);
        TextView nav_email=headerView.findViewById(R.id.nav_email);
        nav_email.setText(currentuser.getEmail());
        nav_name.setText(currentuser.getDisplayName());
      Glide.with(this).load(currentuser.getPhotoUrl()).into(nav_mageView);


    }


    public void profile(View view) {
        Intent in=new Intent(this,profil.class);
        startActivity(in);
    }

    public void books(View view) {
        Intent a=new Intent(Main2Activity.this,user_data.class);
        startActivity(a);
    }
}

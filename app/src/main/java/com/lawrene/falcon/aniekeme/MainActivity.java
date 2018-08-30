package com.lawrene.falcon.aniekeme;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;

    FirebaseAuth mFireAuth;
    FirebaseUser mCurrentUSer;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFireAuth = FirebaseAuth.getInstance();

        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("School Planner");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Setting up navigationbar
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //This is to load the home fragment at the beginning of the process beggining process
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.holder_layout, new HomeFragment());
        fragmentTransaction.commit();


        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        getSupportActionBar().setTitle("School Planner");
                        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.holder_layout, new HomeFragment());
                        fragmentTransaction1.commit();
                        break;

                    case R.id.calender:
                        getSupportActionBar().setTitle("Calender");
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.holder_layout, new CalenderFragment());
                        fragmentTransaction2.commit();
                        break;

                    case R.id.tasks:
                        getSupportActionBar().setTitle("Tasks");
                        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.holder_layout, new TasksFragment());
                        fragmentTransaction3.commit();
                        break;

                    case R.id.add_home_work:
                        startActivity(new Intent(MainActivity.this, AddHomeWork.class));
                        break;

                    case R.id.exams:
                        startActivity(new Intent(MainActivity.this, AddExams.class));
                        break;


                    case R.id.grades:
                        getSupportActionBar().setTitle("Grades");
                        FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction4.replace(R.id.holder_layout, new GradesFragment());
                        fragmentTransaction4.commit();
                        break;

                    case R.id.courses:
                        getSupportActionBar().setTitle("Courses");
                        FragmentTransaction fragmentTransaction5 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction5.replace(R.id.holder_layout, new CoursesFragment());
                        fragmentTransaction5.commit();
                        break;

                    case R.id.notes:
                        getSupportActionBar().setTitle("Notes");
                        FragmentTransaction fragmentTransaction6 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction6.replace(R.id.holder_layout, new NotesFragment());
                        fragmentTransaction6.commit();
                        break;

                    case R.id.logout_nav:
                        mFireAuth.signOut();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }

                DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    long previousTime;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
            if (2000 + previousTime > (previousTime = System.currentTimeMillis())) {
                MainActivity.this.finish();
                moveTaskToBack(true);

            } else {
                Toast.makeText(getBaseContext(), "Touch again to exit", Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    protected void onStart() {
//        mCurrentUSer = mFireAuth.getCurrentUser();
//        if (mCurrentUSer == null){
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//        }
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.logout){
            mFireAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        }
        if(item.getItemId() == R.id.sync){
            if(isConnectingToInternet(this)){
                Toast.makeText(this, "Records have been synced online", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "No internet connection please switch on mobile data or wifi to sync data", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity =
                (ConnectivityManager) context.getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

}

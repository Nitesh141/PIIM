package com.nitescorp.piim;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.nitescorp.piim.R.id.textView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    String status;
    public String pj1st=null,pj2st=null,pj3st=null;
    public String[] pjstatus = new String[100];
    public int selectedIndex=-1;
    public int count =1;
    String m_Text  = "what the fuck!!";
    public int ind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final CharSequence[] items = {" Current "," Finished " };
        final ArrayList seletedItems=new ArrayList();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            String pid;
            int resID;
            @Override
            public void onClick(View view) {

                if (count < 18) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Project Title");

                    // Set up the input
                    final EditText input = new EditText(MainActivity.this);
                    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                    builder.setView(input);



                    builder.setSingleChoiceItems(items, selectedIndex, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            selectedIndex=which;
                            status = (String)items[selectedIndex];


                        }
                    });


//                    builder.setMultiChoiceItems(items, null,
//                            new DialogInterface.OnMultiChoiceClickListener() {
//                                // indexSelected contains the index of item (of which checkbox checked)
//                                @Override
//                                public void onClick(DialogInterface dialog, int indexSelected,
//                                                    boolean isChecked) {
//                                    if (isChecked) {
//                                        // If the user checked the item, add it to the selected items
//                                        // write your code when user checked the checkbox
//                                        seletedItems.add(indexSelected);
//                                    } else if (seletedItems.contains(indexSelected)) {
//                                        // Else, if the item is already in the array, remove it
//                                        // write your code when user Uchecked the checkbox
//                                        seletedItems.remove(Integer.valueOf(indexSelected));
//                                    }
//                                }
//                            });



                    // Set up the buttons
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            m_Text = input.getText().toString();
                            pid = "pj" + (count);

                            resID = getResources().getIdentifier(pid, "id", getPackageName());
                            TextView pjtView = (TextView)findViewById(resID);
                            pjtView.setVisibility(View.VISIBLE);
                            pjtView.setText(m_Text);
                            pjtView.setTypeface(null, Typeface.BOLD);
                            pjstatus[count-1] = status;
                            count = count + 1;
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();





                  }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.all_projects) {

            Intent i = new Intent(this, AllProjects.class);
            startActivity( i );

            // Handle the camera action
        } else if (id == R.id.current_projects) {
            Intent i = new Intent(this, CurrentProjects.class);
            startActivity( i );


        } else if (id == R.id.completed_projects) {
            Intent i = new Intent(this, CompletedProjects.class);
            startActivity( i );

        } else if (id == R.id.stats) {
            Intent i = new Intent(this, Stats.class);
            startActivity( i );

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void openProjectDetails(View  view){

        Intent i = new Intent(this, ProjectDetails.class);
        startActivity( i );
        // String nameid = view.getTag().toString();
        String IdAsString = view.getResources().getResourceName(view.getId());
        String idindex = IdAsString.substring( 24 );
        ind = Integer.parseInt( idindex );
//        TextView pjtStatusView = (TextView) findViewById( R.id.projectdetail_status_text_view );
//        pjtStatusView.setText( pjstatus[ind] );

    }

    public void sortProjects(){

        //this sorts according to categories

    }


    public String[] getValueofpjstatus()
    {
        return pjstatus;
    }









    }


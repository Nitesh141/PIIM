package com.nitescorp.piim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProjectDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        MainActivity aa = new MainActivity();
        String[] a = aa.pjstatus;
        int indind = aa.ind;
        String b = aa.pj1st;

        TextView pjtStatusView = (TextView) findViewById( R.id.projectdetail_status_text_view );
        pjtStatusView.setText( b );



    }
}

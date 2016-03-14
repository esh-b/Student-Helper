package com.example.eshwanth.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity1_vin extends AppCompatActivity implements AsyncResponse_vin,View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1_vin);

        String username = "CS14BTECH11011";
        User_vin user = new User_vin(username);
       // authenticate(user);

        queryAsyncTask_vin q = new queryAsyncTask_vin(this,user);
        q.execute();
        q.delegate = this;


    }

   private static final int[][] idArray ={ {R.id.button11, R.id.button12, R.id.button13, R.id.button14, R.id.button15, R.id.button16},
    {R.id.button21, R.id.button22, R.id.button23, R.id.button24, R.id.button25, R.id.button26},
    {R.id.button31, R.id.button32, R.id.button33, R.id.button34, R.id.button35, R.id.button36},
    {R.id.button41, R.id.button42, R.id.button43, R.id.button44, R.id.button45, R.id.button46},
    {R.id.button51, R.id.button52, R.id.button53, R.id.button54, R.id.button55, R.id.button56}};

   // private static final Button[][] button = new Button[0][];
    public void processFinish(final String[][] delegate) {
    if(delegate.length != 0) {

        final int x = delegate.length;

        int y, z, a, b,c;
        for (int i = 0; i < x; i++) {
            // int i = 5;
            if (delegate[i][3] != null && delegate[i][2] != null && delegate[i][1] != null && !delegate[i][3].isEmpty() && !delegate[i][2].isEmpty() && !delegate[i][1].isEmpty()) {

               // String text = Spinner.getSelectedItem().toString();
               // z = Integer.parseInt(text);
                // a = Integer.parseInt(delegate[i][1]);
                //  b = Integer.parseInt(delegate[i][2]);
                String phrase1 = delegate[i][1];
                String phrase2 = delegate[i][2];
                String phrase3 = delegate[i][3];
                // String delims = ",";
                String[] tokens1 = phrase1.split(",");
                String[] tokens2 = phrase2.split(",");
                String[] tokens3 = phrase3.split(",");
                // String[] tokens = phrase.split(delims);
                for(int k=0;k<tokens3.length;k++) {
                    c = Integer.parseInt(tokens3[k]);

                    if (c == 1) {
                        for (int j = 0; j < tokens1.length; j++) {
                            a = Integer.parseInt(tokens1[j]);
                            b = Integer.parseInt(tokens2[j]);
                            Button button = (Button) findViewById(idArray[a - 1][b - 1]);
                            button.setText(delegate[i][0].toUpperCase());
                            button.setOnClickListener(new MyListener(a, b, i, delegate));
                        }
                    }
                }

            }
        }

        final Spinner Spinner = (Spinner) findViewById(R.id.spinner);

        Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            AlertDialog.Builder builder1;
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                for(int i=0;i<5;i++){
                    for(int j=0;j<6;j++){
                        final Button button = (Button) findViewById(idArray[i][j]);
                        button.setText("");
                    }

                }
                int y, z, a, b,c;
                for (int i = 0; i < x; i++) {
                    // int i = 5;
                    if (delegate[i][3] != null && delegate[i][2] != null && delegate[i][1] != null && !delegate[i][3].isEmpty() && !delegate[i][2].isEmpty() && !delegate[i][1].isEmpty()) {
                        String text = Spinner.getSelectedItem().toString();
                        z = Integer.parseInt(text);
                        // a = Integer.parseInt(delegate[i][1]);
                        //  b = Integer.parseInt(delegate[i][2]);
                        String phrase1 = delegate[i][1];
                        String phrase2 = delegate[i][2];
                        String phrase3 = delegate[i][3];
                        // String delims = ",";
                        String[] tokens1 = phrase1.split(",");
                        String[] tokens2 = phrase2.split(",");
                        String[] tokens3 = phrase3.split(",");
                        // String[] tokens = phrase.split(delims);
                        for(int k=0;k<tokens3.length;k++) {
                            c = Integer.parseInt(tokens3[k]);
                            if (c == z) {
                                for (int j = 0; j < tokens1.length; j++) {
                                    a = Integer.parseInt(tokens1[j]);
                                    b = Integer.parseInt(tokens2[j]);
                                    final Button button = (Button) findViewById(idArray[a - 1][b - 1]);
                                    button.setText(delegate[i][0].toUpperCase());
                                    button.setOnClickListener(new MyListener(a, b, i, delegate));

                                }
                            }
                        }
                    }
                }
                // button.setText(delegate[1][0].toUpperCase());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

    }}

    @Override
    public void onClick(View view) {

    }

    private class MyListener implements Button.OnClickListener {
        int pos;
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity1_vin.this,R.style.NewDialog);
        String[][] delegate1;
        public MyListener (int x, int y, int i, String[][] array) {
           // pos = position;
            delegate1 = array;
            pos = i;
        }
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
           // Intent getContactIntent = new Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI);
           // startActivityForResult(getContactIntent, pos);

            builder1.setCancelable(true);
            builder1.setMessage("COURSE NAME :: " + delegate1[pos][0] + "\n"
                    + "COURSE_ID :: " + delegate1[pos][4] + "\n"
                    + "NO. OF CREDITS :: " + delegate1[pos][6]+"\n"
                    + "PROF NAME :: " + delegate1[pos][5]);
            builder1.show();

        }
    }
}


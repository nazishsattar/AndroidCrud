package com.example.teacher.sqlite_201608b;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DbClass c;
    Button bs,bu,bd,bsr;
    EditText lr,ln,la;
    Cursor cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c=new DbClass(getApplicationContext());

        bs=(Button)findViewById(R.id.btnSave);
        bu=(Button)findViewById(R.id.btnUpdate);
        bd=(Button)findViewById(R.id.btnDelete);
        bsr=(Button)findViewById(R.id.btnSearch);

        lr=(EditText)findViewById(R.id.lblR);
        ln=(EditText)findViewById(R.id.lblN);
        la=(EditText)findViewById(R.id.lblAd);

    }

    public void GetMethod(View v){
        switch(v.getId()){
            case R.id.btnSave:
                c.AddStudent(ln.getText().toString(),la.getText().toString());
                Toast.makeText(this,"Record Inserted Successfully",Toast.LENGTH_LONG).show();
                break;
            case R.id.btnUpdate:
                c.UpdateStudent(ln.getText().toString(),la.getText().toString(),lr.getText().toString());
                Toast.makeText(this,"Record Updated Successfully",Toast.LENGTH_LONG).show();
                break;
            case R.id.btnDelete:
                c.DeleteStudent(lr.getText().toString());
                Toast.makeText(this,"Record Deleted Successfully",Toast.LENGTH_LONG).show();
                break;
            case R.id.btnSearch:
                cr=c.ShowData();
                if(cr.moveToFirst()){

                    do{
                    String r=cr.getString(cr.getColumnIndex(c.ID));
                        String n=cr.getString(cr.getColumnIndex(c.SN));
                        String a=cr.getString(cr.getColumnIndex(c.AD));
                        Toast.makeText(this,"Roll number is "+r+"\n"
                        +"Student Name is "+n+"\n"
                                +"Student Address is "+a,
                                Toast.LENGTH_LONG).show();

                    }
                    while(cr.moveToNext());
                }
                cr.close();
                break;

        }
    }
}
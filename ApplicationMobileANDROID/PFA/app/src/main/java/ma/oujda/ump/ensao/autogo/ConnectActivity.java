package ma.oujda.ump.ensao.autogo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConnectActivity extends AppCompatActivity {

    static volatile boolean stopThread =false;

    static Button connectButton ;
    static Button nextButton ;
    static TextView text;
    private EditText IpText;
    private EditText PortText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        connectButton=findViewById(R.id.connect);
        nextButton=findViewById(R.id.next);
        text=findViewById(R.id.textView);
        IpText=findViewById(R.id.editText1);
        PortText=findViewById(R.id.editText2);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainActivityIntent = new Intent(ConnectActivity.this, ModeActivity.class);
                startActivity(mainActivityIntent);

            }
        });

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(PortText.getText().toString())){
                    PortText.setError("Champ obligatoire !");
                    PortText.requestFocus();
                }
                if(TextUtils.isEmpty(IpText.getText().toString())){
                    IpText.setError("Champ obligatoire !");
                    IpText.requestFocus();
                }
                else if(!TextUtils.isEmpty(IpText.getText().toString()) && !TextUtils.isEmpty(PortText.getText().toString())){
                    if(connectButton.getText().equals("Se connecter")){
                        stopThread=false;
                        new Thread(
                                new ClientConnexion(IpText.getText().toString(),Integer.parseInt(PortText.getText().toString()))
                        ).start();
                    }
                    else{
                        stopThread=true;
                    }
                }
            }
        });

    }


    @Override
    public void onBackPressed(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Voulez vous quitter ?")
                .setCancelable(false)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        stopThread=true;

                        ConnectActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
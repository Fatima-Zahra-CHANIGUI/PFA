package ma.oujda.ump.ensao.autogo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ManuelActivity extends AppCompatActivity {

    private Button fwdBtn , rightBtn , leftBtn , bwdBtn , stopBtn;
    static TextView manTextView;
    static Boolean manFwd=false , manRight=false , manLeft=false , manBwd=false , manStop =false ;
    static Boolean stopManuelMode=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manuel);

        fwdBtn =findViewById(R.id.forward);
        rightBtn =findViewById(R.id.right);
        leftBtn=findViewById(R.id.left);
        bwdBtn=findViewById(R.id.backward);
        stopBtn=findViewById(R.id.stop);
        manTextView=findViewById(R.id.manText);

        fwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manFwd=true;
            }
        });

        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manRight=true;
            }
        });

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manLeft=true;
            }
        });

        bwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manBwd=true;
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manStop=true;
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        stopManuelMode=true;
        Intent mainActivityIntent = new Intent(ManuelActivity.this, ModeActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }
}

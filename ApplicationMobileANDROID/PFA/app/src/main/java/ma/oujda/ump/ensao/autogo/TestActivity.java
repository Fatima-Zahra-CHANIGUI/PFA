package ma.oujda.ump.ensao.autogo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TestActivity extends AppCompatActivity {

    private EditText PARAM_VITES_PAS_EditText,PARAM_I_FOR_GO_LEFT_EditText,PARAM_DELAY_GO_LEFT_EditText,PARAM_STOP_GO_LEFT_EditText,PARAM_I_FOR_GO_RIGHT_EditText,PARAM_DELAY_GO_RIGHT_EditText,PARAM_STOP_GO_RIGHT_EditText,PARAM_ANNUL_TEST_EditText,PARAM_I_FOR_GO_FORWARD_EditText,PARAM_CORRECTION_EditText,PARAM_VITES_L_EditText,PARAM_VITES_R_EditText;
    private Button envoyer_btn;
    private int PARAM_VITES_PAS, PARAM_I_FOR_GO_LEFT, PARAM_DELAY_GO_LEFT, PARAM_STOP_GO_LEFT, PARAM_I_FOR_GO_RIGHT, PARAM_DELAY_GO_RIGHT, PARAM_STOP_GO_RIGHT, PARAM_ANNUL_TEST, PARAM_I_FOR_GO_FORWARD, PARAM_CORRECTION, PARAM_VITES_L, PARAM_VITES_R;
    static Boolean stopTestMode=false;
    static Boolean sendTestObject =false ;
    static TestObject testObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        PARAM_VITES_PAS_EditText = findViewById(R.id.PARAM_VITES_PAS);
        PARAM_I_FOR_GO_LEFT_EditText = findViewById(R.id.PARAM_I_FOR_GO_LEFT);
        PARAM_DELAY_GO_LEFT_EditText = findViewById(R.id.PARAM_DELAY_GO_LEFT);
        PARAM_STOP_GO_LEFT_EditText = findViewById(R.id.PARAM_STOP_GO_LEFT);
        PARAM_I_FOR_GO_RIGHT_EditText = findViewById(R.id.PARAM_I_FOR_GO_RIGHT);
        PARAM_DELAY_GO_RIGHT_EditText = findViewById(R.id.PARAM_DELAY_GO_RIGHT);
        PARAM_STOP_GO_RIGHT_EditText = findViewById(R.id.PARAM_STOP_GO_RIGHT);
        PARAM_ANNUL_TEST_EditText = findViewById(R.id.PARAM_ANNUL_TEST);
        PARAM_I_FOR_GO_FORWARD_EditText = findViewById(R.id.PARAM_I_FOR_GO_FORWARD);
        PARAM_CORRECTION_EditText = findViewById(R.id.PARAM_CORRECTION);
        PARAM_VITES_L_EditText = findViewById(R.id.PARAM_VITES_L);
        PARAM_VITES_R_EditText = findViewById(R.id.PARAM_VITES_R);

        envoyer_btn = findViewById(R.id.sendTest);

        envoyer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PARAM_VITES_PAS = Integer.parseInt(PARAM_VITES_PAS_EditText.getText().toString());
                PARAM_I_FOR_GO_LEFT = Integer.parseInt(PARAM_I_FOR_GO_LEFT_EditText.getText().toString());
                PARAM_DELAY_GO_LEFT = Integer.parseInt(PARAM_DELAY_GO_LEFT_EditText.getText().toString());
                PARAM_STOP_GO_LEFT = Integer.parseInt(PARAM_STOP_GO_LEFT_EditText.getText().toString());
                PARAM_I_FOR_GO_RIGHT = Integer.parseInt(PARAM_I_FOR_GO_RIGHT_EditText.getText().toString());
                PARAM_DELAY_GO_RIGHT = Integer.parseInt(PARAM_DELAY_GO_RIGHT_EditText.getText().toString());
                PARAM_STOP_GO_RIGHT = Integer.parseInt(PARAM_STOP_GO_RIGHT_EditText.getText().toString());
                PARAM_ANNUL_TEST = Integer.parseInt(PARAM_ANNUL_TEST_EditText.getText().toString());
                PARAM_I_FOR_GO_FORWARD = Integer.parseInt(PARAM_I_FOR_GO_FORWARD_EditText.getText().toString());
                PARAM_CORRECTION = Integer.parseInt(PARAM_CORRECTION_EditText.getText().toString());
                PARAM_VITES_L = Integer.parseInt(PARAM_VITES_L_EditText.getText().toString());
                PARAM_VITES_R = Integer.parseInt(PARAM_VITES_R_EditText.getText().toString());

                testObject = new TestObject(PARAM_VITES_PAS, PARAM_I_FOR_GO_LEFT, PARAM_DELAY_GO_LEFT, PARAM_STOP_GO_LEFT, PARAM_I_FOR_GO_RIGHT, PARAM_DELAY_GO_RIGHT, PARAM_STOP_GO_RIGHT, PARAM_ANNUL_TEST, PARAM_I_FOR_GO_FORWARD, PARAM_CORRECTION, PARAM_VITES_L, PARAM_VITES_R);

                sendTestObject=true;
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        stopTestMode=true;
        Intent mainActivityIntent = new Intent(TestActivity.this, ModeActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }
}

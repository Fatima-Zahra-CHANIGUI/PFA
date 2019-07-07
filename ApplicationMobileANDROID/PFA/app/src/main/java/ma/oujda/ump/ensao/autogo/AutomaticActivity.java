package ma.oujda.ump.ensao.autogo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class AutomaticActivity extends AppCompatActivity {

    static EditText rowsText;
    static EditText colsText;
    static EditText xdText,ydText,xaText,yaText,xbText,ybText;
    private Button addBlockButton;
    private Button sendButton;
    private int rows,cols,xd,yd,xa,ya,xb,yb;
    static AutoObject autoObject;
    private Node initialNode, finalNode,block;
    private ArrayList<Node> obstacles = new ArrayList<>();
    static Boolean sendAutoObject =false ;
    static Boolean stopAutoMode=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatic);


        rowsText = findViewById(R.id.rowsText);
        colsText = findViewById(R.id.colsText);
        xdText = findViewById(R.id.xdText);
        ydText = findViewById(R.id.ydText);
        xaText = findViewById(R.id.xaText);
        yaText = findViewById(R.id.yaText);
        xbText = findViewById(R.id.xbText);
        ybText = findViewById(R.id.ybText);
        addBlockButton = findViewById(R.id.plusBlock);
        sendButton = findViewById(R.id.send);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(rowsText.getText().toString()) ||
                        TextUtils.isEmpty(colsText.getText().toString()) ||
                        TextUtils.isEmpty(xdText.getText().toString()) ||
                        TextUtils.isEmpty(ydText.getText().toString()) ||
                        TextUtils.isEmpty(xaText.getText().toString()) ||
                        TextUtils.isEmpty(yaText.getText().toString())
                ){
                    Toast.makeText(getApplicationContext(),"Champs Vide !", Toast.LENGTH_SHORT).show();
                }
                else{

                    rows = Integer.parseInt(rowsText.getText().toString());
                    cols = Integer.parseInt(colsText.getText().toString());
                    xd = Integer.parseInt(xdText.getText().toString());
                    yd = Integer.parseInt(ydText.getText().toString());
                    xa = Integer.parseInt(xaText.getText().toString());
                    ya = Integer.parseInt(yaText.getText().toString());

                    if(( xd >= rows ) || ( xa >= rows ) || ( yd >= cols ) || ( ya >= cols )){
                        Toast.makeText(getApplicationContext(),"donnée invalide !", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        initialNode = new Node(xd,yd);
                        finalNode = new Node(xa,ya);

                        autoObject = new AutoObject(rows,cols,initialNode,finalNode,obstacles);

                        sendAutoObject = true ;

                        Intent mainActivityIntent = new Intent(AutomaticActivity.this, LoadingScreen.class);
                        startActivity(mainActivityIntent);
                        finish();
                    }
                }
            }
        });

        addBlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(xbText.getText().toString()) ||
                        TextUtils.isEmpty(ybText.getText().toString())
                ){
                    Toast.makeText(getApplicationContext(),"Veuillez saisir un obstacle !", Toast.LENGTH_SHORT).show();
                }
                else{

                    xb = Integer.parseInt(xbText.getText().toString());
                    yb = Integer.parseInt(ybText.getText().toString());
                    rows = Integer.parseInt(rowsText.getText().toString());
                    cols = Integer.parseInt(colsText.getText().toString());
                    xd = Integer.parseInt(xdText.getText().toString());
                    yd = Integer.parseInt(ydText.getText().toString());
                    xa = Integer.parseInt(xaText.getText().toString());
                    ya = Integer.parseInt(yaText.getText().toString());

                    if((xd==xb && yd==yb)||
                            (xa==xb && ya==yb)||
                            (xb >= rows)||
                            (yb >= cols)
                    ) {
                        Toast.makeText(getApplicationContext(),"donnée invalide !", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        block = new Node(xb,yb);
                        obstacles.add(block);
                        xbText.setText("");
                        ybText.setText("");
                    }
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        stopAutoMode=true;
        Intent mainActivityIntent = new Intent(AutomaticActivity.this, ModeActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }
}
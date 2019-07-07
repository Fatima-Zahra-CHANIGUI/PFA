package ma.oujda.ump.ensao.autogo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static ma.oujda.ump.ensao.autogo.AutomaticActivity.colsText;
import static ma.oujda.ump.ensao.autogo.AutomaticActivity.rowsText;
import static ma.oujda.ump.ensao.autogo.AutomaticActivity.stopAutoMode;
import static ma.oujda.ump.ensao.autogo.AutomaticActivity.xaText;
import static ma.oujda.ump.ensao.autogo.AutomaticActivity.xbText;
import static ma.oujda.ump.ensao.autogo.AutomaticActivity.xdText;
import static ma.oujda.ump.ensao.autogo.AutomaticActivity.yaText;
import static ma.oujda.ump.ensao.autogo.AutomaticActivity.ybText;
import static ma.oujda.ump.ensao.autogo.AutomaticActivity.ydText;
import static ma.oujda.ump.ensao.autogo.ClientConnexion.autoObjectServer;
import static ma.oujda.ump.ensao.autogo.ModeActivity.mode;

public class ResultPath extends AppCompatActivity {

    private TextView text;
    private Button redoAstar_btn,autoToMode_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_path);

        text = findViewById(R.id.resultAlgoAStar);
        redoAstar_btn = findViewById(R.id.RedoAstar);
        autoToMode_btn= findViewById(R.id.autoToMode);

        if(autoObjectServer!=null){
            text.setText(autoObjectServer.toString());
        }

        rowsText.setText("");
        colsText.setText("");
        xdText.setText("");
        ydText.setText("");
        xaText.setText("");
        yaText.setText("");
        xbText.setText("");
        ybText.setText("");
        autoObjectServer=null;

        redoAstar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int redoAstar_btn_color = ((ColorDrawable)redoAstar_btn.getBackground()).getColor();
                int white = Color.WHITE;

                if(redoAstar_btn_color==white){
                    redoAstar_btn.setBackgroundColor(Color.BLACK);
                    redoAstar_btn.setTextColor(Color.WHITE);
                    autoToMode_btn.setBackgroundColor(Color.WHITE);
                    mode="auto";
                    Intent mainActivityIntent = new Intent(ResultPath.this, AutomaticActivity.class);
                    startActivity(mainActivityIntent);
                    finish();
                }
                else{
                    redoAstar_btn.setBackgroundColor(Color.WHITE);
                    redoAstar_btn.setTextColor(Color.BLACK);
                }
            }
        });

        autoToMode_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int autoToMode_btn_color = ((ColorDrawable)autoToMode_btn.getBackground()).getColor();
                int white = Color.WHITE;

                if(autoToMode_btn_color==white){
                    autoToMode_btn.setBackgroundColor(Color.BLACK);
                    autoToMode_btn.setTextColor(Color.WHITE);
                    redoAstar_btn.setBackgroundColor(Color.WHITE);
                    mode="";
                    stopAutoMode=true;
                    Intent mainActivityIntent = new Intent(ResultPath.this, ModeActivity.class);
                    startActivity(mainActivityIntent);
                    finish();
                }
                else{
                    autoToMode_btn.setBackgroundColor(Color.WHITE);
                    autoToMode_btn.setTextColor(Color.BLACK);
                }
            }
        });


    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        stopAutoMode=true;
        Intent mainActivityIntent = new Intent(ResultPath.this, ModeActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }
}

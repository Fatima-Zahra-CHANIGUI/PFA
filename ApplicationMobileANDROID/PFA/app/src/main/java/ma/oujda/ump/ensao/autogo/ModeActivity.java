package ma.oujda.ump.ensao.autogo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import static ma.oujda.ump.ensao.autogo.ManuelActivity.stopManuelMode;
import static ma.oujda.ump.ensao.autogo.AutomaticActivity.stopAutoMode;
import static ma.oujda.ump.ensao.autogo.TestActivity.stopTestMode;

public class ModeActivity extends AppCompatActivity {

    static Button manuel_button;
    static Button auto_button;
    static Button test_button;
    static volatile String mode="" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        manuel_button = findViewById(R.id.btn2);
        auto_button = findViewById(R.id.btn1);
        test_button = findViewById(R.id.btn3);

        manuel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int manuel_btn_Color = ((ColorDrawable)manuel_button.getBackground()).getColor();
                int white = Color.WHITE;

                if(manuel_btn_Color==white){
                    manuel_button.setBackgroundColor(Color.BLACK);
                    manuel_button.setTextColor(Color.WHITE);
                    auto_button.setBackgroundColor(Color.WHITE);
                    test_button.setBackgroundColor(Color.WHITE);
                    mode="manuel";
                    stopManuelMode=false;
                    Intent mainActivityIntent = new Intent(ModeActivity.this, ManuelActivity.class);
                    startActivity(mainActivityIntent);
                    finish();
                }
                else{
                    manuel_button.setBackgroundColor(Color.WHITE);
                    manuel_button.setTextColor(Color.BLACK);
                    mode="";
                    stopManuelMode=true;
                }
            }
        });

        auto_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int auto_btn_Color = ((ColorDrawable)auto_button.getBackground()).getColor();
                int white = Color.WHITE;

                if(auto_btn_Color==white){
                    auto_button.setBackgroundColor(Color.BLACK);
                    auto_button.setTextColor(Color.WHITE);
                    manuel_button.setBackgroundColor(Color.WHITE);
                    test_button.setBackgroundColor(Color.WHITE);
                    mode="auto";
                    stopAutoMode=false;
                    Intent mainActivityIntent = new Intent(ModeActivity.this, AutomaticActivity.class);
                    startActivity(mainActivityIntent);
                    finish();
                }
                else{
                    auto_button.setBackgroundColor(Color.WHITE);
                    auto_button.setTextColor(Color.BLACK);
                    mode="";
                    stopAutoMode=true;
                }

            }
        });

        test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int test_btn_Color = ((ColorDrawable)test_button.getBackground()).getColor();
                int white = Color.WHITE;

                if(test_btn_Color==white){
                    test_button.setBackgroundColor(Color.BLACK);
                    test_button.setTextColor(Color.WHITE);
                    auto_button.setBackgroundColor(Color.WHITE);
                    manuel_button.setBackgroundColor(Color.WHITE);
                    mode="test";
                    stopTestMode=false;
                    Intent mainActivityIntent = new Intent(ModeActivity.this, TestActivity.class);
                    startActivity(mainActivityIntent);
                    finish();
                }
                else{
                    test_button.setBackgroundColor(Color.WHITE);
                    test_button.setTextColor(Color.BLACK);
                    mode="";
                    stopTestMode=true;
                }

            }
        });
    }
}

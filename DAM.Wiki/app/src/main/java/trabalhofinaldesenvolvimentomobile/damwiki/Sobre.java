package trabalhofinaldesenvolvimentomobile.damwiki;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Sobre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
    }

    public void backMainActivity(View view) {
        finish();
    }
}

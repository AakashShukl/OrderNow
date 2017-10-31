package pay4free.in.ordernow.Model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pay4free.in.ordernow.R;


public class signin extends AppCompatActivity {
EditText phone,pass;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        phone=(EditText)findViewById(R.id.edtphone);
        pass=(EditText)findViewById(R.id.password);
        button=(Button)findViewById(R.id.signin);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

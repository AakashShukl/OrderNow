package pay4free.in.ordernow;

import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;
import pay4free.in.ordernow.Database.Database;
import pay4free.in.ordernow.Model.Order;
import pay4free.in.ordernow.Model.Request;
import pay4free.in.ordernow.ViewHolder.CartAdapter;
import pay4free.in.ordernow.common.Common;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference Request;
    FButton button;
    TextView texttotalPrice;
    List<Order> cart=new ArrayList<>();
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        database=FirebaseDatabase.getInstance();
        Request=database.getReference("Requests");


        recyclerView=(RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        button=(FButton)findViewById(R.id.btnPlaceOrder);
        texttotalPrice=(TextView)findViewById(R.id.total);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        showAlertDialog();
    }
});
        loadListFood();


    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(Cart.this);
        alertDialog.setTitle("One more Step");
        alertDialog.setMessage("Enter your Address");

        final EditText edtaddress=new EditText(Cart.this);
        LinearLayout.LayoutParams layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        edtaddress.setLayoutParams(layoutparams);
        alertDialog.setView(edtaddress);
        alertDialog.setIcon(R.drawable.ic_action_name);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialoginterface,int i) {
               // Request request=new Request(Common.currentuser.getPhoneNumber(),Common.currentuser.getDisplayName(),edtaddress.getText().toString(),texttotalPrice.getText().toString(),cart);
                Request request=new Request("7000569010","Aakash",edtaddress.getText().toString(),texttotalPrice.getText().toString(),cart);
                Request.child(String.valueOf(System.currentTimeMillis())).setValue(request);
                new Database(getBaseContext()).cleancart();
                Toast.makeText(Cart.this,"Thank you,Order Place",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    private void loadListFood() {
     cart=new Database(this).getCarts();
        adapter=new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);


        int total=0;
        for(Order order:cart)
        {
            total=total+(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
            Locale locale=new Locale("en","IN");
            NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
            texttotalPrice.setText(fmt.format(total));
        }

    }
}

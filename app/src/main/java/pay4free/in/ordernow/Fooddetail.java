package pay4free.in.ordernow;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import pay4free.in.ordernow.Database.Database;
import pay4free.in.ordernow.Model.Food;
import pay4free.in.ordernow.Model.Order;

public class Fooddetail extends AppCompatActivity {
    TextView foodname,price,description;
    ImageView foodimage;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart,place;
    ElegantNumberButton elegantNumberButton;
    String foodId="";
    FirebaseDatabase database;
    DatabaseReference foods;
Food currentFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooddetail);
        database=FirebaseDatabase.getInstance();
        foods=database.getReference("Food");
        elegantNumberButton=(ElegantNumberButton)findViewById(R.id.number_button);
      btnCart=(FloatingActionButton)findViewById(R.id.fab);
        description=(TextView)findViewById(R.id.food_description);
        foodname=(TextView)findViewById(R.id.food_name);
        price=(TextView)findViewById(R.id.food_price);
       foodimage=(ImageView)findViewById(R.id.img_food);

       collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);
     collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);


        if(getIntent()!=null)
        {
            foodId=getIntent().getStringExtra("FoodID");
        }
        if(!foodId.isEmpty())
        {
            getdetailfood(foodId);
        }
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addTocart(new Order(foodId,currentFood.getName(),elegantNumberButton.getNumber(),currentFood.getPrice(),currentFood.getDiscount()));
                Toast.makeText(Fooddetail.this,"Added To Cart",Toast.LENGTH_SHORT).show();
            }


        });




    }

    private void getdetailfood(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                currentFood=dataSnapshot.getValue(Food.class);
                if(currentFood!=null) {

                    Picasso.with(getBaseContext()).load(currentFood.getImage()).into(foodimage);
                    collapsingToolbarLayout.setTitle(currentFood.getName());
                    price.setText(currentFood.getPrice());
                    foodname.setText(currentFood.getName());
                    description.setText(currentFood.getDescription());

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


}

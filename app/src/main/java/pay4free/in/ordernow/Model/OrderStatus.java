package pay4free.in.ordernow.Model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pay4free.in.ordernow.R;
import pay4free.in.ordernow.ViewHolder.OrderViewHolder;
import pay4free.in.ordernow.common.Common;

public class OrderStatus extends AppCompatActivity {
  public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Request,OrderViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference requests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        database=FirebaseDatabase.getInstance();
        requests=database.getReference("Requests");
        recyclerView=(RecyclerView)findViewById(R.id.listorders);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
      //  loadOrders(Common.currentuser.getPhoneNumber());
        loadOrders("7000569010");




    }

    private void loadOrders(String s) {
    adapter=new FirebaseRecyclerAdapter<Request, OrderViewHolder>(Request.class,R.layout.order_layout,OrderViewHolder.class,requests.orderByChild("phone").equalTo(s)){
        @Override
        protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {
            viewHolder.OrderId.setText(adapter.getRef(position).getKey());viewHolder.Orderstatus.setText(convertCodetoStatus(model.getStatus()));viewHolder.orderphone.setText(model.getPhone());viewHolder.orderAddress.setText(model.getAddress());
        }
    };

recyclerView.setAdapter(adapter);
}

    private String  convertCodetoStatus(String status) {
 if(status.equals("0"))
return "Placed";
else if(status.equals("1"))
return "On my way";
else
return "Shipped";

}
}
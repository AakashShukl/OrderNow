package pay4free.in.ordernow.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import pay4free.in.ordernow.Model.Order;

/**
 * Created by AAKASH on 12-10-2017.
 */

public class Database extends SQLiteAssetHelper {
    private static final String DB_Name="EatIT.db";
    private static final int DB_Ver=1;
    public Database(Context context) {
        super(context,DB_Name, null, DB_Ver);
    }
    public List<Order> getCarts()
    {
        SQLiteDatabase db=getReadableDatabase();
        SQLiteQueryBuilder queryBuilder=new SQLiteQueryBuilder();
        String[] sqlselect={"ProductName","ProductId","Quantity","Price","Discount"};
        String sqlTable="OrderDetail";
        queryBuilder.setTables(sqlTable);

        Cursor cursor=queryBuilder.query(db,sqlselect,null,null,null,null,null);
        final List<Order> result=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do {
                result.add(new Order(cursor.getString(cursor.getColumnIndex("ProductId")),
                        cursor.getString(cursor.getColumnIndex("ProductName")),
                        cursor.getString(cursor.getColumnIndex("Quantity")),
                        cursor.getString(cursor.getColumnIndex("Price")),
                        cursor.getString(cursor.getColumnIndex("Discount"))
                                ));
            }while (cursor.moveToNext());
        }
        return result;

    }
    public void addTocart(Order order)
    {
        SQLiteDatabase database=getReadableDatabase();
        String query =String.format("INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount) VALUES('%s','%s','%s','%s','%s');",
                order.getProductId(),order.getProductName(),order.getQuantity(),order.getPrice(),order.getDiscount());
        database.execSQL(query);
    }
    public void cleancart()
    {
        SQLiteDatabase database=getReadableDatabase();
        String query =String.format("DELETE FROM OrderDetail");
        database.execSQL(query);
    }


}

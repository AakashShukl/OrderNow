package pay4free.in.ordernow.Model;

/**
 * Created by AAKASH on 09-10-2017.
 */

public class Category {
    private String Name;
    private String Image;
    private String MenuId;
    public Category()
    {

    }

    public Category(String name, String image, String menuId) {
        Name = name;
        Image = image;
        MenuId = menuId;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public String getName() {
        return Name;
    }

    public String getImage() {
        return Image;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setImage(String image) {
        Image = image;
    }
}

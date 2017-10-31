package pay4free.in.ordernow.Model;

/**
 * Created by AAKASH on 10-10-2017.
 */

public class CenterShow {
    private String Name;
    private String Image;




    public CenterShow()
    {

    }


    public CenterShow(String name, String image) {
        Name = name;
        Image = image;

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

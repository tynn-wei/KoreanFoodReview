package koreanfoodreview.C346.tantynnwei;

import java.io.Serializable;

public class Food implements Serializable {

    private int id;
    private String name;
    private String desc;
    private String location;
    private float stars;

    public Food(String name, String desc, String location, float stars) {
        this.name = name;
        this.desc = desc;
        this.location = location;
        this.stars = stars;
    }

    public Food(int id, String name, String desc, String location, float stars) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.location = location;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getLocation() {
        return location;
    }

    public float getStars() {
        return stars;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        String starsString = "";
        for(int i = 0; i < stars; i++)
        {
            starsString += "*";
        }
        return name + "\n" + desc + " - " + location + "\n" + starsString;
    }
}

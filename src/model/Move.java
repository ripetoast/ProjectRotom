package model;

/**
 *
 * @author k196
 */
public class Move{
    private final String name; 
    private final String type;
    private final String category;
    private final int power;
    private final int accuracy;
    
    // optional stuff
    private int level;
    
    public Move(String n, String t, String cat, String p, String acc) { 
        this.name = n; 
        this.type = t; 
        this.category = cat;
        this.power = this.adjust(p);
        this.accuracy = this.adjust(acc);
    }
    
    private int adjust(String val){
        switch (val) {
            case "—":
                return 0; // for stuff that dont have a value
            case "∞":
                return 999; // for when acc is infinite
            default:
                return Integer.parseInt(val);
        }
    }
    
    public void setLevel(int lv){
        this.level = lv;
    }
    
    public String getName(){
        return name;
    }
    
    public String getType(){
        return type;
    }
    
    public String getCategory(){
        return category;
    }
    
    public int getPower(){
        return power;
    }
    
    public int getAccuracy(){
        return accuracy;
    }
    
    public int getLevel(){
        return level;
    }
}

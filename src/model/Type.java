package model;

/**
 *
 * @author kp16
 */
public class Type{
    public final String type1; 
    public final String type2;
    
    public Type(String t1, String t2) { 
        this.type1 = t1; 
        this.type2 = t2; 
    }
    
    public String getType1(){
        return this.type1;
    }
    
    public String getType2(){
        return this.type2;
    }
}

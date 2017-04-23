package model;

/**
 *
 * @author kp16
 */
public class TypeDamage {
    public final String type; 
    public final double multiplier;
    
    public TypeDamage(String t, double m) { 
        this.type = t; 
        this.multiplier = m; 
    }
    
    public String getType(){
        return this.type;
    }
    
    public double getMultiplier(){
        return this.multiplier;
    }
}

package rsc;

import java.util.ArrayList;
import model.Move;
import model.Type;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author kp16
 */
public class Util {
    /* 
    ---- Using Serebii ----
    */
    
    public static String getNameEng(Document doc, String dexNoString){
        String name = "";
        try{        
            boolean found = false;
            for (int i = 0;i < 7;i++){   
                Elements options = doc.getElementsByAttributeValue("name","SelectURL").get(i).children();
                for (Element option : options) {
                    if (option.attr("value").toString().equals( "/pokedex-sm/" + dexNoString + ".shtml")) {
                        String stuff = option.text();
                        if (Character.toString(stuff.charAt(4)).equals(" ")) //if dex number >722
                        {
                            name = stuff.substring(5);
                        } else { 
                            name = stuff.substring(4);
                        }
                        System.out.println(name);
                        found = true;
                        break;
                    }
                    if (found) break;
                }
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        return name;
    }

    /* Supported languages 
     * French (fr)
     * German (ger)
     * Japanese Romanji (jpr)
     * Japanese Hiragana (jph)
     */
    public static String getName(Document doc, String lang){
        int g = 0; // for counting
        int row2Filter = 0;
        String name = "";
        String supportedLang[] = {"jpr","jph","fr","ger"};
        try{
            if(lang.toLowerCase().equals(supportedLang[0]) || lang.toLowerCase().equals(supportedLang[1])){
                row2Filter = 4;
            }
            if(lang.toLowerCase().equals(supportedLang[2])){
                row2Filter = 5;
            }
            if(lang.toLowerCase().equals(supportedLang[3])){
                row2Filter = 6;
            }

            Element table = doc.select("table[class=dextable]").get(0); //select the first table.
            for (Element row : table.select("tr")) {
                g++;
                Elements tds = row.select("td");
                if (g == row2Filter){
                    if (g == 4){// If Japanese is selected 
                        String txt = tds.get(1).text();
                        if (lang.toLowerCase().equals(supportedLang[0])){//jpr
                            name = txt.substring(0, txt.indexOf(" "));
                        }else{//jph
                            name = txt.substring(txt.indexOf(" ")+1);
                        }
                    }else{
                        name = tds.get(1).text(); 
                    }
                    System.out.println(name);
                    break;
                }
            }
            
        }catch(Exception e){
            name = null;
        }
        return name;
    }
    
    public static String getClassification(Document doc){
        int g = 0;      
        String classification = "";
        Element table = doc.select("table[class=dextable]").get(0); //select the first table.
        for (Element row : table.select("tr")) {
            g++;
            Elements tds = row.select("td");
            if (g == 17){
                classification = tds.get(0).text();
                break;
            }
        }
        
        return classification;
    }
    
    // in metres
    public static double getHeight(Document doc){
        int g = 0;      
        double height = 0;
        Element table = doc.select("table[class=dextable]").get(0); //select the first table.
        for (Element row : table.select("tr")) {
            g++;
            Elements tds = row.select("td");
            if (g == 17){
                String str = tds.get(1).text();
                height = Double.parseDouble(str.substring(str.indexOf(" "), str.indexOf("m")));
                break;
            }
        }
        
        return height;
    }
    
    // in kilograms
    public static double getWeight(Document doc){
        int g = 0;      
        double weight = 0;
        Element table = doc.select("table[class=dextable]").get(0); //select the first table.
        for (Element row : table.select("tr")) {
            g++;
            Elements tds = row.select("td");
            System.out.println(tds.text());
            if (g == 17){
                String str = tds.get(2).text();
                weight = Double.parseDouble(str.substring(str.indexOf(" "), str.indexOf("k")));
                break;
            }
        }
        
        return weight;
    }
    
    public static int getCaptureRate(Document doc){
        int g = 0;      
        int cr = 0;
        Element table = doc.select("table[class=dextable]").get(0); //select the first table.
        for (Element row : table.select("tr")) {
            g++;
            Elements tds = row.select("td");
            System.out.println(tds.text());
            if (g == 17){
                cr = Integer.parseInt(tds.get(3).text());
                break;
            }
        }
        
        return cr;
    }
    
    public static int getBaseEggSteps(Document doc){
        int g = 0;      
        int bes = 0;
        Element table = doc.select("table[class=dextable]").get(0); //select the first table.
        for (Element row : table.select("tr")) {
            g++;
            Elements tds = row.select("td");
            System.out.println(tds.text());
            if (g == 17){
                String str = tds.get(4).text();
                bes = Integer.parseInt(str.replace(",", ""));
                break;
            }
        }
        
        return bes;
    }
    
    public static int[] getTotalExperience(Document doc){
        int g = 0;
        int[] baseStats = new int[6];
        Element table = doc.select("table[class=dex-table]").get(3); //select the first table.
        for (Element row : table.select("tr")) {
            Elements tds = row.select("td");
            if (g == 7) break;
            if (g >= 1){
                System.out.println(tds.get(0).text());
                baseStats[g - 1] = Integer.parseInt(tds.get(0).text());
            }
            g++;
        }
        
        return baseStats;
    }
    
    // show the damage taken of attacks from each type
    public static void getTypeDamageTaken(Document doc){
        int g = 0;      
        Element table = doc.select("table[class=dextable]").get(2); //select the first table.
        for (Element row : table.select("tr")) {
            Elements tds = row.select("td");
            if(g == 1){
                for (Element type : tds){
                    String str = type.select("img").attr("src");
                    System.out.println(str.substring(str.lastIndexOf("/")+1, str.indexOf("2")));
                }  
            }if(g == 2){
                for (Element effect : tds){
                    System.out.println(effect.text());
                }
                System.out.println(tds.get(17).text());
            }
            System.out.println(" ");
            g++;
        }
    }
    
    /* 
    ---- Using pdb ---- 
    */
    public static int[] getBaseStats(Document doc){
        int g = 0;
        int[] baseStats = new int[6];
        Element table = doc.select("table[class=vitals-table]").get(3); //select the first table.
        for (Element row : table.select("tr")) {
            Elements tds = row.select("td");
            if (g == 7) break;
            if (g >= 1){
                baseStats[g - 1] = Integer.parseInt(tds.get(0).text());
            }
            g++;
        }
        
        return baseStats;
    }
    
    public static Type getType(Document doc){
        int g = 0;
        Type t = null;
        Element table = doc.select("table[class=vitals-table]").get(0); //select the first table.
        for (Element row : table.select("tr")) {
            Elements tds = row.select("td");
            if (g == 1){
                int j = 0;
                String types[] = {"none","none"};
                for(Element s: tds.select("a")){
                    types[j] = s.text();
                    if (tds.select("a").size() == 1){
                       break;
                    }
                    j++;
                }
                t = new Type(types[0], types[1]);
                break;
            }
            g++;
        }
        return t;
    }
    
    // level up
    public static ArrayList<Move> getMoves1(Document doc){
        ArrayList<Move> moves = new ArrayList<>();
        Element table = doc.select("table[class=data-table wide-table]").get(0); //select the first table.
        int f = 0; //counter
        for (Element row : table.select("tr")) {
            if(f>=1){
                Move move = new Move(
                    row.select("td").get(1).text(),
                    row.select("td").get(2).text(),
                    row.select("td").select("img").attr("title"),
                    row.select("td").get(4).text(),
                    row.select("td").get(5).text());
                int lv = Integer.parseInt(row.select("td").get(0).text());
                move.setLevel(lv);
                moves.add(move);
            }
            f++;
        }
        return moves;
    }
    
    // egg moves
    public static ArrayList<Move> getMoves2(Document doc){
        ArrayList<Move> moves = new ArrayList<>();
        Element table = doc.select("table[class=data-table wide-table]").get(1); //select the first table.
        int f = 0; // counter
        for (Element row : table.select("tr")) {
            if(f>=1){
                Move move = new Move(
                    row.select("td").get(0).text(),
                    row.select("td").get(1).text(),
                    row.select("td").select("img").attr("title"),
                    row.select("td").get(3).text(),
                    row.select("td").get(4).text());
                moves.add(move);
            }
            f++;
        }
        
        return moves;
    }
}

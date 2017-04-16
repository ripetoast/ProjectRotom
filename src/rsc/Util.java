package rsc;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author kp16
 */
public class Util {
    public static String getNameEng(Document doc, String dexNoString){
        String name = "";
        boolean found = false;
        int j = 0;
        for (int i = 0;i < 7;i++){   
            Elements options = doc.getElementsByAttributeValue("name","SelectURL").get(i).children();
            for (Element option : options) {
                System.out.println(j);
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
            j++;
            }
        }

        return name;      
    }
    
    // if r is true, romanji is returned
    public static String getNameJpn(Document doc, boolean r){
        int g = 0;
        String jpnName = "";
        Element table = doc.select("table[class=dextable]").get(0); //select the first table.
        for (Element row : table.select("tr")) {
            g++;
            Elements tds = row.select("td");
            String txt = tds.get(1).text();
            if (g == 4){
                if (r){
                    txt = txt.substring(0, txt.indexOf(" "));
                    jpnName = txt;
                }else{
                    txt = txt.substring(txt.indexOf(" ")); 
                    jpnName = txt;
                }
                System.out.println(jpnName);
                break;
            }
        }
        
        return jpnName;     
    }
    
    public static String getNameFr(Document doc){
        int g = 0;
        String frName = "";
        Element table = doc.select("table[class=dextable]").get(0); //select the first table.
        for (Element row : table.select("tr")) {
            g++;
            Elements tds = row.select("td");
            System.out.println(tds.get(1).text());
            if (g == 5){
                frName = tds.get(1).text();        
                break;
            }
        }
        
        return frName;     
    }
    
        public static String getNameGer(Document doc){
        int g = 0;
        String gerName = "";
        Element table = doc.select("table[class=dextable]").get(0); //select the first table.
        for (Element row : table.select("tr")) {
            g++;
            Elements tds = row.select("td");
            System.out.println(tds.get(1).text());
            if (g == 6){
                gerName = tds.get(1).text();        
                break;
            }
        }
        
        return gerName;     
    }
    
    public static String getClassification(Document doc){
        int g = 0;      
        String classifaction = "";
        Element table = doc.select("table[class=dextable]").get(0); //select the first table.
        for (Element row : table.select("tr")) {
            g++;
            Elements tds = row.select("td");
            if (g == 17){
                classifaction = tds.get(0).text();
                break;
            }
        }
        
        return classifaction;
    }
    
}

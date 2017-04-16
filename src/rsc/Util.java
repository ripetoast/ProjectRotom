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

        return name;      
    }

    /* Supported languages 
     * French (fr)
     * German (ger)
     * Japanese Romanji (jpr)
     * Japanese hiragana (jph)
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
                    if (g == 4){// If Japanse is selected 
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
    
    public static String getHeight(Document doc){
        int g = 0;      
        String height = "";
        Element table = doc.select("table[class=dextable]").get(0); //select the first table.
        for (Element row : table.select("tr")) {
            g++;
            Elements tds = row.select("td");
            if (g == 17){
                height = tds.get(0).text();
                break;
            }
        }
        
        return height;
    }
    
}

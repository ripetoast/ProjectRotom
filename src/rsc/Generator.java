package rsc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author kp16
 */
public class Generator {
    public static void test(){
       /* reads html locally by saving the webpage into \nbproject\private\
        * there's no need to save this page as webpage complete.
        * put the path to this webpage in a txt file called config.txt
        */

        String current = System.getProperty("user.dir");
        Document doc;
        try {
            Scanner x = new Scanner(new File(current+"\\nbproject\\private\\config.txt"));
            String path2html = x.next();
            x.close();
            File input = new File(path2html);
            doc = Jsoup.parse(input, "UTF-8");
            
            System.out.println(Util.getBaseEggSteps(doc));
        } catch (IOException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

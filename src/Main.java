import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import rsc.Generator;
import rsc.Util;

/**
 *
 * @author kp16
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            
            Util.getType(doc);
            //System.out.println();
        } catch (IOException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

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
       /* code below will read html saved locally, saving your webpages into \nbproject\private\
        * there's no need to save this page as webpage complete in the save dialog of your browser.
        * put the path to your local webpage in a txt file called config.txt
        * This code will only read 1 file at a time
        */

        String current = System.getProperty("user.dir");
        Document doc;
        try {
            Scanner x = new Scanner(new File(current+"\\nbproject\\private\\config.txt"));
            String path2html = x.next();
            x.close();
            File input = new File(path2html);
            doc = Jsoup.parse(input, "UTF-8");
            
        } catch (IOException ex) {
            Logger.getLogger(Generator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

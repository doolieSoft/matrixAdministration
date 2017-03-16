
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package table_des_matieres;

import table_des_matieres.view.*;
//~--- JDK imports ------------------------------------------------------------

import java.awt.SplashScreen;

/**
 *
 * @author fabrice
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        SplashScreen splash = SplashScreen.getSplashScreen();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {

            // ignore it
        }

        Matrix_View app;

        app = new Matrix_View();
        app.setVisible(true);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

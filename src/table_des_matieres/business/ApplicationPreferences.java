
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package table_des_matieres.business;

//~--- JDK imports ------------------------------------------------------------

import java.io.*;

/**
 *
 * @author fabrice
 */
public class ApplicationPreferences implements Serializable {
    private String backgroundApplicationFrameColor;
    private int    lastAddDialogXPosition;
    private int    lastAddDialogYPosition;
    private int    lastApplicationFrameXPosition;
    private int    lastApplicationFrameYPosition;
    private String listPrelevementPanelColor;
    private String preferredSoftware;
    private String searchPanelColor;

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {}

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {}

    private void readObjectNoData() throws ObjectStreamException {}
}


//~ Formatted by Jindent --- http://www.jindent.com

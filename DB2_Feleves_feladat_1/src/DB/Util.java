package DB;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Util {

	public static void Alert(String message) {
		JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void Success(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Util.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	
}

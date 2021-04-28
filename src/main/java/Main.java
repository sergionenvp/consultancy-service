import javax.swing.*;
import java.awt.*;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
public class Main {
    public static void main(String[] args) {
        UIManager UI=new UIManager();
        JFrame frame = new JFrame("Consultancy Service App");
        JPanel panel = new JPanel();
        SuperMenu superMenu = new SuperMenu();
        superMenu.Menu();
    }
}

package MediatorPattern;
import javax.swing.*;
public interface Mediator {
    public void changed(JFrame frame);
    public void changed(JLabel label);
    public void changed(JPanel panel);
    public void changed(JButton button);
    public JPanel getPanel();
    public JLabel getLabel();
    public JButton getButton();
    public JFrame getFrame();
}

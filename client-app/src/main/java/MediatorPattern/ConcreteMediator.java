package MediatorPattern;

import javax.swing.*;

public class ConcreteMediator implements  Mediator{
    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JButton button = new JButton();
    public void changed(JPanel panel) { this.panel=panel; }
    public  void changed(JLabel label)
    {
        this.label=label;
    }
    public  void changed(JButton button)
    {
        this.button=button;
    }
    public void changed(JFrame frame)
    {
        this.frame = frame;
    }
    public JPanel getPanel()
    {
       return  panel;
    }
    public JLabel getLabel()
    {
        return label;
    }
    public JButton getButton()
    {
         return button;
    }
    public JFrame getFrame()
    {
        return frame;
    }
}

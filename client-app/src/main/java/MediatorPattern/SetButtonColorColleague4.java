package MediatorPattern;

import javax.swing.*;
import java.awt.*;

public class SetButtonColorColleague4 implements  MainColleague {
    ConcreteMediator mediator;
    Color color;
    public SetButtonColorColleague4(ConcreteMediator mediator,Color color)
    {
        this.mediator = mediator;
        this.color = color;

    }
    public void changed()
    {
       //get button to change color
        JButton button = mediator.getButton();
       //update color
        button.setBackground(color);
        // update button
        mediator.changed(button);
    }
}

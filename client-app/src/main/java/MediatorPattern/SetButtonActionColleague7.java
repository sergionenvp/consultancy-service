package MediatorPattern;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SetButtonActionColleague7 implements  MainColleague {
    ConcreteMediator mediator;
    ActionListener e;
    public SetButtonActionColleague7(ConcreteMediator mediator, ActionListener e)
    {
        this.mediator = mediator;
        this.e=e;

    }
    public void changed()
    {
        //get button to change color
        JButton button = mediator.getButton();
        //update color
        button.addActionListener(e);
        // update button
        mediator.changed(button);
    }
}

package MediatorPattern;

import javax.swing.*;

public class SetButtonTextColleague6 implements  MainColleague {
    ConcreteMediator mediator;
    String text;
    public SetButtonTextColleague6(ConcreteMediator mediator, String text)
    {
        this.mediator = mediator;
        this.text = text;

    }
    public void changed()
    {
        //get button to change color
        JButton button = mediator.getButton();
        //update color
        button.setText(text);
        // update button
        mediator.changed(button);
    }
}

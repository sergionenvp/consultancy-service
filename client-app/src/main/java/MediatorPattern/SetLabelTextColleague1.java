package MediatorPattern;

import javax.swing.*;

public class SetLabelTextColleague1 implements MainColleague {
    private  String text;
    ConcreteMediator mediator;
    public SetLabelTextColleague1(ConcreteMediator mediator, String text)
    {
        this.mediator = mediator;
        this.text = text;
    }
    public void changed()
        {
        //get hold of current label
        JLabel label = mediator.getLabel();
        //change font
        label.setText(text);
        //update changes
        mediator.changed(label);
    }
}
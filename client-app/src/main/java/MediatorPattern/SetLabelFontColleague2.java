package MediatorPattern;

import javax.swing.*;
import java.awt.*;

public class SetLabelFontColleague2 implements MainColleague {
    // text to be added.
    private  Font font;
    //

    ConcreteMediator mediator;
    public SetLabelFontColleague2(ConcreteMediator mediator, Font font)
    {
        this.mediator = mediator;
        this.font = font;
    }


    public void changed() {
        //get hold of current label
       JLabel label = mediator.getLabel();
       //change font
       label.setFont(font);
       //update changes
       mediator.changed(label);
    }
}
package MediatorPattern;

import javax.swing.*;
import java.awt.*;

public class SetButtonFontColleague5 implements  MainColleague {
    ConcreteMediator mediator;
    Font font;
    public SetButtonFontColleague5(ConcreteMediator mediator, Font font)
    {
        this.mediator = mediator;
        this.font = font;

    }
    public void changed()
    {
        //get button to change font
        JButton button = mediator.getButton();
        //update font
        button.setFont(font);
        // update button
        mediator.changed(button);
    }
}

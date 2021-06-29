package MediatorPattern;

import javax.swing.*;

public class SetFrameTitleColleague11 implements MainColleague
{
    String title;
    ConcreteMediator mediator;
    public SetFrameTitleColleague11(ConcreteMediator mediator, String title)
    {
        this.mediator = mediator;
        this.title = title;
    }
    public void changed()
    {
        JFrame frame = mediator.getFrame();
        frame.setTitle(title);
        mediator.changed(frame);

    }
}

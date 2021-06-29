package MediatorPattern;

import javax.swing.*;

public class AddPanelToFrameColleague9 implements  MainColleague
{
    ConcreteMediator mediator;
    public AddPanelToFrameColleague9(ConcreteMediator mediator)
    {
        this.mediator=mediator;

    }
    public void changed()
    {
        JFrame frame = mediator.getFrame();
        frame.add(mediator.getPanel());
        mediator.changed(frame);
    }

//frame set size

}

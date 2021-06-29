package MediatorPattern;

import javax.swing.*;

public class AddLabelToPanelColleague3 implements MainColleague {
    ConcreteMediator mediator;
    public AddLabelToPanelColleague3(ConcreteMediator mediator)
    {
        this.mediator=mediator;
    }
    public void changed() {
        JLabel label = mediator.getLabel();
        JPanel panel = mediator.getPanel();
            panel.add(label);
        mediator.changed(panel);
    }
}

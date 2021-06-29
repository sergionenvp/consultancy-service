package MediatorPattern;

import javax.swing.*;

public class AddButtonToPanelColleague8 implements MainColleague {
    ConcreteMediator mediator;
    public AddButtonToPanelColleague8(ConcreteMediator mediator)
    {
        this.mediator=mediator;
    }
    public void changed() {
        JButton button = mediator.getButton();
        //creates a new clone
        JButton button1 = new JButton();
        button1.setText(button.getText());

        button1.addActionListener(button.getActionListeners()[0]);
        button1.setFont(button.getFont());
//        button1.setBackground(button.getBackground());
//        button1.setAction(button.getAction());

        JPanel panel = mediator.getPanel();
        panel.add(button1);
        //update panel with the label
        mediator.changed(panel);

    }



}

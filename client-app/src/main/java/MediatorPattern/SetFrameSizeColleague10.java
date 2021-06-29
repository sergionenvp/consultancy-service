package MediatorPattern;
import javax.swing.*;
public class SetFrameSizeColleague10 implements MainColleague
{
    int width = 0;
    int height = 0;
    ConcreteMediator mediator;
    public SetFrameSizeColleague10(ConcreteMediator mediator, int width,int height)
    {
        this.mediator = mediator;
        this.width = width;
        this.height = height;
    }
    public void changed()
    {
        JFrame frame = mediator.getFrame();
        frame.setSize(width,height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mediator.changed(frame);

    }
}

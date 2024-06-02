
import java.awt.*;

import javax.swing.*;

public class Screen extends JFrame {

    public void initialize() {
        JPanel mvc = new JPanel();

        mvc.setLayout(new BorderLayout());

        setTitle("Restaurante a la classe");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

}

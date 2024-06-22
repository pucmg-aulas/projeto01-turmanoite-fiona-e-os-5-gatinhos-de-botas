import java.awt.*;

import javax.swing.*;

public class ScreenSairMesa extends JFrame implements MesasDAO{
    
    
    public void iniciarTela() {

        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout());

        setTitle("Fazer Requisição");
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Escolha a mesa que voce vai sair:"));
    }
}
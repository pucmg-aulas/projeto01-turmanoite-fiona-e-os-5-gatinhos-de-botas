import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;

public class ScreenFzrRequi extends JFrame {
    private JTextField nameField = new JTextField(20);
    private JTextField numberOfGuestsField = new JTextField(5);
    private JButton submitButton = new JButton("Submit");

    public void iniciarTela() {

        JPanel painel = new JPanel();

        painel.setLayout(new BorderLayout());

        setTitle("Fazer Requisição");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Nome:"));
        panel.add(nameField);
        panel.add(new JLabel("Número de convidados:"));
        panel.add(numberOfGuestsField);
        panel.add(submitButton);

        submitButton.addActionListener(e ->{
            fazerRequisicao();
            JOptionPane.showMessageDialog(null, "Requisição feita com sucesso!");
            nameField.setText("");
            numberOfGuestsField.setText("");
        });

        add(panel);
        setVisible(true);
    }
    public void fazerRequisicao(){
        String nome = this.getName();
        int numConvidados = this.getNumberOfGuests();
        Cliente cliente = new Cliente(nome, numConvidados);
        Requisicao requisicao = new Requisicao(cliente);
        System.out.println(requisicao.getConvidados());
     }
 
     public String getName() {
         return nameField.getText();
     }
 
     public int getNumberOfGuests() {
         try {
             return Integer.parseInt(numberOfGuestsField.getText());
         } catch (NumberFormatException e) {
             return 0;
         }
     }
 
     public void addSubmitListener(ActionListener listener) {
         submitButton.addActionListener(listener);
     }
 
     public void displayMessage(String message) {
         JOptionPane.showMessageDialog(this, message);
     }
}
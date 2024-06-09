import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;

public class ScreenFzrRequi extends JFrame {
    RequisicaoController controller;
    
    // gambiarra
    Restaurante r = new Restaurante();
    Mesa m;

    private JTextField nameField = new JTextField(20);
    private JTextField numberOfGuestsField = new JTextField(5);
    private JButton submitButton = new JButton("Submit");

    public void iniciarTela() {

        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout());

        setTitle("Fazer Requisição");
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Nome:"));
        panel.add(nameField);
        panel.add(new JLabel("Número de convidados:"));
        panel.add(numberOfGuestsField);
        panel.add(submitButton);

        submitButton.addActionListener(e -> {
            Requisicao requisicao = fazerRequisicao();
            nameField.setText("");
            numberOfGuestsField.setText("");
            Mesa m = r.encontrarMesa(requisicao);
            showSuccessDialog(frame, m);
        });


        add(panel);
        setVisible(true);
    }

    public Requisicao fazerRequisicao() {
        String nome = this.getName();
        int numConvidados = this.getNumberOfGuests();
        Cliente cliente = new Cliente(nome, numConvidados);
        Requisicao requisicao = new Requisicao(cliente);
        System.out.println(requisicao.getConvidados());
        return requisicao;
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

    private static void showSuccessDialog(JFrame parent, Mesa mesa) {
        JDialog dialog = new JDialog(parent, "Sucesso", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(550, 100);

        JLabel messageLabel = new JLabel("Requisição feita com sucesso! Por favor, direcione o  cliente à mesa: " + mesa.getIdMesa() , SwingConstants.CENTER);
        JButton okButton = new JButton("OK");

        okButton.addActionListener(e -> dialog.dispose());

        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        dialog.add(messageLabel);
        dialog.add(okButton);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }


}
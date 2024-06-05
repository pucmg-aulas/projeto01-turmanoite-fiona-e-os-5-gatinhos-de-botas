import java.awt.*;

import javax.swing.*;

public class Screen extends JFrame {
    Restaurante restaurante = new Restaurante();

    public Restaurante getRestaurante() {
        return this.restaurante;
    }

    public void initialize() {
        JPanel mvc = new JPanel();

        mvc.setLayout(new BorderLayout());

        setTitle("Restaurante a la classe");
        setSize(500, 500);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);

        JLabel title = new JLabel("Fiona e os 5 gatinhos de botas!");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(30, 10, 300, 30);
        add(title);

        JButton fazerRequisicao = new JButton("Fazer requisição");
        fazerRequisicao.setBounds(30, 50, 200, 30);
        fazerRequisicao.setBackground(new Color(173, 216, 230));
        fazerRequisicao.addActionListener(e -> {
            ScreenFzrRequi screenFzrRequi = new ScreenFzrRequi();
            screenFzrRequi.iniciarTela();
        });
        add(fazerRequisicao);

        JButton sairDaMesa = new JButton("Sair da mesa");
        sairDaMesa.setBounds(30, 90, 200, 30);
        sairDaMesa.setBackground(new Color(173, 216, 230));
        sairDaMesa.addActionListener(e -> {
            restaurante.sairDaMesa();
        });
        add(sairDaMesa);

        JButton consultarPedido = new JButton("Consultar pedido");
        consultarPedido.setBounds(30, 130, 200, 30);
        consultarPedido.setBackground(new Color(173, 216, 230));
        consultarPedido.addActionListener(e -> {
            restaurante.consultarPedido();
        });
        add(consultarPedido);

        JButton consultarFilaDeEspera = new JButton("Consultar fila de espera");
        consultarFilaDeEspera.setBounds(30, 170, 200, 30);
        consultarFilaDeEspera.setBackground(new Color(173, 216, 230));
        add(consultarFilaDeEspera);

        JButton cancelarRequisicaoEmEspera = new JButton("Cancelar requisição em espera");
        cancelarRequisicaoEmEspera.setBounds(30, 210, 200, 30);
        cancelarRequisicaoEmEspera.setBackground(new Color(173, 216, 230));
        add(cancelarRequisicaoEmEspera);

        JButton realizarPedido = new JButton("Realizar um pedido");
        realizarPedido.setBounds(30, 250, 200, 30);
        realizarPedido.setBackground(new Color(173, 216, 230));
        add(realizarPedido);

        JButton finalizaPedido = new JButton("Finalizar um pedido");
        finalizaPedido.setBounds(30, 290, 200, 30);
        finalizaPedido.setBackground(new Color(173, 216, 230));
        add(finalizaPedido);

        add(mvc);
        setVisible(true); 

    }

}

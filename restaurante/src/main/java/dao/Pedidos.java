package dao;

import model.Pedido;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pedidos extends AbstractDAO implements Serializable {

    private List<Pedido> pedidos;
    private final String path = "./src/main/java/data/Pedidos.dat";
    private static Pedidos instancia;

    private Pedidos() {
        this.pedidos = new ArrayList<>();
        this.carrega(); // Carrega os pedidos salvos anteriormente
    }

    public static Pedidos getInstancia() {
        if (instancia == null) {
            instancia = new Pedidos();
        }
        return instancia;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void adicionar(Pedido pedido) {
        if (pedido != null) {
            pedidos.add(pedido);
            this.grava(); // Salva o pedido adicionado
        } else {
            throw new IllegalArgumentException("O pedido não pode ser nulo.");
        }
    }

    public void grava() {
        super.grava(path, pedidos);
    }

    private void carrega() {
        this.pedidos = super.leitura(path);
        if (this.pedidos == null) {
            this.pedidos = new ArrayList<>(); // Se não houver dados carregados, inicializa uma lista vazia
        }
    }

    public void removerPedido(Pedido pedido) {
        pedidos.remove(pedido);
        this.grava(); // Salva após remover
    }

    public Pedido obter(int idPedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getIdPedido() == idPedido) {
                return pedido;
            }
        }
        return null;
    }

    public List<Pedido> listar() {
        return new ArrayList<>(pedidos);
    }

    @Override
    public String toString() {
        return "Pedidos{" +
                "pedidos=" + pedidos +
                '}';
    }
}

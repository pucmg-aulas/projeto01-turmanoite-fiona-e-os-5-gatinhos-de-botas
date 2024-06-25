package model;

import dao.IdAutoMesas;
import dao.Requisicoes;
import exception.MesaNaoAlocadaException;
import java.io.Serializable;

public class Mesa implements Serializable {

    private int idMesa; // ID único e autoincremental da mesa
    private int capacidade; // Capacidade da mesa (número de pessoas)
    private boolean status; // Status da mesa (disponível ou ocupada)

    // Construtor que utiliza o IdAutoMesas para definir o ID único da mesa
    public Mesa(int capacidade) {
        this.idMesa = IdAutoMesas.getInstancia().getProximoId(); // Obtém o próximo ID disponível
        this.capacidade = capacidade;
        this.status = true; // A mesa começa como disponível por padrão
    }

    // Getters e Setters
    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Método para encontrar uma requisição ativa associada à mesa
    public Requisicao reqDaMesa() throws MesaNaoAlocadaException {
        Requisicoes requisicoes = Requisicoes.getInstancia();

        // Procura por uma requisição ativa associada a esta mesa
        Requisicao requisicao = requisicoes.listar().stream()
                .filter(req -> req.getStatus() && req.getMesa().getIdMesa() == this.getIdMesa())
                .findFirst()
                .orElse(null);

        // Verifica se foi encontrada uma requisição ativa
        if (requisicao == null) {
            throw new MesaNaoAlocadaException("Não existe uma requisição ativa para esta mesa.");
        }

        // Verifica se a requisição ativa possui um pedido associado
        if (requisicao.getPedido() == null) {
            throw new MesaNaoAlocadaException("A requisição ativa para esta mesa não possui um pedido associado.");
        }

        return requisicao; // Retorna a requisição encontrada
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "idMesa=" + idMesa +
                ", capacidade=" + capacidade +
                ", status=" + status +
                '}';
    }
}

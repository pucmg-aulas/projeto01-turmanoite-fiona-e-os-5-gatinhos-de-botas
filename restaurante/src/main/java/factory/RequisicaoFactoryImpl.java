package factory;

import model.Cliente;
import model.Requisicao;

public class RequisicaoFactoryImpl implements RequisicaoFactory {

    @Override
    public Requisicao criarRequisicao(String nomeCliente, int qtdPessoas) {
        Cliente cliente = new Cliente(nomeCliente, qtdPessoas);
        return new Requisicao(cliente);
    }
}

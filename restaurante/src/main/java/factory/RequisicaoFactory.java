package factory;

import model.Requisicao;

public interface RequisicaoFactory {
    Requisicao criarRequisicao(String nomeCliente, int qtdPessoas);
}


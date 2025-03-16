/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Produto;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class Cardapio extends AbstractDAO{

    private List<Produto> produtos;
    
    private final String path = "./src/main/java/data/Cardapio.dat";

    // Instância única da classe Mesas
    private static Cardapio instancia;

    // Construtor privado para evitar instanciamento externo
    private Cardapio() {
        this.produtos = new ArrayList<>();
        carregaCardapio();
    }

    public void iniciaCardapio() {
        produtos.add(new Produto("Moqueca de Tilápia", 25.0));
        produtos.add(new Produto("Falafel Assado", 15.0));
        produtos.add(new Produto("Salada Primavera com Macarrão Konjac", 18.0));
        produtos.add(new Produto("Escondidinho de Frango", 20.0));
        produtos.add(new Produto("Strogonoff", 22.0));
        produtos.add(new Produto("Caçarola de carne com legumes", 23.0));
        produtos.add(new Produto("Água", 3.0));
        produtos.add(new Produto("Suco", 5.0));
        produtos.add(new Produto("Refrigerante", 4.0));
        produtos.add(new Produto("Cerveja", 6.0));
        produtos.add(new Produto("Taça de vinho", 8.0));
        grava();
    }
    
    public List getProdutos(){
        return produtos;
    }
    
    // Método para obter a instância única da classe
    public static Cardapio getInstancia() {
        if (instancia == null) {
            instancia = new Cardapio();

        }
        return instancia;
    }

    public void adicionar(Produto produto) {
        if (produto != null) {
            produtos.add(produto);
            grava();
        } else {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }
    }

    public void removerProduto(Produto p) {
        produtos.remove(p);
        grava();
    }

    public Produto obter(int idProduto) {
        for (Produto produto : produtos) {
            if (produto.getIdProduto() == idProduto) {
                return produto;
            }
        }
        return null;
    }

    public List<Produto> listar() {
        return new ArrayList<>(produtos);
    }
    
    private void grava(){
        super.grava(path, produtos);
    }

    @Override
    public String toString() {
        return "Cardapio{"
                + "produtos=" + produtos
                + '}';
    }

    private void carregaCardapio() {
        this.produtos = super.leitura(path);
    }
}

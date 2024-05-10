import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class Restaurante {
    static ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
    static ArrayList<Requisicao> filaDeEspera = new ArrayList<Requisicao>();
    static ArrayList<Mesa> mesas = new ArrayList<Mesa>();
    static ArrayList<Produto> cardapio = new ArrayList<Produto>();
    LocalTime entrada;
    LocalTime saida;

    // CONSTRUTORES
    static {
        Mesa m1 = new Mesa(4, 1);
        Mesa m2 = new Mesa(4, 2);
        Mesa m3 = new Mesa(4, 3);
        Mesa m4 = new Mesa(4, 4);
        Mesa m5 = new Mesa(6, 5);
        Mesa m6 = new Mesa(6, 6);
        Mesa m7 = new Mesa(6, 7);
        Mesa m8 = new Mesa(6, 8);
        Mesa m9 = new Mesa(8, 9);
        Mesa m10 = new Mesa(8, 10);

        mesas.add(m1);
        mesas.add(m2);
        mesas.add(m3);
        mesas.add(m4);
        mesas.add(m5);
        mesas.add(m6);
        mesas.add(m7);
        mesas.add(m8);
        mesas.add(m9);
        mesas.add(m10);

    }

    static { // CARDAPIO
        cardapio.add(new Produto("Moqueca de Tilápia", 25.0));
        cardapio.add(new Produto("Falafel Assado", 15.0));
        cardapio.add(new Produto("Salada Primavera com Macarrão Konjac", 18.0));
        cardapio.add(new Produto("Escondidinho de Frango", 20.0));
        cardapio.add(new Produto("Strogonoff", 22.0));
        cardapio.add(new Produto("Caçarola de carne com legumes", 23.0));
        cardapio.add(new Produto("Água", 3.0));
        cardapio.add(new Produto("Suco", 5.0));
        cardapio.add(new Produto("Refrigerante", 4.0));
        cardapio.add(new Produto("Cerveja", 6.0));
        cardapio.add(new Produto("Taça de vinho", 8.0));

    }

    // ENCONTRAR MESA
    public Mesa encontrarMesa(Requisicao r) {
        int convidados = r.getConvidados();
        for (Mesa mesa : mesas) {
            if (convidados <= mesa.getCapacidade() && mesa.getStatus() == false) {
                mesa.ocuparMesa();
                return mesa;
            } else if (convidados > 8) {
                return null;
            }
        }

        return null;
    }

    // FAZER REQUISICAO
    public void fazerRequisicao() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        System.out.println("Digite a quantidade de pessoas no total: ");
        int qntPessoas = scanner.nextInt();

        Cliente cliente = new Cliente(nomeCliente, qntPessoas);

        Requisicao r = new Requisicao(cliente);
        this.entrada = LocalTime.now();
        System.out.println("Requisicao feita!");
        requisicoes.add(r);
        this.imprimeRequisicao(r);

        r.setMesa(encontrarMesa(r));
        if (r.getMesa() == null && r.getConvidados() > 8) {
            System.out.println("Porém, não temos mesa para mais de 8 pessoas, desculpe.");
        } else if (r.getMesa() == null) {
            colocaFilaDeEspera(r);
        }

    }

    // FILA DE ESPERA
    public void colocaFilaDeEspera(Requisicao requisicao) {
        filaDeEspera.add(requisicao);
        System.out.println(
                "Nao tem mesas disponiveis, você entrou na fila de espera, sua posição é a: " + filaDeEspera.size());
    }

    public void tirarFilaDeEspera(Requisicao requisicao) {
        filaDeEspera.remove(requisicao);
    }

    // SAIR DA MESA

    public void sairDaMesa() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o número da mesa que deseja sair: ");
        int numero = scanner.nextInt();
        boolean mesaEncontrada = false;

        for (Mesa mesa : mesas) {
            if (mesa.getnumero() == numero) {
                mesa.desocuparMesa();
                this.saida = LocalTime.now();
                LocalTime diftime = this.saida.minusNanos(this.entrada.toNanoOfDay());
                System.out.println("Tempo de permanência: " + diftime);
                this.verificarFila(mesa);
                mesaEncontrada = true;
                break;
            }
        }
        if (!mesaEncontrada) {
            System.out.println("Mesa não encontrada.");
        }
    }

    // VERIFICAR FILA
    public void verificarFila(Mesa m) {
        Iterator<Requisicao> iterator = filaDeEspera.iterator();
        while (iterator.hasNext()) {
            Requisicao r = iterator.next();
            if (m.getCapacidade() >= r.getConvidados() && !m.getStatus()) {
                m.ocuparMesa();
                iterator.remove(); // Remover de forma segura durante a iteração
                System.out.println("Mesa ocupada pela fila de espera, Cliente: " + r.getCliente().getNome());
            }
        }
    }

    // MOSTRA ESTATISTICAS DA FILA
    public void statsFila() {
        if (filaDeEspera.isEmpty()) {
            System.out.println("A fila de espera está vazia!");
            return;
        }
        System.out.println("A fila de espera possui " + filaDeEspera.size() + " clientes: ");
        int posição = 1;
        for (Requisicao r : filaDeEspera) {
            System.out.println(posição + "." + r.getCliente().getNome() + "(Id-" + r.getIdRequisicao() + ")");
            posição++;
        }

    }

    // TIRA UMA REQUISIÇÃO DA FILA DE ESPERA
    public void cancelarRequisicao() {
        if (filaDeEspera.isEmpty()) {
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o identificador da requisição que deseja cancelar: ");
        int idCancela;
        boolean cancelado = false;
        if (scanner.hasNextInt()) {
            idCancela = scanner.nextInt();
            System.out.println("Verificando...\n ");
            Iterator<Requisicao> iterator = filaDeEspera.iterator();
            while (iterator.hasNext()) {
                Requisicao r = iterator.next();
                if (r.getIdRequisicao() == idCancela) {
                    iterator.remove();
                    cancelado = true;
                    System.out.println("Requisição de id " + r.getIdRequisicao() + " cancelada");
                    break;
                }

            }
            if (!cancelado) {
                System.out.println("Requisição de id " + idCancela + " não está na fila de esspera");
            }
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }

    }

    public void imprimeRequisicao(Requisicao r) {
        System.out.println("Informações da requisição:\n");
        System.out.println("Requisição no nome de : " + r.getCliente().getNome());
        System.out.println("Identificador da requisição: " + r.getIdRequisicao() + "\n");
    }

    public void imprimeCardapio() {
        System.out.println("---------");
        System.out.println("Cardápio:");
        for (Produto produto : cardapio) {
            System.out.println(produto.getIdProduto() + "-" + produto.getNome() + " - R$" + produto.getPreço());
        }
        System.out.println("---------");
    }
    /*
     * 
     * public void fazerPedido() {
     * Scanner scanner = new Scanner(System.in);
     * System.out.
     * println("Informe o identificador da requisição que deseja realizar um pedido: "
     * );
     * int idReq;
     * int idProd;
     * Produto produtoSelecionado = null;
     * if (scanner.hasNextInt()) {
     * idReq = scanner.nextInt();
     * System.out.println("Verificando...\n ");
     * System.out.println("requisição de id " + idReq + " selecionada ");
     * System.out.println("Informe o id do prato/produto que deseja pedir ");
     * imprimeCardapio();
     * if (scanner.hasNextInt() && scanner.nextInt() < 1 || scanner.nextInt() >
     * cardapio.size()) {
     * idProd = scanner.nextInt();
     * System.out.println("Verificando...\n ");
     * for (Produto produto : cardapio) {
     * if (produto.getIdProduto() == idProd) {
     * produtoSelecionado = produto;
     * break;
     * }
     * }
     * 
     * if (produtoSelecionado == null) {
     * System.out.println("Produto não encontrado.");
     * return;
     * }
     * System.out.println(produtoSelecionado.getNome() + " Selecionado(a)");
     * 
     * } else {
     * System.out.println("Opção inválida. Tente novamente.");
     * }
     * 
     * } else {
     * System.out.println("Opção inválida. Tente novamente.");
     * 
     * }
     * 
     * }
     */

    public void fazerPedido() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o identificador da requisição que deseja realizar um pedido: ");
        int idReq;
        int idProd;

        if (scanner.hasNextInt()) {
            idReq = scanner.nextInt();
            if (posReq(idReq) != -1) {
                System.out.println("Verificando...\n ");
                System.out.println("requisição de id " + idReq + " selecionada ");
                System.out.println("Informe o id do prato/produto que deseja pedir ");
                imprimeCardapio();
                if (scanner.hasNextInt()) {
                    idProd = scanner.nextInt();
                    if (posProd(idProd) != -1) {
                        System.out.println("Verificando...\n ");
                        System.out.println("produto de id " + idProd + " selecionado: ");

                        imprimeProd(idProd);
                        requisicoes.get(posReq(idReq)).getPedido().addProduto(cardapio.get(posProd(idProd)));
                        requisicoes.get(posReq(idReq)).getPedido().imprimePedido();

                    } else {
                        System.out.println("produto não encontrado");
                    }

                } else {
                    System.out.println("requisição não encontrada");
                }

            } else {
                System.out.println("Requisição não encontrada");
            }

        } else {
            System.out.println("Opção inválida. Tente novamente.");

        }
    }

    public int posReq(int id) {
        for (Requisicao r : requisicoes) {

            if (id == r.getIdRequisicao()) {
                return (requisicoes.indexOf(r));
            }
        }
        return (-1);
    }

    public int posProd(int id) {
        for (Produto p : cardapio) {
            if (id == p.getIdProduto()) {
                return (cardapio.indexOf(p));
            }
        }
        return (-1);
    }

    public void imprimeProd(int id) {

        System.out.println(cardapio.get(posProd(id)).getIdProduto() + "-" + cardapio.get(posProd(id)).getNome()
                + " - R$" + cardapio.get(posProd(id)).getPreço());
    }

    // end class
}

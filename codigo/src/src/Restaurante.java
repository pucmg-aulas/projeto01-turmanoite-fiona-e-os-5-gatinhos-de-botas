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
        Mesa m1 = new Mesa(4);
        Mesa m2 = new Mesa(4);
        Mesa m3 = new Mesa(4);
        Mesa m4 = new Mesa(4);
        Mesa m5 = new Mesa(6);
        Mesa m6 = new Mesa(6);
        Mesa m7 = new Mesa(6);
        Mesa m8 = new Mesa(6);
        Mesa m9 = new Mesa(8);
        Mesa m10 = new Mesa(8);

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
                r.reqAtiva();
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
        if (verificaReqAtiva()) {
            System.out.println("Informe o número da mesa que deseja sair: ");
            imprimeReqsAtivas();
            int id = scanner.nextInt();
            boolean mesaEncontrada = false;

            for (Mesa mesa : mesas) {
                if (mesa.getIdMesa() == id && mesa.getStatus() == true && reqDaMesa(mesa).getPedido()
                        .getStatus() == false) {
                    mesa.desocuparMesa();
                    reqDaMesa(mesa).reqInativa();
                    this.saida = LocalTime.now();
                    LocalTime diftime = this.saida.minusNanos(this.entrada.toNanoOfDay());
                    System.out.println("Tempo de permanência: " + diftime);
                    this.verificarFila(mesa);
                    mesaEncontrada = true;
                    break;
                } else if (mesa.getIdMesa() == id && mesa.getStatus() == true && reqDaMesa(mesa).getPedido()
                        .getStatus() == true) {
                    System.out.println("Não é possível sair da mesa sem pagar");
                    break;

                } else {
                    System.out.println("Mesa não encontrada ou não ocupada");
                    break;
                }
            }

        } else {
            System.out.println("Não existem mesas ocupadas no momento");
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

    public Requisicao reqDaMesa(Mesa m) {
        for (Requisicao r : requisicoes) {
            if (r.getMesa().getIdMesa() == m.getIdMesa()) {
                return (r);
            }
        }
        return null;
    }

    public void fazerPedido() {
        Scanner scanner = new Scanner(System.in);
        if (verificaReqAtiva()) {

            System.out.println("Informe o identificador da requisição que deseja realizar um pedido: ");
            imprimeReqsAtivas();
            int idReq;
            int idProd;
            int qntProd;
            if (scanner.hasNextInt()) {
                idReq = scanner.nextInt();
                if (posReq(idReq) != -1 && requisicoes.get(posReq(idReq)).getStatus() == true) {
                    System.out.println("Verificando...\n ");
                    System.out.println("Requisição de id " + idReq + " selecionada ");
                    System.out.println("Informe o id do prato/produto que deseja pedir ");
                    imprimeCardapio();
                    if (scanner.hasNextInt()) {
                        idProd = scanner.nextInt();
                        if (posProd(idProd) != -1) {
                            System.out.println("Verificando...\n ");
                            System.out.println("Produto de id " + idProd + " selecionado: ");
                            System.out.println("Informe quantos pratos/produtos deseja pedir");
                            if (scanner.hasNextInt()) {
                                qntProd = scanner.nextInt();
                                if (qntProd > 0) {
                                    System.out.println(
                                            "Produto de id " + idProd + " selecionado " + qntProd + " vezes: ");
                                    imprimeProd(idProd);
                                    ItemProduto itemprod = new ItemProduto(cardapio.get(posProd(idProd)), qntProd);
                                    requisicoes.get(posReq(idReq)).getPedido().addItem(itemprod);
                                    requisicoes.get(posReq(idReq)).imprimePedido();
                                    requisicoes.get(posReq(idReq)).getPedido().ativaPedido();
                                } else {
                                    System.out.println("Quantidade não é válida");
                                }

                            } else {
                                System.out.println("Opção invalida");
                            }

                        } else {
                            System.out.println("Produto não encontrado");
                        }

                    } else {
                        System.out.println("Requisição não encontrada");
                    }

                } else {
                    System.out.println("Requisição não encontrada");
                }

            } else {
                System.out.println("Opção inválida. Tente novamente.");

            }
        } else {
            System.out.println("não existem requisições ativas no momento");
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

        System.out.println(cardapio.get(posProd(id)).getIdProduto() + " - " + cardapio.get(posProd(id)).getNome()
                + " - R$" + cardapio.get(posProd(id)).getPreço());
    }

    public void imprimeReqsAtivas() {
        for (Requisicao req : requisicoes) {
            System.out.println("Mesa " + req.getMesa().getIdMesa() + " - requisição no nome de "
                    + req.getCliente().getNome() + " (" + req.getIdRequisicao() + ") ");
        }
    }

    public boolean verificaReqAtiva() {
        boolean reqEncontrada = false;
        for (Requisicao req : requisicoes) {
            if (req.getStatus() == true) {
                reqEncontrada = true;
            }
        }
        return reqEncontrada;
    }

    public void fecharPedido() {
        Scanner scanner = new Scanner(System.in);
        if (verificaReqAtiva()) {

            System.out.println("Informe o identificador da requisição em que deseja finalizar o pedido: ");
            imprimeReqsAtivas();
            int idReq;
            if (scanner.hasNextInt()) {
                idReq = scanner.nextInt();
                for (Requisicao requisicao : requisicoes) {
                    if (idReq == requisicao.getIdRequisicao() && requisicao.getPedido().getStatus() == true) {
                        System.out.println("O valor a se pagar é: " + requisicao.getPedido().calculaTotal());
                        requisicao.getPedido().finaliza();

                    } else if (idReq == requisicao.getIdRequisicao() && requisicao.getPedido().getStatus() == false) {
                        System.out.println(" Pedido náo está ativo.");

                    } else {
                        System.out.println("Requisição não encontrada.");
                    }
                }
            }
            // end class
        } else {
            System.out.println("não existem requisições ativas no momento");
        }
    }
}

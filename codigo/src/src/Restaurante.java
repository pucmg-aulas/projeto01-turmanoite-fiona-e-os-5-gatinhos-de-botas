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
                r.setMesa(mesa);
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
        System.out.println("Informe o id da mesa que deseja sair: ");
        int id = scanner.nextInt();
        boolean mesaEncontrada = false;

        for (Mesa mesa : mesas) {
            if (mesa.getIdMesa() == id && mesa.getStatus() == true) {
                mesa.desocuparMesa();
                reqDaMesa(mesa).reqInativa();
                this.saida = LocalTime.now();
                LocalTime diftime = this.saida.minusNanos(this.entrada.toNanoOfDay());
                System.out.println("Tempo de permanência: " + diftime);
                this.verificarFila(mesa);
                mesaEncontrada = true;
                break;
            }
        }
        if (!mesaEncontrada) {
            System.out.println("Mesa não encontrada ou não ocupada");
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
        System.out.println("Informações da requisição: ");
        System.out.println("Requisição no nome de : " + r.getCliente().getNome());
        System.out.println("Identificador da requisição: " + r.getIdRequisicao());
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

    //exibir mesas ocupadas e desocupadas
    public void exibeMesas(){
        System.out.println("\n" + "Mesas disponíveis: ");
        for (Mesa mesa : mesas) {
            if (mesa.getStatus() == false) {
                System.out.println("Mesa " + mesa.getIdMesa() + " - Capacidade: " + mesa.getCapacidade());
            }
        }
        System.out.println("\n" + "Mesas ocupadas: ");
        for (Requisicao r : requisicoes) {
            if (r.getStatus() == true) {
                System.out.println("Mesa " + r.getMesa().getIdMesa() + " - Capacidade: " + r.getMesa().getCapacidade()
                        + " - Cliente: " + r.getCliente().getNome() + " ID da requisição: " + r.getIdRequisicao());
            }
        }

    }

    
    //fazer pedido
    public void fazerPedido() {
        Scanner scanner = new Scanner(System.in);
        exibeMesas();
        System.out.println("Informe o identificador da requisição que deseja realizar um pedido: ");
        int idReq;
        int idProd;

        if (scanner.hasNextInt()) {
            idReq = scanner.nextInt();
            if (posReq(idReq) != -1 && requisicoes.get(posReq(idReq)).getStatus() == true) {
                System.out.println("Verificando...\n ");
                System.out.println("requisição de id " + idReq + " selecionada ");
                imprimeCardapio();
                System.out.println("Informe o id do prato/produto que deseja pedir ");
                int prato = scanner.nextInt();
                System.out.println("Insira a quantidade desejada: ");
                int qnt = scanner.nextInt(); 
                if (prato > 0 && prato <= cardapio.size()) {
                    if (posProd(prato) != -1) {
                        Item_Pedido item = new Item_Pedido(cardapio.get(posProd(prato)), qnt);
                        System.out.println("Verificando...\n ");
                        System.out.println(qnt +" produto)s) de id " + prato + " selecionado(s): ");
                        requisicoes.get(posReq(idReq)).getPedido().addProduto(item);
                        System.out.println("Pedido realizado com sucesso: " + item.getQuantidade() + " - "
                                + item.getProduto().getNome() + " - R$ = " + item.getQuantidade() + " x "
                                + item.getProduto().getPreço() + " = " + item.getQuantidade() * item.getProduto().getPreço());

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

package src;
public class Mesa {
    private int capacidade;
    private boolean status;

    public Mesa(int capacidade, int numero) {
        this.capacidade = capacidade;
        this.status = false;

    }
    public int getCapacidade() {
        return this.capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    public boolean getStatus() {
        return this.status;
    }
<<<<<<< Updated upstream:codigo/src/src/src/Mesa.java
    public int getnumero() {
        return this.numero;
    }

    public void ocuparMesa() {
        this.status = true;
=======

    // OCUPAR e DESOCUPAR
    public void ocuparMesa() {
        this.status = true;
        System.out.println("Por favor, direcione o cliente à mesa " + this.idMesa + "\n \n");
>>>>>>> Stashed changes:codigo/src/src/Mesa.java
    }
    public void desocuparMesa() {
        this.status = false;
<<<<<<< Updated upstream:codigo/src/src/src/Mesa.java
        System.out.println("Iremos buscar na fila de espera, caso não haja, a mesa ficará livre!");
        verificarFila();
=======
        System.out.println("Mesa " + this.idMesa + " desocupada com sucesso! \n \n");
>>>>>>> Stashed changes:codigo/src/src/Mesa.java
    }
    public void verificarFila(){
        //não sei como faz p verificar a fila
        Requisicao.encontrarMesa();
    }
}
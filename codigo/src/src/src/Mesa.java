package src;
public class Mesa {
    private int capacidade;
    private boolean status;
    private int numero;

    public Mesa(int capacidade, int numero) {
        this.capacidade = capacidade;
        this.status = false;
        this.numero = numero;
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
    public int getnumero() {
        return this.numero;
    }

    public void ocuparMesa() {
        this.status = true;
    }
    public void desocuparMesa() {
        this.status = false;
        System.out.println("Iremos buscar na fila de espera, caso não haja, a mesa ficará livre!");
        verificarFila();
    }
    public void verificarFila(){
        //não sei como faz p verificar a fila
        Requisicao.encontrarMesa();
    }
}
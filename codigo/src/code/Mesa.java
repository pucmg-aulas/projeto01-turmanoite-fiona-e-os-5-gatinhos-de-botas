package code;

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
        verificarFila();
    }
    public void verificarFila(){
        //?
    }
}

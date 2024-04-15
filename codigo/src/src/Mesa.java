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
    public boolean getsStatus() {
        return this.status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public int getnumero() {
        return this.numero;
    }
    public void setnumero(int numero) {
        this.numero = numero;
    }

    public void ocuparMesa() {
        this.status = true;
    }
    public void desocuparMesa() {
        this.status = false;
    }
    public void verificarFila(){
        //?
    }
}

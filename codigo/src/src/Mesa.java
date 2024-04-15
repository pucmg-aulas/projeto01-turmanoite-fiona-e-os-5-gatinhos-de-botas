public class Mesa {
    private int capacidade;
    private boolean status;
    private int numero;

    public Mesa(int capacidade, int numero) {
        this.capacidade = capacidade;
        this.status = false;
        this.numero = numero;
    }
    public getCapacidade() {
        return this.capacidade;
    }
    public setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    public getsStatus() {
        return this.status;
    }
    public setStatus(boolean status) {
        this.status = status;
    }
    public getnumero() {
        return this.numero;
    }
    public setnumero(int numero) {
        this.numero = numero;
    }

    public void ocuparMesa() {
        this.status = true;
    }
    public void desocuparMesa() {
        this.status = false;
    }
    public void verificarFila(){
        
    }
}

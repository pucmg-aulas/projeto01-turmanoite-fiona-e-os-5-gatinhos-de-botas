
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

    public boolean getStatus() {
        return this.status;
    }

    public int getnumero() {
        return this.numero;
    }

    // OCUPAR e DESOCUPAR
    public void ocuparMesa() {
        this.status = true;
        System.out.println("Por favor, direcione o cliente à mesa " + this.numero + "\n \n");
    }

    public void desocuparMesa() {
        this.status = false;
        System.out.println("Mesa " + this.numero + " desocupada com sucesso! \n \n");
    }

}

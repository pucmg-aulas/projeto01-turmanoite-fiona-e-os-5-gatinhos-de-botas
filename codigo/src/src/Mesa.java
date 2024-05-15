
public class Mesa {
    private static int contadorMesa = 0;
    private int idMesa;
    private int capacidade;
    private boolean status;

    public Mesa(int capacidade) {
        this.idMesa = ++contadorMesa;
        this.capacidade = capacidade;
        this.status = false;

    }

    public int getIdMesa() {
        return (this.idMesa);
    }

    public int getCapacidade() {
        return this.capacidade;
    }

    public boolean getStatus() {
        return this.status;
    }

    // OCUPAR e DESOCUPAR
    public void ocuparMesa() {
        this.status = true;
        System.out.println("Por favor, direcione o cliente à mesa " + this.idMesa + "\n \n");
    }

    public void desocuparMesa() {
        this.status = false;
        System.out.println("Mesa " + this.idMesa + " desocupada com sucesso! \n \n");
    }

}

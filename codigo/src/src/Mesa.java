import java.time.LocalTime;

public class Mesa {
    private int capacidade;
    private boolean status;
    private int numero;

    LocalTime entrada;
    LocalTime saida;

    public LocalTime getEntrada() {
        return entrada;
    }

    public LocalTime getSaida() {
        return saida;
    }

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
        entrada = LocalTime.now();
        System.out.println("mesa " + this.numero + " ocupada");
    }

    public void desocuparMesa() {
        this.status = false;
        saida = LocalTime.now();
        System.out.println("Mesa desocupada  \n\n");
    }

}

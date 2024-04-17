
public class Mesa {
    private int capacidade;
    private boolean status;
    private int numMesa;

    Mesa (int capacidade, int numMesa){
        this.capacidade = capacidade;
        this.status = false;
        this.numMesa = numMesa;
    }

    public int getCapacidade(){
        return this.capacidade;
    }

    public int getNumMesa(){
        return this.numMesa;
    }

    public boolean getstatus(){
        return this.status;
    }

    public void ocuparMesa(){
        this.status = true;
    }

    public void desocuparMesa(){
        this.status = false;
    }

}

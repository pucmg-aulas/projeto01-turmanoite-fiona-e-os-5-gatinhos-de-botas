import java.util.ArrayList;

public class Restaurante {
    public static void main(String[] args) throws Exception {

        ArrayList<Requisicao> requisicoes = new ArrayList<>();
        ArrayList<Requisicao> filaDeEspera = new ArrayList<>();
        ArrayList<Mesa> mesas = new ArrayList<>();

        Mesa m1 = new Mesa(8, 1);
        Mesa m2 = new Mesa(8, 2);
        Mesa m3 = new Mesa(8, 3);
        Mesa m4 = new Mesa(8, 4);
        Mesa m5 = new Mesa(8, 5);
        Mesa m6 = new Mesa(8, 6);

        mesas.add(m1);
        mesas.add(m2);
        mesas.add(m3);

        

    }
}

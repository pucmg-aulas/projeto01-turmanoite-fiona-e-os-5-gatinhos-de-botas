import java.util.ArrayList;

public class Restaurante {
    private Mesa []mesas;
    private ArrayList<Requisicao> requisiçõesAtendidas;
    private ArrayList<Requisicao> filaDeEspera;

    public Requisicao verificarFila(){
        Cliente cliente = new Cliente("Albert", 6);
        Requisicao requisicao = new Requisicao(cliente);

        for(int i = 0; i < 10; i++){
            if(this.mesas[i].getstatus()){
                for(Requisicao r: filaDeEspera){
                    if (r.getCliente().getQtdPessoas() <= mesas[i].getCapacidade() && mesas[i].getstatus()) {
                        requisicao = r;
                        tirarFilaDeEspera(r);
                        
                        requisicao.setMesa(mesas[i]);
                        requisicao.setStatus(true); 
                        requisiçõesAtendidas.add(requisicao);
                        return requisicao;
                    }
                }
            }    
        }

        return null;
    }

    public boolean temMesa(){
        for(Mesa m: mesas){
            if(m.getstatus()){
                return true;
            }
        }
        return false;
    }

    public void colocaFilaDeEspera(Requisicao requisicao){
        filaDeEspera.add(requisicao);
    }

    public void tirarFilaDeEspera(Requisicao requisicao){
        filaDeEspera.remove(requisicao);
    }
}



public class Cliente {
    private static int contadorClientes = 0; 
    private int idCliente;
    private String nome;
    private int qtdPessoas;

    public Cliente(String nome, int qtdPessoas){
        this.idCliente = ++contadorClientes;
        this.nome = nome;
        this.qtdPessoas = qtdPessoas;
    }
    
    public String getNome(){
        return this.nome;
    }
    public int getQtdPessoas(){
        return this.qtdPessoas;
    }
}
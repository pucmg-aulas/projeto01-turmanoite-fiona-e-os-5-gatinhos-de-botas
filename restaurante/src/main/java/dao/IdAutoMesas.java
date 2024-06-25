package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

/**
 * Classe responsável por gerenciar o índice autoincremental.
 */
public class IdAutoMesas extends AbstractDAO {

    private int id; // índice autoincremental
    private static IdAutoMesas instancia; // instância única da classe
    private final String path = "./src/main/java/data/IdAutoMesas.dat"; // caminho do arquivo de serialização

    // Construtor privado para evitar instanciamento externo
    private IdAutoMesas() {
        this.id = 0;
        carregaId(); // carregar o valor do índice ao inicializar
    }

    // Método para obter a instância única da classe
    public static IdAutoMesas getInstancia() {
        if (instancia == null) {
            instancia = new IdAutoMesas();
        }
        return instancia;
    }

    // Método para carregar o valor do índice do arquivo
    private void carregaId() {
        File arq = new File(path);
        if (arq.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arq))) {
                this.id = ois.readInt();
            } catch (Exception e) {
                System.err.println("Erro ao carregar o ID: " + e.getMessage());
            }
        }
    }

    // Método para salvar o valor atualizado do índice no arquivo
    private void gravaId() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeInt(id);
        } catch (Exception e) {
            System.err.println("Erro ao gravar o ID: " + e.getMessage());
        }
    }

    // Método para obter o próximo ID disponível e incrementá-lo
    public synchronized int getProximoId() {
        int proximoId = ++id; // incrementar o índice
        gravaId(); // salvar o valor atualizado no arquivo
        return proximoId; // retornar o próximo ID
    }
}

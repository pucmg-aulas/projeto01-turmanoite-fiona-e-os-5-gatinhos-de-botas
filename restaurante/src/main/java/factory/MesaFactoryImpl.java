package factory;

import model.Mesa;

public class MesaFactoryImpl implements MesaFactory {

    @Override
    public Mesa criarMesa(int capacidade) {
        // Aqui você pode incluir qualquer lógica extra de criação,
        // como validações adicionais ou até mesmo instanciar subclasses de Mesa.
        return new Mesa(capacidade);
    }
}

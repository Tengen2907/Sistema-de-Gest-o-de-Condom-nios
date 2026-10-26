
package persistence;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Apartamento;


public class ApartamentoDAO {
    

    private final String caminho = "dados/Apartamento.dat";

    public ApartamentoDAO() {
        File pasta = new File("dados");

        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    // Guardar um novo Apartamento
    public void guardar(Apartamento apartamento) {

        if (apartamento == null || apartamento.getId() == null
                ||apartamento.getId().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID do apartamento é obrigatório.");
        }

        List<Apartamento> apartamentos = listar();

        for (Apartamento a : apartamentos) {
            if (a.getId().equals(apartamento.getId())) {
                throw new IllegalArgumentException(
                        "Já existe um apartamento com este ID.");
            }
        }

        apartamentos.add(apartamento);
        gravarFicheiro(apartamentos);
    }

    // Listar todos os apartamentos
    public List<Apartamento> listar() {

        File ficheiro = new File(caminho);

        if (!ficheiro.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream entrada =
                new ObjectInputStream(
                        new FileInputStream(ficheiro))) {

            return (List<Apartamento>) entrada.readObject();

        } catch (IOException | ClassNotFoundException a) {
            throw new RuntimeException(
                    "Erro ao carregar os apartamentos.", a);
        }
    }

    // Procurar um apartamento pelo ID
    public Apartamento buscarPorId(String id) {

        for (Apartamento morador : listar()) {
            if (morador.getId().equals(id)) {
                return morador;
            }
        }

        return null;
    }

    // Atualizar um Apartamento existente
    public void atualizar(Apartamento atualizado) {

        if (atualizado == null || atualizado.getId() == null) {
            throw new IllegalArgumentException(
                    "Apartamento ou ID inválido.");
        }

        List<Apartamento> apartamento= listar();

        for (int i = 0; i < apartamento.size(); i++) {

            if (apartamento.get(i).getId()
                    .equals(atualizado.getId())) {

                apartamento.set(i, atualizado);
                gravarFicheiro(apartamento);
                return;
            }
        }

        throw new IllegalArgumentException(
                "Apartamento não encontrado.");
    }

    // Eliminar um Apartamento pelo ID
    public void eliminar(String id) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID é obrigatório.");
        }

        List<Apartamento> moradores = listar();

        boolean removido = moradores.removeIf(
                m -> m.getId().equals(id));

        if (!removido) {
            throw new IllegalArgumentException(
                    "Apartamento não encontrado.");
        }

        gravarFicheiro(moradores);
    }

    // Gravar a lista no ficheiro .dat
    private void gravarFicheiro(List<Apartamento> moradores) {

        try (ObjectOutputStream saida =
                new ObjectOutputStream(
                        new FileOutputStream(caminho))) {

            saida.writeObject(moradores);

        } catch (IOException a) {
            throw new RuntimeException(
                    "Erro ao guardar os Apartamentos.", a);
        }
    }
}

    
    


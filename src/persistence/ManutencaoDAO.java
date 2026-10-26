package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Manutencao;

public class ManutencaoDAO {

    private final String caminho = "dados/ Manutencao.dat";

    public ManutencaoDAO() {
        File pasta = new File("dados");

        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    // Guardar uma nova manutencao
    public void guardar(Manutencao manutencao) {

        if (manutencao == null || manutencao.getId() == null
                || manutencao.getId().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID da manutencao é obrigatório.");
        }

        List< Manutencao> manutencaos = listar();

        for (Manutencao m : manutencaos) {
            if (m.getId().equals(manutencao.getId())) {
                throw new IllegalArgumentException(
                        "Já existe uma manutencao com este ID.");
            }
        }

        manutencaos .add(manutencao);
        gravarFicheiro(manutencaos);
    }

    // Listar todas as manutencaos
    public List< Manutencao> listar() {

        File ficheiro = new File(caminho);

        if (!ficheiro.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream entrada
                = new ObjectInputStream(
                        new FileInputStream(ficheiro))) {

            return (List< Manutencao>) entrada.readObject();

        } catch (IOException | ClassNotFoundException m) {
            throw new RuntimeException(
                    "Erro ao carregar as manutencaos.", m);
        }
    }

    // Procurar uma manutencao pelo ID
    public Manutencao buscarPorId(String id) {

        for (Manutencao manutencaos : listar()) {
            if (manutencaos.getId().equals(id)) {
                return manutencaos;
            }
        }

        return null;
    }

    // Atualizar um manutencao existente
    public void atualizar(Manutencao atualizado) {

        if (atualizado == null || atualizado.getId() == null) {
            throw new IllegalArgumentException(
                    "manutencao ou ID inválido.");
        }

        List< Manutencao> manutencaos = listar();

        for (int i = 0; i < manutencaos.size(); i++) {

            if (manutencaos.get(i).getId()
                    .equals(atualizado.getId())) {

                manutencaos.set(i, atualizado);
                gravarFicheiro(manutencaos);
                return;
            }
        }

        throw new IllegalArgumentException(
                "manutencao não encontrado.");
    }

    // Eliminar uma manutencao pelo ID
    public void eliminar(String id) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID é obrigatório.");
        }

        List< Manutencao> manutencaos = listar();

        boolean removido = manutencaos.removeIf(
                m -> m.getId().equals(id));

        if (!removido) {
            throw new IllegalArgumentException(
                    "manutencao não encontrado.");
        }

        gravarFicheiro(manutencaos);
    }

    // Gravar a lista no ficheiro .dat
    private void gravarFicheiro(List< Manutencao> manutencaos) {

        try (ObjectOutputStream saida
                = new ObjectOutputStream(
                        new FileOutputStream(caminho))) {

            saida.writeObject(manutencaos);

        } catch (IOException m) {
            throw new RuntimeException(
                    "Erro ao guardar as manutencao.", m);
        }
    }
}

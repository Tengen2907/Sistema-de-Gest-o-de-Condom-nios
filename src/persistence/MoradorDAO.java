
package persistence;

import model.Cadastro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoradorDAO {

    private final String caminho = "dados/moradores.dat";

    public MoradorDAO() {
        File pasta = new File("dados");

        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    // Guardar um novo morador
    public void guardar(Cadastro morador) {

        if (morador == null || morador.getId() == null
                || morador.getId().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID do morador é obrigatório.");
        }

        List<Cadastro> moradores = listar();

        for (Cadastro m : moradores) {
            if (m.getId().equals(morador.getId())) {
                throw new IllegalArgumentException(
                        "Já existe um morador com este ID.");
            }
        }

        moradores.add(morador);
        gravarFicheiro(moradores);
    }

    // Listar todos os moradores
    public List<Cadastro> listar() {

        File ficheiro = new File(caminho);

        if (!ficheiro.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream entrada =
                new ObjectInputStream(
                        new FileInputStream(ficheiro))) {

            return (List<Cadastro>) entrada.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(
                    "Erro ao carregar os moradores.", e);
        }
    }

    // Procurar um morador pelo ID
    public Cadastro buscarPorId(String id) {

        for (Cadastro morador : listar()) {
            if (morador.getId().equals(id)) {
                return morador;
            }
        }

        return null;
    }

    // Atualizar um morador existente
    public void atualizar(Cadastro atualizado) {

        if (atualizado == null || atualizado.getId() == null) {
            throw new IllegalArgumentException(
                    "Morador ou ID inválido.");
        }

        List<Cadastro> moradores = listar();

        for (int i = 0; i < moradores.size(); i++) {

            if (moradores.get(i).getId()
                    .equals(atualizado.getId())) {

                moradores.set(i, atualizado);
                gravarFicheiro(moradores);
                return;
            }
        }

        throw new IllegalArgumentException(
                "Morador não encontrado.");
    }

    // Eliminar um morador pelo ID
    public void eliminar(String id) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID é obrigatório.");
        }

        List<Cadastro> moradores = listar();

        boolean removido = moradores.removeIf(
                m -> m.getId().equals(id));

        if (!removido) {
            throw new IllegalArgumentException(
                    "Morador não encontrado.");
        }

        gravarFicheiro(moradores);
    }

    // Gravar a lista no ficheiro .dat
    private void gravarFicheiro(List<Cadastro> moradores) {

        try (ObjectOutputStream saida =
                new ObjectOutputStream(
                        new FileOutputStream(caminho))) {

            saida.writeObject(moradores);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao guardar os moradores.", e);
        }
    }
}

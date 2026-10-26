
package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Aviso;

public class AvisoDAO {
    
 
    private final String caminho = "dados/aviso.dat";

    public AvisoDAO() {
        File pasta = new File("dados");

        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    // Guardar um novo morador
    public void guardar(Aviso aviso) {

        if (aviso == null || aviso.getId() == null
                || aviso.getId().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID do aviso é obrigatório.");
        }

        List<Aviso> avisos = listar();

        for (Aviso m : avisos) {
            if (m.getId().equals(aviso.getId())) {
                throw new IllegalArgumentException(
                        "Já existe um aviso com este ID.");
            }
        }

        avisos .add(aviso);
        gravarFicheiro(avisos);
    }

    // Listar todos os avisos
    public List<Aviso> listar() {

        File ficheiro = new File(caminho);

        if (!ficheiro.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream entrada =
                new ObjectInputStream(
                        new FileInputStream(ficheiro))) {

            return (List<Aviso>) entrada.readObject();

        } catch (IOException | ClassNotFoundException v) {
            throw new RuntimeException(
                    "Erro ao carregar os avisos.", v);
        }
    }

    // Procurar um aviso pelo ID
    public Aviso buscarPorId(String id) {

        for (Aviso aviso : listar()) {
            if (aviso.getId().equals(id)) {
                return aviso;
            }
        }

        return null;
    }

    // Atualizar um aviso existente
    public void atualizar(Aviso atualizado) {

        if (atualizado == null || atualizado.getId() == null) {
            throw new IllegalArgumentException(
                    "Avisos ou ID inválido.");
        }

        List<Aviso> Avisos = listar();

        for (int i = 0; i < Avisos.size(); i++) {

            if (Avisos.get(i).getId()
                    .equals(atualizado.getId())) {

                Avisos.set(i, atualizado);
                gravarFicheiro(Avisos);
                return;
            }
        }

        throw new IllegalArgumentException(
                "Aviso não encontrado.");
    }

    // Eliminar um aviso pelo ID
    public void eliminar(String id) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID é obrigatório.");
        }

        List<Aviso> avisos = listar();

        boolean removido = avisos.removeIf(
                m -> m.getId().equals(id));

        if (!removido) {
            throw new IllegalArgumentException(
                    "Aviso não encontrado.");
        }

        gravarFicheiro(avisos);
    }

    // Gravar a lista no ficheiro .dat
    private void gravarFicheiro(List<Aviso> avisos) {

        try (ObjectOutputStream saida =
                new ObjectOutputStream(
                        new FileOutputStream(caminho))) {

            saida.writeObject(avisos);

        } catch (IOException v) {
            throw new RuntimeException(
                    "Erro ao guardar os avisos.", v);
        }
    }
}

    


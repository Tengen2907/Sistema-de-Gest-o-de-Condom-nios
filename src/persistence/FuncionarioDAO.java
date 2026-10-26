
package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;

public class FuncionarioDAO {
 
 
    private final String caminho = "dados/Funcionario.dat";

    public FuncionarioDAO() {
        File pasta = new File("dados");

        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    // Guardar um novo Funcionario
    public void guardar(Funcionario  funcionario) {

        if (funcionario == null || funcionario.getId() == null
                || funcionario.getId().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID do funcionarioé obrigatório.");
        }

        List<Funcionario > funcionarios = listar();

        for ( Funcionario   f: funcionarios) {
            if (f.getId().equals(funcionario.getId())) {
                throw new IllegalArgumentException(
                        "Já existe um funcionario com este ID.");
            }
        }

        funcionarios .add(funcionario );
        gravarFicheiro(funcionarios);
    }

    // Listar todos os funcionarios
    public List<Funcionario > listar() {

        File ficheiro = new File(caminho);

        if (!ficheiro.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream entrada =
                new ObjectInputStream(
                        new FileInputStream(ficheiro))) {

            return (List<Funcionario >) entrada.readObject();

        } catch (IOException | ClassNotFoundException f) {
            throw new RuntimeException(
                    "Erro ao carregar os funcionarios.", f);
        }
    }

    // Procurar um funcionario pelo ID
    public Funcionario buscarPorId(String id) {

        for (Funcionario  funcionarios : listar()) {
            if (funcionarios.getId().equals(id)) {
                return funcionarios;
            }
        }

        return null;
    }

    // Atualizar um funcionario existente
    public void atualizar(Funcionario  atualizado) {

        if (atualizado == null || atualizado.getId() == null) {
            throw new IllegalArgumentException(
                    "funcionario ou ID inválido.");
        }

        List<Funcionario > funcionarios = listar();

        for (int i = 0; i < funcionarios.size(); i++) {

            if (funcionarios.get(i).getId()
                    .equals(atualizado.getId())) {

                funcionarios.set(i, atualizado);
                gravarFicheiro(funcionarios);
                return;
            }
        }

        throw new IllegalArgumentException(
                "funcionario não encontrado.");
    }

    // Eliminar um morador pelo ID
    public void eliminar(String id) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID é obrigatório.");
        }

        List<Funcionario> funcionarios = listar();

        boolean removido = funcionarios.removeIf(
                m -> m.getId().equals(id));

        if (!removido) {
            throw new IllegalArgumentException(
                    "funcionario não encontrado.");
        }

        gravarFicheiro(funcionarios);
    }

    // Gravar a lista no ficheiro .dat
    private void gravarFicheiro(List<Funcionario> funcionarios) {

        try (ObjectOutputStream saida =
                new ObjectOutputStream(
                        new FileOutputStream(caminho))) {

            saida.writeObject(funcionarios);

        } catch (IOException f) {
            throw new RuntimeException(
                    "Erro ao guardar os funcionarios.", f);
        }
    }
}

    




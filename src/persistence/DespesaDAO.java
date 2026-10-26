/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Despesa;

public class DespesaDAO {

    private final String caminho = "dados/Despesas.dat";

    public DespesaDAO() {
        File pasta = new File("dados");

        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    // Guardar um novo morador
    public void guardar(Despesa despesa) {

        if (despesa == null || despesa.getId() == null
                || despesa.getId().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID da Despesa é obrigatório.");
        }

        List<Despesa> despesas = listar();

        for (Despesa d : despesas) {
            if (d.getId().equals(despesa.getId())) {
                throw new IllegalArgumentException(
                        "Já existe um despesa com este ID.");
            }
        }

        despesas.add(despesa);
        gravarFicheiro(despesas);
    }

    // Listar todos os moradores
    public List<Despesa> listar() {

        File ficheiro = new File(caminho);

        if (!ficheiro.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream entrada
                = new ObjectInputStream(
                        new FileInputStream(ficheiro))) {

            return (List<Despesa>) entrada.readObject();

        } catch (IOException | ClassNotFoundException d) {
            throw new RuntimeException(
                    "Erro ao carregar as Despesas.", d);
        }
    }

    // Procurar um despesa pelo ID
    public Despesa buscarPorId(String id) {

        for (Despesa despesa : listar()) {
            if (despesa.getId().equals(id)) {
                return despesa;
            }
        }

        return null;
    }

    // Atualizar uma despesa existente
    public void atualizar(Despesa atualizado) {

        if (atualizado == null || atualizado.getId() == null) {
            throw new IllegalArgumentException(
                    "Morador ou ID inválido.");
        }

        List<Despesa> despesas = listar();

        for (int i = 0; i < despesas.size(); i++) {

            if (despesas.get(i).getId()
                    .equals(atualizado.getId())) {

                despesas.set(i, atualizado);
                gravarFicheiro(despesas);
                return;
            }
        }

        throw new IllegalArgumentException(
                "Despesas não encontrado.");
    }

    // Eliminar uma Despesas pelo ID
    public void eliminar(String id) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID é obrigatório.");
        }

        List<Despesa> despesas = listar();

        boolean removido = despesas.removeIf(
                m -> m.getId().equals(id));

        if (!removido) {
            throw new IllegalArgumentException(
                    "despesa não encontrado.");
        }

        gravarFicheiro(despesas);
    }

    // Gravar a lista no ficheiro .dat
    private void gravarFicheiro(List<Despesa> despesas) {

        try (ObjectOutputStream saida
                = new ObjectOutputStream(
                        new FileOutputStream(caminho))) {

            saida.writeObject(despesas);

        } catch (IOException d) {
            throw new RuntimeException(
                    "Erro ao guardar as despesas.", d);
        }
    }
}

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
import model.Pagamento;


/**
 *
 * @author yurankali
 */
public class PagamentoDAO {
    
    
    private final String caminho = "dados/Pagamentos.dat";

    public PagamentoDAO() {
        File pasta = new File("dados");

        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    // Guardar um novo Pagamento
    public void guardar(Pagamento pagamento) {

        if (pagamento == null || pagamento.getId() == null
                || pagamento.getId().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID do Pagamento é obrigatório.");
        }

        List<Pagamento> pagamentos = listar();

        for (Pagamento p : pagamentos) {
            if (p.getId().equals(pagamento.getId())) {
                throw new IllegalArgumentException(
                        "Já existe um Pagamento com este ID.");
            }
        }

        pagamentos.add(pagamento);
        gravarFicheiro(pagamentos);
    }

    // Listar todos os Pagamentos
    public List<Pagamento> listar() {

        File ficheiro = new File(caminho);

        if (!ficheiro.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream entrada =
                new ObjectInputStream(
                        new FileInputStream(ficheiro))) {

            return (List<Pagamento>) entrada.readObject();

        } catch (IOException | ClassNotFoundException p) {
            throw new RuntimeException(
                    "Erro ao carregar os Pagamentos.", p);
        }
    }

    // Procurar um Pagamento pelo ID
    public Pagamento buscarPorId(String id) {

        for (Pagamento Pagamento : listar()) {
            if (Pagamento.getId().equals(id)) {
                return Pagamento;
            }
        }

        return null;
    }

    // Atualizar um Pagamento existente
    public void atualizar(Pagamento atualizado) {

        if (atualizado == null || atualizado.getId() == null) {
            throw new IllegalArgumentException(
                    "Pagamento ou ID inválido.");
        }

        List<Pagamento> pagamentos = listar();

        for (int i = 0; i < pagamentos.size(); i++) {

            if (pagamentos.get(i).getId()
                    .equals(atualizado.getId())) {

                pagamentos.set(i, atualizado);
                gravarFicheiro(pagamentos);
                return;
            }
        }

        throw new IllegalArgumentException(
                "Pagamento não encontrado.");
    }

    // Eliminar um Pagamento pelo ID
    public void eliminar(String id) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID é obrigatório.");
        }

        List<Pagamento> pagamentos = listar();

        boolean removido = pagamentos.removeIf(
                p-> p.getId().equals(id));

        if (!removido) {
            throw new IllegalArgumentException(
                    "Pagamento não encontrado.");
        }

        gravarFicheiro(pagamentos);
    }

    // Gravar a lista no ficheiro .dat
    private void gravarFicheiro(List<Pagamento> pagamentos) {

        try (ObjectOutputStream saida =
                new ObjectOutputStream(
                        new FileOutputStream(caminho))) {

            saida.writeObject(pagamentos);

        } catch (IOException p) {
            throw new RuntimeException(
                    "Erro ao guardar os Pagamentos.", p);
        }
    }
}

    


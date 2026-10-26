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

import model.Notificacao;

/**
 *
 * @author yurankali
 */
public class NotificacaoDAO {
     

    private final String caminho = "dados/notificacao.dat";

    public NotificacaoDAO() {
        File pasta = new File("dados");

        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    // Guardar uma nova notificacao
    public void guardar(Notificacao notificacao) {

        if (notificacao == null ||notificacao .getId() == null
                || notificacao .getId().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID da notificacao é obrigatório.");
        }

        List<Notificacao> notificacaos = listar();

        for (Notificacao n : notificacaos) {
            if (n.getId().equals(notificacao.getId())) {
                throw new IllegalArgumentException(
                        "Já existe uma notificacao com este ID.");
            }
        }

        notificacaos.add(notificacao);
        gravarFicheiro(notificacaos);
    }

    // Listar todos as notificacaos
    public List<Notificacao> listar() {

        File ficheiro = new File(caminho);

        if (!ficheiro.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream entrada =
                new ObjectInputStream(
                        new FileInputStream(ficheiro))) {

            return (List<Notificacao>) entrada.readObject();

        } catch (IOException | ClassNotFoundException t) {
            throw new RuntimeException(
                    "Erro ao carregar as notificacaos.", t);
        }
    }

    // Procurar uma notificacao pelo ID
    public Notificacao buscarPorId(String id) {

        for (Notificacao notificacao : listar()) {
            if (notificacao.getId().equals(id)) {
                return notificacao;
            }
        }

        return null;
    }

    // Atualizar uma notificacao existente
    public void atualizar(Notificacao atualizado) {

        if (atualizado == null || atualizado.getId() == null) {
            throw new IllegalArgumentException(
                    "notificacao ou ID inválido.");
        }

        List<Notificacao> notificacaos = listar();

        for (int i = 0; i < notificacaos.size(); i++) {

            if (notificacaos.get(i).getId()
                    .equals(atualizado.getId())) {

                notificacaos.set(i, atualizado);
                gravarFicheiro(notificacaos);
                return;
            }
        }

        throw new IllegalArgumentException(
                "notificacao não encontrada.");
    }

    // Eliminar uma notificacao pelo ID
    public void eliminar(String id) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID é obrigatório.");
        }

        List<Notificacao> notificacaos = listar();

        boolean removido = notificacaos.removeIf(
                m -> m.getId().equals(id));

        if (!removido) {
            throw new IllegalArgumentException(
                    "notificacao não encontrada.");
        }

        gravarFicheiro(notificacaos);
    }

    // Gravar a lista no ficheiro .dat
    private void gravarFicheiro(List<Notificacao> notificacaos) {

        try (ObjectOutputStream saida =
                new ObjectOutputStream(
                        new FileOutputStream(caminho))) {

            saida.writeObject(notificacaos);

        } catch (IOException t) {
            throw new RuntimeException(
                    "Erro ao guardar as notificacaos.", t);
        }
    }
}

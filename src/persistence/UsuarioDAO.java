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
import model.Usuario;

/**
 *
 * @author yurankali
 */
public class UsuarioDAO {
    

    private final String caminho = "dados/Usuario.dat";

    public UsuarioDAO() {
        File pasta = new File("dados");

        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    // Guardar um novo Usuario
    public void guardar(Usuario usuario) {

        if (usuario == null || usuario.getId() == null
                || usuario.getId().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID do Usuario é obrigatório.");
        }

        List<Usuario> usuarios = listar();

        for (Usuario u : usuarios) {
            if (u.getId().equals(usuario.getId())) {
                throw new IllegalArgumentException(
                        "Já existe um Usuario com este ID.");
            }
        }

        usuarios.add(usuario);
        gravarFicheiro(usuarios);
    }

    // Listar todos os moradores
    public List<Usuario> listar() {

        File ficheiro = new File(caminho);

        if (!ficheiro.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream entrada =
                new ObjectInputStream(
                        new FileInputStream(ficheiro))) {

            return (List<Usuario>) entrada.readObject();

        } catch (IOException | ClassNotFoundException u) {
            throw new RuntimeException(
                    "Erro ao carregar o Usuario.", u);
        }
    }

    // Procurar um Usuario pelo ID
    public Usuario buscarPorId(String id) {

        for (Usuario usuario : listar()) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }

        return null;
    }

    // Atualizar um Usuario existente
    public void atualizar(Usuario atualizado) {

        if (atualizado == null || atualizado.getId() == null) {
            throw new IllegalArgumentException(
                    " ID inválido.");
        }

        List<Usuario> usuarios = listar();

        for (int i = 0; i < usuarios.size(); i++) {

            if (usuarios.get(i).getId()
                    .equals(atualizado.getId())) {

                usuarios.set(i, atualizado);
                gravarFicheiro(usuarios);
                return;
            }
        }

        throw new IllegalArgumentException(
                "Usuario não encontrado.");
    }

    // Eliminar um Usuario pelo ID
    public void eliminar(String id) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O ID é obrigatório.");
        }

        List<Usuario> usuarios = listar();

        boolean removido = usuarios.removeIf(
                m -> m.getId().equals(id));

        if (!removido) {
            throw new IllegalArgumentException(
                    "Usuario não encontrado.");
        }

        gravarFicheiro(usuarios);
    }

    // Gravar a lista no ficheiro .dat
    private void gravarFicheiro(List<Usuario> usuarios) {

        try (ObjectOutputStream saida =
                new ObjectOutputStream(
                        new FileOutputStream(caminho))) {

            saida.writeObject(usuarios);

        } catch (IOException u) {
            throw new RuntimeException(
                    "Erro ao guardar os Usuario.", u);
        }
    }
}

    


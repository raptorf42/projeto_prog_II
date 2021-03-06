package repositorioArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JOptionPane;

import excepitonRepositorioArray.AdministradorJaCadastradoException;
import excepitonRepositorioArray.AdministradorNaoEncotradoException;
import excepitonRepositorioArray.AdministradorVazioException;
import negocioClassesBasicas.Administrador;
import repositorio.RepositorioAdministrador;

public class RepositorioAdministradorArray implements RepositorioAdministrador, Serializable {
	private Administrador[] array;
	private int indice;
	private int TAMANHO = 100;
	private static RepositorioAdministradorArray instance;

	public RepositorioAdministradorArray() {
		array = new Administrador[TAMANHO];
		indice = 0;
	}

	public static RepositorioAdministradorArray getInstance() {
		if (instance == null) {
			instance = lerDoArquivo();
		}
		return instance;
	}

	public static RepositorioAdministradorArray lerDoArquivo() {
		RepositorioAdministradorArray instanciaLocal = null;
		// Criando um arquivo e passando o nome dele
		File in = new File("Administrador.dat");// criando um arquivo .dat na pasta do projeto

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(in);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			instanciaLocal = (RepositorioAdministradorArray) o;
		} catch (Exception e) {
			instanciaLocal = new RepositorioAdministradorArray();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
				}
			}
		}
		return instanciaLocal;
	}

	public static void salvarArquivo() {
		if (!(instance == null)) {

			File out = new File("Administrador.dat");
			FileOutputStream fos = null;
			ObjectOutputStream oos = null;
			try {
				fos = new FileOutputStream(out);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(instance);
			} catch (Exception e) {
				 e.printStackTrace();
				
			} finally {
				if (oos != null) {
					try {
						oos.close();
					} catch (IOException e) {
					}
				}
			}
		}
	}

	@Override
	public void inserir(Administrador administrador)
			throws AdministradorVazioException, AdministradorJaCadastradoException {
		if (administrador == null) {

		} else {

			Administrador resultadoBusca = null;

			for (int i = 0; i < indice; i++) {
				if (!(array[i] == null) && administrador.getId() == array[i].getId()) {
					resultadoBusca = array[i];
				}
			}

			if (resultadoBusca != null) {

			} else {
				array[indice] = administrador;
				indice++;
			}
		}
	}

	@Override
	public void remover(int id) throws AdministradorNaoEncotradoException {
		buscar(id);

		for (int i = 0, j = indice; i < j; i++) {
			if (!(array[i] == null) && id == array[i].getId()) {
				array[i] = array[indice];
				array[--indice] = null;
				indice--;
			}
		}

	}

	@Override
	public Administrador buscar(int id) throws AdministradorNaoEncotradoException {
		Administrador resultadoBusca = null;

		for (int i = 0, j = indice; i < j; i++) {
			if (array[i] != null && id == array[i].getId()) {
				resultadoBusca = array[i];
			}
		}

		if (resultadoBusca == null) {
			AdministradorNaoEncotradoException e = new AdministradorNaoEncotradoException();
			throw e;
		}

		return resultadoBusca;

	}

	@Override
	public void alterar(Administrador novoAdministrador)
			throws AdministradorVazioException, AdministradorNaoEncotradoException {
		if (novoAdministrador == null) {

			AdministradorVazioException e = new AdministradorVazioException();

			throw e;
		} else {

			buscar(novoAdministrador.getId());

			for (int i = 0, j = indice; i < j; i++) {
				if (array[i] != null && novoAdministrador.getId() == array[i].getId()) {
					array[i] = novoAdministrador;
				}
			}
		}

	}

	@Override
	public Administrador[] listarAdministrador() {
		return array;
	}

}

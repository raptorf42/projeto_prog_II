package repositorio;

import negocio.Usuario;

public class RepositorioUsuarioArray implements RepositorioUsuario {

	private Usuario[] array;
	private static int indice;
	private final static int TAMANHO = 100;

	public RepositorioUsuarioArray() {
		array = new Usuario[TAMANHO];
	}

	@Override
	public void inserir(Usuario usuario) {
		if (usuario != null) {
			if (indice > 0) {
				if (buscar(usuario.getCpf()) == null) {
					array[indice] = usuario;
					indice++;
				}

			} else {
				{
					array[indice] = usuario;
					indice++;
				}
			}
		}
	}

	@Override
	public void atualizar(Usuario usuario) {

		for (int i = 0, j = indice; i < j; i++) {
			if (usuario.getCpf().equals(array[i].getCpf())) {
				array[i] = usuario;

			}
		}
	}

	@Override
	public Usuario buscar(String cpf) {
		for (int i = 0; i < indice; i++) {

			if (cpf.equals(array[i].getCpf())) {
				return array[i];
			}
		}
		return null;
	}

	@Override
	public void remover(String cpf) {
		for (int i = 0, j = indice; i < j; i++) {

			if (cpf.equals(array[i].getCpf())) {

				array[i] = array[indice];
				array[indice] = null;
				indice--;

			}
		}

	}

	@Override
	public Usuario[] listar() {

		return array;
	}

}
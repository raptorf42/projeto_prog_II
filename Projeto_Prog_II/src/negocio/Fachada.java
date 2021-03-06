package negocio;

import java.util.ArrayList;
import java.util.List;

import excepitonRepositorioArray.AdministradorJaCadastradoException;
import excepitonRepositorioArray.AdministradorNaoEncotradoException;
import excepitonRepositorioArray.AdministradorVazioException;
import excepitonRepositorioArray.LojaJaCadastradaException;
import excepitonRepositorioArray.LojaNaoCadastradaException;
import excepitonRepositorioArray.LojaVaziaException;
import excepitonRepositorioArray.PedidoJaInseridoException;
import excepitonRepositorioArray.PedidoNaoCadastrado;
import excepitonRepositorioArray.PedidoVazioException;
import excepitonRepositorioArray.PratoNaoEncontradoException;
import excepitonRepositorioArray.PratoVazioException;
import excepitonRepositorioArray.UsuarioAnteriormenteCadastradoException;
import excepitonRepositorioArray.UsuarioNaoCadastradoException;
import excepitonRepositorioArray.UsuarioVazioException;
import negocioClassesBasicas.Administrador;
import negocioClassesBasicas.Cliente;
import negocioClassesBasicas.Entregador;
import negocioClassesBasicas.Loja;
import negocioClassesBasicas.Pedido;
import negocioClassesBasicas.Prato;
import negocioClassesBasicas.Usuario;

public class Fachada {

	private static Fachada instance;
	private ControleUsuario usuarios;
	private ControleLoja lojas;
	private ControlePedidos pedidos;
	private ControleLogin loginControle;
	private ControleAdiministrador adiministrador;

	public Fachada() {
		usuarios = new ControleUsuario();
		lojas = new ControleLoja();
		pedidos = new ControlePedidos();
		loginControle = new ControleLogin();
		adiministrador = new ControleAdiministrador();
	}

	public static Fachada getInstance() {
		if (instance == null) {
			instance = new Fachada();
		}
		return instance;
	}

	// ControleLogin

	public String loginCliente(String cpf, String senha) throws UsuarioNaoCadastradoException {
		return loginControle.loginCliente(cpf, senha);
	}

	public int loginAdministrador(String login, String senha) throws AdministradorNaoEncotradoException {
		return loginControle.loginAdministrador(login, senha);
	}

	// ControleAdministrador

	public void inserirAdmnistrador(Administrador adm)
			throws AdministradorVazioException, AdministradorJaCadastradoException {
		adiministrador.inserirAdmnistrador(adm);
	}

	public void removerAdmnistrador(int id) throws AdministradorNaoEncotradoException {
		adiministrador.removerAdmnistrador(id);
	}

	public Administrador buscarAdmnistrador(int id) throws AdministradorNaoEncotradoException {
		return adiministrador.buscarAdmnistrador(id);
	}

	public void alterarAdministrador(Administrador adm)
			throws AdministradorVazioException, AdministradorNaoEncotradoException {
		adiministrador.alterarAdministrador(adm);
	}

	public Administrador[] listarAdiministrador() {
		return adiministrador.listarAdministrador();
	}

	// Controle Pedido

	public void finalizarPedido(Pedido pedido)
			throws LojaNaoCadastradaException, PratoNaoEncontradoException, PratoVazioException {
		pedidos.finalizarPedido(pedido);
	}

	public int fazerPedido(Cliente cliente, Loja loja, ArrayList<Prato> pratosEscolhidos)
			throws PedidoJaInseridoException, PedidoVazioException {
		return pedidos.fazerPedido(cliente, loja, pratosEscolhidos);
	}

	public Pedido novoPedido() {
		return pedidos.novoPedido();
	}

	public void inserirPedido(Pedido pedido) throws PedidoJaInseridoException, PedidoVazioException {
		pedidos.inserirPedido(pedido);
	}

	public void removerPedido(int codigo) throws PedidoNaoCadastrado {
		pedidos.removerPedido(codigo);
	}

	public Pedido buscarPedido(int codigo) throws PedidoNaoCadastrado {
		return pedidos.buscar(codigo);
	}

	public void alterarPedido(Pedido pedido) throws PedidoVazioException, PedidoNaoCadastrado {
		pedidos.alterar(pedido);
	}

	public Pedido[] listarPedido() {
		return pedidos.listar();
	}

	// Controle Loja

	public void inserirLoja(String nome, String telefone, String cnpj, String endereco)
			throws LojaVaziaException, LojaJaCadastradaException {
		lojas.inserir(new Loja(nome, telefone, cnpj, endereco));
	}

	public void removerLoja(String cnpj) throws LojaNaoCadastradaException {
		lojas.remover(cnpj);
	}

	public Loja buscarLoja(String cnpj) throws LojaNaoCadastradaException {
		return lojas.buscar(cnpj);
	}

	public void alterarLoja(String nome, String telefone, String cnpj, String endereco)
			throws LojaVaziaException, LojaNaoCadastradaException {
		lojas.alterar(new Loja(nome, telefone, cnpj, endereco));
	}

	public List<Loja> listarLoja() {
		return lojas.listar();
	}

	// Controle Usuario
	public void inserirUsuarioCliente(String nomeUsuario, String senha, String nome, String telefone, String cpf,
			String endereco, String email)
			throws UsuarioVazioException, UsuarioAnteriormenteCadastradoException, UsuarioNaoCadastradoException {
		usuarios.inserir(new Cliente(nome, telefone, cpf, email, endereco, nomeUsuario, senha));
	}

	public void inserirUsuarioEntregador(String nome, String telefone, String cpf, String placaVeiculo, String email)
			throws UsuarioVazioException, UsuarioAnteriormenteCadastradoException, UsuarioNaoCadastradoException {
		usuarios.inserir(new Entregador(nome, telefone, cpf, placaVeiculo, email));

	}

	public void removerUsuario(String cpf) throws UsuarioNaoCadastradoException {
		usuarios.remover(cpf);
	}

	public Usuario buscarUsuario(String cpf) throws UsuarioNaoCadastradoException {
		return usuarios.buscar(cpf);
	}

	public void atualizarUsuarioEntregador(String nome, String telefone, String cpf, String placaVeiculo, String email)
			throws UsuarioVazioException, UsuarioNaoCadastradoException {
		usuarios.atualizar(new Entregador(nome, telefone, cpf, placaVeiculo, email));
	}

	public void atualizarUsuarioCliente(String nomeUsuario, String senha, String nome, String telefone, String cpf,
			String endereco, String email) throws UsuarioVazioException, UsuarioNaoCadastradoException {
		usuarios.atualizar(new Cliente(nome, telefone, cpf, email, endereco, nomeUsuario, senha));
	}

	public ArrayList<Usuario> listarUsuario() {
		return usuarios.listar();
	}

}

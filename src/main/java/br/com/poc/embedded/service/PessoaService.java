package br.com.poc.embedded.service;

import java.util.List;
import java.util.Optional;

import br.com.poc.embedded.repository.model.Pessoa;

public interface PessoaService {

	Pessoa buscaPorId(Long id);

	void salvarPessoa(Pessoa pessoa);

	Optional<Pessoa> buscaPorNome(String nome);

	void limpaTabela();

	List<Pessoa> buscaPorCepOrdenadorPorIdadeDescrecente(String codigo);

}

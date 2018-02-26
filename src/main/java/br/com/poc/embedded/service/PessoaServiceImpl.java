package br.com.poc.embedded.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.poc.embedded.repository.dao.PessoaRepository;
import br.com.poc.embedded.repository.model.Pessoa;

@Service("pessoaService")
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public void salvarPessoa(Pessoa pessoa) {
		pessoaRepository.saveAndFlush(pessoa);
	}

	public Pessoa buscaPorId(Long id) {
		return pessoaRepository.findOne(id);
	}

	public Optional<Pessoa> buscaPorNome(String nome) {
		return pessoaRepository.findFirstByNome(nome);
	}

	@Override
	public void limpaTabela() {
		pessoaRepository.deleteAll();

	}

	@Override
	public List<Pessoa> buscaPorCepOrdenadorPorIdadeDescrecente(String codigo) {
		return pessoaRepository.findByEnderecoCepCodigoContainingOrderByDataNascimentoAsc(codigo);
	}

}

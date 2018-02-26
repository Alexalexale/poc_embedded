package br.com.poc.embedded.repository.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.poc.embedded.repository.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	Optional<Pessoa> findFirstByNome(String nome);

	List<Pessoa> findByEnderecoCepCodigoContainingOrderByDataNascimentoAsc(String codigo);
}

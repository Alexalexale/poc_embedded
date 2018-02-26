package br.com.poc.embedded.repository.model;

import java.time.LocalDate;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Documento {

	@CPF
	private String cpf;

	private String rg;

	private LocalDate dataEmissao;

}

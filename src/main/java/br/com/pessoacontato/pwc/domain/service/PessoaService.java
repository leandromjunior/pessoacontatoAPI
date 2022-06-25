package br.com.pessoacontato.pwc.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pessoacontato.pwc.domain.exception.EntidadeNaoEncontradaException;
import br.com.pessoacontato.pwc.domain.model.Pessoa;
import br.com.pessoacontato.pwc.domain.repository.PessoaRepository;

@Service
public class PessoaService {

	private PessoaRepository pessoaRepository;
	
	@Autowired
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();		
	}
	
	public Pessoa getPessoa(Long id) {
		return findOrFail(id);
	}
	
	public Pessoa salvarPessoa(Pessoa p) {
		p.getContatos().forEach(c -> c.setPessoa(p));
		return pessoaRepository.save(p);
	}
	
	public Pessoa atualizarPessoa(Long id, Pessoa p) {
		Pessoa pessoaSalva = findOrFail(id);
		
		pessoaSalva.getContatos().clear();
		
		pessoaSalva.getContatos().addAll(p.getContatos());
		
		pessoaSalva.getContatos().forEach(c -> c.setPessoa(pessoaSalva));
		
		BeanUtils.copyProperties(p, pessoaSalva, "id", "contatos");
		
		return pessoaRepository.save(pessoaSalva);
		
	}
	
	public void deletarPessoa(Long id) {
		Pessoa p = findOrFail(id);
		pessoaRepository.delete(p);
	}
	
	private Pessoa findOrFail(Long id) {
		return pessoaRepository.findById(id).
				orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não localizada"));
	}
	
	
}

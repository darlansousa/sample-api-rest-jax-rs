package br.com.getcriativa.amgdevapi.pessoa.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.getcriativa.amgdevapi.pessoa.entity.Pessoa;

public class PessoaRepository {
	
	private final static HashMap<Integer, Pessoa> pessoas = new HashMap<Integer, Pessoa>();
	
	public List<Pessoa> GetAll(){
		return new ArrayList<Pessoa>(pessoas.values());
	}
	
	public Pessoa Get(final int id) {
		return pessoas.get(id);
	}
	
	public void Add(final Pessoa pessoa) {
		if(pessoa.getIdentificator().equals(0))
			pessoa.setIdentificator(generateId(pessoa.getIdentificator()));
		pessoas.put(pessoa.getIdentificator(), pessoa);
	}
	
	public void Edit(final Pessoa pessoa) {
		pessoas.remove(pessoa.getIdentificator());
		pessoas.put(pessoa.getIdentificator(), pessoa);
	}
	
	public void Delete(final Integer identificator) {
		pessoas.remove(identificator);
	}
	
	private Integer generateId(final Integer possible) {
		if(pessoas.containsKey(possible))
			return generateId(possible +1);
		
		return possible;
	}

}

package br.com.etechoracio.boa_viagem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.etechoracio.boa_viagem.entity.Gasto;
import br.com.etechoracio.boa_viagem.entity.Viagem;
import br.com.etechoracio.boa_viagem.repository.GastoRepository;
import br.com.etechoracio.boa_viagem.repository.ViagemRepository;


@Service
public class ViagemService {
	@Autowired
	private ViagemRepository viagemrepository;
	
	@Autowired
	private GastoRepository gastoRepository;
	
	public boolean excluir (Long id) { 
		boolean existe = viagemrepository.existsById(id);
		if (!existe) {
			return existe;
		}
	
	
	List<Gasto> gastos = gastoRepository.findByViagemId(id);
	
	if (!gastos.isEmpty()) {
		gastoRepository.deleteAll(gastos);
	}
	viagemrepository.deleteById(id);
	return existe;
		
	}
	public List<Viagem> listarTodos() {

		return viagemrepository.findAll();
	}

	public Optional<Viagem> buscarPorId(Long id) {

		return viagemrepository.findById(id);

	}

	public boolean deletarPorId(Long id) {
		boolean existe = viagemrepository.existsById(id);

		if (existe) {

			viagemrepository.deleteById(id);
		}
		return existe;
	}

	public Viagem inserir(Viagem obj) {

		return viagemrepository.save(obj);

	}

	public Optional<Object> atualizar(Long id, Viagem viagem) {

		boolean existe = viagemrepository.existsById(id);

		if (!existe) {

			return Optional.empty();
		}

		return Optional.of(viagemrepository.save(viagem));

	}
}

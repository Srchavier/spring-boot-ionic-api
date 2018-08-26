package com.cursomc.service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cursomc.builder.ClienteBuilder;
import com.cursomc.dto.ClienteDTO;
import com.cursomc.enums.PerfilCliente;
import com.cursomc.model.Cliente;
import com.cursomc.repository.ClienteRepository;
import com.cursomc.repository.EnderecoRepository;
import com.cursomc.security.UserSS;
import com.cursomc.service.exception.AuthorizationException;
import com.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private S3Service s3Service;

	@Transactional
	public Cliente salvar(Cliente cliente) {

		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	public Cliente alterar(@Valid Cliente cliente, Long id) {

		Cliente clienteSalvo = buscarPorId(id);
		// BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		alterarCliente(cliente, clienteSalvo);
		return clienteRepository.save(clienteSalvo);
	}

	private void alterarCliente(@Valid Cliente cliente, Cliente clienteSalvo) {

		clienteSalvo.setNome(cliente.getNome());
		clienteSalvo.setEmail(cliente.getEmail());
	}

	public void deleta(Long id) {

		buscarPorId(id);
		clienteRepository.deleteById(id);
	}

	public Cliente buscarPorId(Long id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(PerfilCliente.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objecto não encontrado id:" + id + " type:" + Cliente.class.getName()));
	}

	public List<ClienteDTO> buscarTodos() {

		List<Cliente> cat = clienteRepository.findAll();
		return cat.stream().map(obj -> ClienteBuilder.builder(obj)).collect(Collectors.toList());
	}

	public Page<ClienteDTO> listaComPaginacao(Integer pagina, Integer numeroLinhas, String ordem, String direcao) {

		PageRequest pageRequest = PageRequest.of(pagina, numeroLinhas, Direction.valueOf(direcao), ordem);
		Page<Cliente> cliente = clienteRepository.findAll(pageRequest);
		return cliente.map(obj -> ClienteBuilder.builder(obj));
	}

	public URI uploadProfileFoto(MultipartFile file) {
		return s3Service.uploadFile(file);
	}

}

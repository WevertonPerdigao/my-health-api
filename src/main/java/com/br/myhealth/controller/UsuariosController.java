package com.br.myhealth.controller;


import com.br.myhealth.controller.dto.AgendamentoDTO;
import com.br.myhealth.controller.dto.UsuarioDTO;
import com.br.myhealth.controller.form.UsuarioForm;
import com.br.myhealth.exception.HealthException;
import com.br.myhealth.model.Agendamento;
import com.br.myhealth.model.Usuario;
import com.br.myhealth.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuarioForm.setSenha(encoder.encode(usuarioForm.getSenha()));
        Usuario usuario = usuarioRepository.save(convertToUsuario(usuarioForm));
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(convertToUsuarioDTO(usuario));
    }

    @GetMapping(value = "/{id}")
    public UsuarioDTO findById(@PathVariable("id") Long id) {
        return convertToUsuarioDTO(usuarioRepository.findById(id).orElseThrow(() -> new HealthException("Usuário não encontrado")));
    }

    @GetMapping()
    public List<UsuarioDTO> listAll() {
        return convertToListUsuarioDTO(usuarioRepository.findAll());
    }

    public UsuarioDTO convertToUsuarioDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    private List<UsuarioDTO> convertToListUsuarioDTO(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    public Usuario convertToUsuario(UsuarioForm usuario) {
        return modelMapper.map(usuario, Usuario.class);
    }


}

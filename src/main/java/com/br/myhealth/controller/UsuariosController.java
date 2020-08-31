package com.br.myhealth.controller;


import com.br.myhealth.controller.dto.UsuarioDTO;
import com.br.myhealth.model.Usuario;
import com.br.myhealth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody Usuario usuario, UriComponentsBuilder uriBuilder) {

        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }


    @GetMapping
    public String hello() {
        return "hello";
    }


}

package com.br.myhealth.controller;

import com.br.myhealth.controller.dto.AgendamentoDTO;
import com.br.myhealth.controller.dto.UsuarioDTO;
import com.br.myhealth.controller.form.AgendamentoForm;
import com.br.myhealth.controller.form.UsuarioForm;
import com.br.myhealth.model.Agendamento;
import com.br.myhealth.repository.AgendamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("agendamentos")
public class AgendamentoController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AgendamentoRepository agendamentoRepository;


    @PostMapping
    public ResponseEntity<AgendamentoDTO> cadastrar(@RequestBody @Valid AgendamentoForm agendamentoForm, UriComponentsBuilder uriBuilder) {
        Agendamento agendamento = agendamentoRepository.save(convertToAgendamento(agendamentoForm));
        URI uri = uriBuilder.path("/agendamentos/{id}").buildAndExpand(agendamento.getId()).toUri();
        return ResponseEntity.created(uri).body(convertToAgendamentoDTO(agendamento));
    }

    @GetMapping
    public List<AgendamentoDTO> listAll() {
        return toListAgendamentoDTO(this.agendamentoRepository.findAll());
    }

    @PutMapping
    public ResponseEntity<AgendamentoDTO> editar(@RequestBody @Valid AgendamentoForm agendamentoForm, UriComponentsBuilder uriBuilder) {
        Agendamento agendamento = agendamentoRepository.save(convertToAgendamento(agendamentoForm));
        URI uri = uriBuilder.path("/agendamentos/{id}").buildAndExpand(agendamento.getId()).toUri();
        return ResponseEntity.created(uri).body(convertToAgendamentoDTO(agendamento));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAgendamento(@PathVariable Long id) {
        agendamentoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


    public Agendamento convertToAgendamento(AgendamentoForm agendamentoForm) {
        return modelMapper.map(agendamentoForm, Agendamento.class);
    }

    public AgendamentoDTO convertToAgendamentoDTO(Agendamento agendamento) {
        return modelMapper.map(agendamento, AgendamentoDTO.class);
    }

    private List<AgendamentoDTO> toListAgendamentoDTO(List<Agendamento> agendamentos) {
        return agendamentos.stream()
                .map(agendamento -> convertToAgendamentoDTO(agendamento))
                .collect(Collectors.toList());
    }


}

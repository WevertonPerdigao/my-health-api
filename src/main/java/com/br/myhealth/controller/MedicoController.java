package com.br.myhealth.controller;

import com.br.myhealth.controller.dto.AgendamentoDTO;
import com.br.myhealth.controller.dto.MedicoDTO;
import com.br.myhealth.controller.form.AgendamentoForm;
import com.br.myhealth.controller.form.MedicoForm;
import com.br.myhealth.model.Agendamento;
import com.br.myhealth.model.Medico;
import com.br.myhealth.repository.MedicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MedicoRepository medicoRepository;


    @PostMapping
    public ResponseEntity<MedicoDTO> cadastrar(@RequestBody @Valid MedicoForm medicoForm, UriComponentsBuilder uriBuilder) {
        Medico medico = medicoRepository.save(convertToMedico(medicoForm));
        URI uri = uriBuilder.path("/agendamentos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(convertToMedicoDTO(medico));
    }

    @GetMapping
    public List<Medico> listAll() {
        return medicoRepository.findAll();
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteMedico(@PathVariable Long id) {
        medicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


    public Medico convertToMedico(MedicoForm medicoForm) {
        return modelMapper.map(medicoForm, Medico.class);
    }

    public MedicoDTO convertToMedicoDTO(Medico medico) {
        return modelMapper.map(medico, MedicoDTO.class);
    }


}

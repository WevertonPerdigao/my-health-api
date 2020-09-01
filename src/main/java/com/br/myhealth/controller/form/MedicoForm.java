package com.br.myhealth.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicoForm {


    private Long id;
    @NotBlank
    private String especialidade;
    @NotBlank
    private String nome;
}

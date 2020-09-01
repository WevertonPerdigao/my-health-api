package com.br.myhealth.controller.form;

import com.br.myhealth.controller.input.MedicoInput;
import com.br.myhealth.controller.input.UsuarioInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoForm {

    private Long id;
    private MedicoInput medico;
    private UsuarioInput usuario;
    @NotNull
    private LocalDateTime data;
}

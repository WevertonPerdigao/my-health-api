package com.br.myhealth.repository;

import com.br.myhealth.model.Agendamento;
import com.br.myhealth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}

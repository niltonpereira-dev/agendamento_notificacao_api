package com.niltonpereira_dev.agendamento_notificacao_api.infrastructure.repositories;

import com.niltonpereira_dev.agendamento_notificacao_api.infrastructure.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}

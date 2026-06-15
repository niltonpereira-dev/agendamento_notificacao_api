package com.niltonpereira_dev.agendamento_notificacao_api.business.service;

import com.niltonpereira_dev.agendamento_notificacao_api.business.mapper.IAgendamentoMapper;
import com.niltonpereira_dev.agendamento_notificacao_api.controller.dto.in.AgendamentoDTO;
import com.niltonpereira_dev.agendamento_notificacao_api.controller.dto.out.AgendamentoDTOOut;
import com.niltonpereira_dev.agendamento_notificacao_api.infrastructure.exception.NotFoundException;
import com.niltonpereira_dev.agendamento_notificacao_api.infrastructure.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final IAgendamentoMapper agendamentoMapper;

    public AgendamentoDTOOut gravarAgendamento(AgendamentoDTO agendamentoDTO){
        return agendamentoMapper.ParaOut(repository.save(agendamentoMapper.paraEntity(agendamentoDTO)));
    }

    public AgendamentoDTOOut buscarAgendamentoPorId(Long id){
        return agendamentoMapper.ParaOut(repository.findById(id).orElseThrow(() -> new NotFoundException("Id não encontrado")));
    }
}

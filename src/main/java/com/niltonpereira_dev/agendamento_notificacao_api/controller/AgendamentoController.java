package com.niltonpereira_dev.agendamento_notificacao_api.controller;

import com.niltonpereira_dev.agendamento_notificacao_api.business.service.AgendamentoService;
import com.niltonpereira_dev.agendamento_notificacao_api.controller.dto.in.AgendamentoDTO;
import com.niltonpereira_dev.agendamento_notificacao_api.controller.dto.out.AgendamentoDTOOut;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoDTOOut> gravarAgendamneto(@RequestBody AgendamentoDTO agendamentoDTO){
    return ResponseEntity.ok(agendamentoService.gravarAgendamento(agendamentoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDTOOut> buscarAgendamentoPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(agendamentoService.buscarAgendamentoPorId(id));
    }
}

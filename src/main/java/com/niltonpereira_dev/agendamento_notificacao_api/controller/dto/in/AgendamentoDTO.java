package com.niltonpereira_dev.agendamento_notificacao_api.controller.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record AgendamentoDTO(
        String emailDestinatario,
        String telefoneDestinatario,
        String mensagem,

        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "dd-MM-yyyy HH:mm:ss"
        )
        LocalDateTime dataHoraEnvio
) {
}
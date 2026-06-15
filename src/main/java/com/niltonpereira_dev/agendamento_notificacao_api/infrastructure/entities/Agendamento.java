package com.niltonpereira_dev.agendamento_notificacao_api.infrastructure.entities;

import com.niltonpereira_dev.agendamento_notificacao_api.infrastructure.enums.StatusNotificacaoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "agendamento")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailDestinatario;
    private String telefoneDestinatario;
    private LocalDateTime dataHoraEnvio;
    private LocalDateTime dataHoraAgendamento;
    private LocalDateTime dataHoraModificacao;
    private String mensagem;
    private StatusNotificacaoEnum statusNotificacaoEnum;

    @PrePersist
    private void prePersist(){
        dataHoraAgendamento= LocalDateTime.now();
        statusNotificacaoEnum = StatusNotificacaoEnum.AGENDADO;
    }
}

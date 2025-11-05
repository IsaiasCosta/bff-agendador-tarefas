package com.isaiascosta.bffagendadortarefas.business;

import com.isaiascosta.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.isaiascosta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.isaiascosta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class CronService {

   private final TarefasService tarefasService;
   private final EmailService emailService;
   private final UsuarioService usuarioService;

   @Value("${usuario.email}")
   private String email;
   @Value("${usuario.senha}")
   private String senha;

   @Scheduled(cron = "${cron.horario}")
   public void buscaTarefasProximaHora() {
      String token = login(converterParaRequestDTO());
      log.info("Busca de tarefas iniciadas");
      LocalDateTime horaAtual = LocalDateTime.now();
      LocalDateTime horaFutura = horaAtual.plusHours(1).plusMinutes(5);
      List<TarefasDTOResponse> listaTarefas = tarefasService.buscarTarefasAgendadasPorPeriodo(
              horaAtual, horaFutura, token);
      log.info("Tarefas encontradas " + listaTarefas);

      listaTarefas.forEach(tarefa -> {
         emailService.enviaEmail(tarefa);
         log.info("Email enviado ao usuario " + tarefa.getEmailUsuario());
         tarefasService.alterarStatusTarefa(StatusNotificacaoEnum.NOTIFICADO,
                 tarefa.getId(), token);
      });
      log.info("Busca e notifocação de tarefas encerradas");
   }

   public String login(LoginRequestDTO dto) {
      return usuarioService.loginUsuario(dto);
   }

   public LoginRequestDTO converterParaRequestDTO() {
      return LoginRequestDTO.builder()
              .email(email)
              .senha(senha)
              .build();
   }
}

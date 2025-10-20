package com.isaiascosta.bffagendadortarefas.business;


import com.isaiascosta.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.isaiascosta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.isaiascosta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.isaiascosta.bffagendadortarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefasService {
   private final TarefasClient client;

   public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto) {
      return client.gravarTarefas(dto, token);

   }

   public List<TarefasDTOResponse> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                    LocalDateTime dataFinal,
                                                                    String token) {
      return client.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
   }

   public List<TarefasDTOResponse> buscarTarefasPorEmail(String token) {

      return client.buscarTarefasPorEmail(token);
   }

   public void excluirTarefaPorId(String id, String token) {
      client.excluirTarefaPorId(id, token);
   }

   public TarefasDTOResponse alterarStatusTarefa(StatusNotificacaoEnum status, String id, String token) {
      return client.alterarStatusTarefa(status, id, token);
   }

   public TarefasDTOResponse updateTarefasDTO(TarefasDTORequest dto, String id, String token) {
      return client.updateTarefasDTO(dto, id, token);
   }
}
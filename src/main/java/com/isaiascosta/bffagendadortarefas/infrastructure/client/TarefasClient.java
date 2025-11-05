package com.isaiascosta.bffagendadortarefas.infrastructure.client;


import com.isaiascosta.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.isaiascosta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.isaiascosta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tarefas", url = "${tarefas.url}")
// Define um cliente Feign para comunicar com o microsserviço de usuário
public interface TarefasClient {

   @PostMapping
      // Mapeia requisições HTTP POST para este endpoint
   TarefasDTOResponse gravarTarefas(@RequestBody TarefasDTORequest dto,
                                    @RequestHeader("Authorization") String token);

   //Buscar dados em periodo de tempo usando o between
   @GetMapping("/eventos")
   List<TarefasDTOResponse> buscaListaDeTarefasPorPeriodo(@RequestParam
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                  LocalDateTime dataInicial,
                                                          @RequestParam
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                  LocalDateTime dataFinal,
                                                          @RequestHeader("Authorization")String token);

   //Buscar dados por email
   @GetMapping
   List<TarefasDTOResponse> buscarTarefasPorEmail(@RequestHeader("Authorization") String token);

   @DeleteMapping
   void excluirTarefaPorId(@RequestParam("id") String id,
                           @RequestHeader("Authorization") String token);


   @PatchMapping
   TarefasDTOResponse alterarStatusTarefa(@RequestParam("status") StatusNotificacaoEnum status,
                                          @RequestParam("id") String id,
                                          @RequestHeader("Authorization") String token);

   @PutMapping
   TarefasDTOResponse updateTarefasDTO(@RequestBody TarefasDTORequest dto,
                                       @RequestParam("id") String id,
                                       @RequestHeader("Authorization") String token);

}
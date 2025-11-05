package com.isaiascosta.bffagendadortarefas.controller;


import com.isaiascosta.bffagendadortarefas.business.TarefasService;
import com.isaiascosta.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.isaiascosta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.isaiascosta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;

import com.isaiascosta.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastrar tarefas do usuario")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {
   @Autowired
   private final TarefasService tarefasService;


   @PostMapping // Mapeia requisições HTTP POST para este endpoint
   @Operation(summary = "Cria Tarefas do usuario", description = "Cria uma tarefa do usuário")
   @ApiResponse(responseCode = "200", description = "Tarefa salvo com sucesso ")
   @ApiResponse(responseCode = "500", description = "Erro no servidor ")
   public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                           @RequestHeader(name = "Authorization", required = false)
                                                   String token) { // Corrigido: "Authorization"
      return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto)); // Chama o serviço para salvar a tarefa e retorna 200 OK com o DTO
   }

   //Buscar dados em periodo de tempo usando o between
   @GetMapping("/eventos")
   @Operation(summary = "Buscar Tarefas do usuario por periodo",
           description = "Busca uma tarefa cadastrada por periodo")
   @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso ")
   @ApiResponse(responseCode = "500", description = "Erro no servidor ")
   public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(@RequestParam
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                         LocalDateTime dataInicial,
                                                                                 @RequestParam
                                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                         LocalDateTime dataFinal,
                                                                                 @RequestHeader(name = "Authorization", required = false)
                                                                         String token) {
      return ResponseEntity.ok(tarefasService.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
   }

   //Buscar dados por email
   @GetMapping
   @Operation(summary = "Buscar Tarefas do usuario por email",
           description = "Busca uma tarefa cadastrada por email")
   @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso ")
   @ApiResponse(responseCode = "500", description = "Erro no servidor ")
   public ResponseEntity<List<TarefasDTOResponse>> buscarTarefasPorEmail(@RequestHeader(name = "Authorization", required = false)
                                                                 String token) {
      return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));

   }

   @DeleteMapping
   @Operation(summary = "Exclui Tarefas do usuario por Id",
           description = "Exclui uma tarefa cadastrada por Id")
   @ApiResponse(responseCode = "200", description = "Tarefa excluida com sucesso ")
   @ApiResponse(responseCode = "500", description = "Erro no servidor ")
   public ResponseEntity<Void> excluirTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false)
                                                  String token) {
      tarefasService.excluirTarefaPorId(id, token);
      return ResponseEntity.ok().build();

   }

   @PatchMapping
   @Operation(summary = "Atualiza status da tarefa do usuario",
           description = "Atualiza o status da tarefa ")
   @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso ")
   @ApiResponse(responseCode = "500", description = "Erro no servidor ")
   public ResponseEntity<TarefasDTOResponse> alterarStatusTarefa(@RequestParam("status") StatusNotificacaoEnum status,
                                                                 @RequestParam("id") String id,
                                                                 @RequestHeader(name = "Authorization", required = false)
                                                         String token) {
      return ResponseEntity.ok(tarefasService.alterarStatusTarefa(status, id, token));

   }

   @PutMapping
   @Operation(summary = "Atualiza  tarefa do usuario por Id",
           description = "Atualiza  tarefa por Id ")
   @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso ")
   @ApiResponse(responseCode = "500", description = "Erro no servidor ")
   public ResponseEntity<TarefasDTOResponse> updateTarefasDTO(@RequestBody TarefasDTORequest dto,
                                                              @RequestParam("id")
                                                      String id,
                                                              @RequestHeader(name = "Authorization", required = false)
                                                      String token) {
      return ResponseEntity.ok(tarefasService.updateTarefasDTO(dto, id, token));
   }
}

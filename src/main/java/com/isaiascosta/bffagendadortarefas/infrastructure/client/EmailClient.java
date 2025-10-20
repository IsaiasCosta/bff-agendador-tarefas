package com.isaiascosta.bffagendadortarefas.infrastructure.client;


import com.isaiascosta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
// Define um cliente Feign para comunicar com o microsserviço de usuário
public interface EmailClient {

  void enviarEmail(@RequestBody TarefasDTOResponse dto);
}


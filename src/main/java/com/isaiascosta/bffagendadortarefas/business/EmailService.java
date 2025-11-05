package com.isaiascosta.bffagendadortarefas.business;


import com.isaiascosta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.isaiascosta.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class EmailService {
   private final EmailClient emailClient;

   public void enviaEmail(TarefasDTOResponse dto) {
      emailClient.enviarEmail(dto);

   }
}
package com.isaiascosta.bffagendadortarefas.business.dto.in;

import lombok.*;

import java.util.List;

//Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTORequest {
   //DTO: use somente o que fornecessario par  aplicação

   private String nome;
   private String email;
   private String senha;
   private List<EnderecoDTORequest> enderecos;
   private List<TelefoneDTORequest> telefones;

}

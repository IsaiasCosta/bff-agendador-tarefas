package com.isaiascosta.bffagendadortarefas.business.dto.out;

import lombok.*;

import java.util.List;

//Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTOResponse {
   //DTO: use somente o que fornecessario par  aplicação

   private String nome;
   private String email;
   private String senha;
   private List<EnderecoDTOResponse> enderecos;
   private List<TelefoneDTOResponse> telefones;

}

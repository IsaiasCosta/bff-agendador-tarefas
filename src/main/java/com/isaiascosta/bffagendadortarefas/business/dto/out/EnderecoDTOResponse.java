package com.isaiascosta.bffagendadortarefas.business.dto.out;

import lombok.*;

//Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTOResponse {
   private String id;
   private String rua;
   private Long numero;
   private String complemento;
   private String cidade;
   private String estado;
   private String cep;

}

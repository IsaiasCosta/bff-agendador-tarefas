package com.isaiascosta.bffagendadortarefas.business.dto.in;

import lombok.*;

//Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTORequest {

   private String rua;
   private Long numero;
   private String complemento;
   private String cidade;
   private String estado;
   private String cep;

}

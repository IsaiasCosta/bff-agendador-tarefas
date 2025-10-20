package com.isaiascosta.bffagendadortarefas.business.dto.in;

import lombok.*;

//Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTORequest {
   private String id;
   private String numero;
   private String ddd;

}

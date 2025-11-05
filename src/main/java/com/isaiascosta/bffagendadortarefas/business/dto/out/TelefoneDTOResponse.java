package com.isaiascosta.bffagendadortarefas.business.dto.out;

import lombok.*;

//Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTOResponse {
   private String id;
   private String numero;
   private String ddd;

}

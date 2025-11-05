package com.isaiascosta.bffagendadortarefas.business.dto.in;

import lombok.*;

//Lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDTO {
   private String email;
   private String senha;
}

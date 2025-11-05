package com.isaiascosta.bffagendadortarefas.infrastructure.client;


import com.isaiascosta.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.isaiascosta.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.isaiascosta.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.isaiascosta.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.isaiascosta.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.isaiascosta.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.isaiascosta.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.isaiascosta.bffagendadortarefas.business.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
// Define um cliente Feign para comunicar com o microsserviço de usuário
public interface UsuarioClient {

   /* Faz uma requisição GET para o endpoint /usuario do serviço remoto
    Envia o e-mail como parâmetro na URL - Envia o token JWT no cabeçalho para autenticação */
   @GetMapping
   UsuarioDTOResponse buscarPorEmail(@RequestParam("email") String email,
                                    @RequestHeader("Authorization") String token);

   //Login do Usuario
   @PostMapping("/login")
   String login(@RequestBody LoginRequestDTO usuarioDTO);

   //Criando  o usuario no banco
   @PostMapping
   UsuarioDTOResponse salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

   //Buscar Por Email
   @GetMapping
   UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                           @RequestHeader("Authorization") String token);

//   //Busca Usuario por nome
//   @GetMapping("/por-nome")
//   public ResponseEntity<UsuarioDTO> buscarUsuarioPorNome(@RequestParam("nome") String nome,
//                                                          @RequestHeader("Authorization") String token) {
//      return ResponseEntity.ok(usuarioService.buscarUsuarioPorNome(nome, token));
//   }

//   //Deleta usuario  Por Id
//   @DeleteMapping("/id/{id}")
//   public ResponseEntity<Void> excluirPorId(@PathVariable Long id,
//                                            @RequestHeader("Authorization") String token) {
//      usuarioService.excluirPorId(id, token);
//      return ResponseEntity.ok().build();
//   }

   //Deleta usuario por email
   @DeleteMapping("{email}")
   void excluirPorEmail(@PathVariable String email,
                        @RequestHeader("Authorization") String token);

   //Atualizar o usuário logado pegando o e-mail que está dentro do token JWT.
   @PutMapping
   UsuarioDTOResponse atualizarUsuarioPorEmail(@RequestBody UsuarioDTORequest dto,
                                               @RequestHeader("Authorization") String token);

   @PutMapping("/endereco")
   EnderecoDTOResponse atualizarEnderecoPorId(@RequestBody EnderecoDTORequest enderecoDTO,
                                              @RequestParam("id") Long id,
                                              @RequestHeader("Authorization") String token);


   @PutMapping("/telefone")
   TelefoneDTOResponse atualizaTelefonePorID(@RequestBody TelefoneDTORequest telefoneDTO,
                                             @RequestParam("id") Long id,
                                             @RequestHeader("Authorization") String token);

   @GetMapping("/endereco/{cep}")
   ViaCepDTOResponse buscarEndereco(@PathVariable("cep") String cep);

}


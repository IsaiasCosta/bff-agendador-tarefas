package com.isaiascosta.bffagendadortarefas.controller;

import com.isaiascosta.bffagendadortarefas.business.UsuarioService;
import com.isaiascosta.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.isaiascosta.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.isaiascosta.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.isaiascosta.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.isaiascosta.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.isaiascosta.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.isaiascosta.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.isaiascosta.bffagendadortarefas.business.dto.out.ViaCepDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro e login de usuarios")

public class UsuarioController {

   private final UsuarioService usuarioService;

   //Criando  o usuario no banco
   @PostMapping
   @Operation(summary = "Salvar Usuarios", description = "Cria um novo usuário")
   @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso ")
   @ApiResponse(responseCode = "400", description = "Usuario já cadastrado ")
   @ApiResponse(responseCode = "500", description = "Erro no servidor ")
   public ResponseEntity<UsuarioDTOResponse> salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {
      return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
   }

   //Login do Usuario
   @PostMapping("/login")
   @Operation(summary = "Login de Usuarios", description = "Loga o usuário")
   @ApiResponse(responseCode = "200", description = "Usuario logado com sucesso ")
   @ApiResponse(responseCode = "401", description = "Credenciais Invalidas")
   @ApiResponse(responseCode = "500", description = "Erro no servidor ")
   public String loginUsuario(@RequestBody LoginRequestDTO usuarioDTO) {
      return usuarioService.loginUsuario(usuarioDTO);
   }

   //Buscar Por Email
   @GetMapping
   @Operation(summary = "Buscar dados do usuario por email ",
           description = "Buscar dados do usuário")
   @ApiResponse(responseCode = "200", description = "Usuario encontrado ")
   @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
   @ApiResponse(responseCode = "500", description = "Erro no servidor ")
   ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                            @RequestHeader(name = "Authorization", required = false)
                                                            String token) {
      return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
   }
//
//   //Busca Usuario por nome
//   @GetMapping("/por-nome")
//   public ResponseEntity<UsuarioDTO> buscarUsuarioPorNome(@RequestParam("nome") String nome,
//                                                         @RequestHeader(Name ="Authorization", Required =False) String token) {
//      return ResponseEntity.ok(usuarioService.buscarUsuarioPorNome(nome,token));
//   }

//   //Deleta usuario  Por Id
//   @DeleteMapping("/id/{id}")
//   public ResponseEntity<Void> excluirPorId(@PathVariable Long id,
//                                           @RequestHeader(Name ="Authorization", Required =False) String token) {
//      usuarioService.excluirPorId(id,token);
//      return ResponseEntity.ok().build();
//   }

   //Deleta usuario por email
   @DeleteMapping("{email}")
   @Operation(summary = "Excluir dados do usuario por email ",
           description = "Excluir dados do usuário")
   @ApiResponse(responseCode = "200", description = "Usuario excluido com sucesso")
   @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
   @ApiResponse(responseCode = "500", description = "Erro no servidor ")
   public ResponseEntity<Void> excluirPorEmail(@PathVariable String email,
                                               @RequestHeader(name = "Authorization", required = false)
                                               String token) {
      usuarioService.excluirPorEmail(email, token);
      return ResponseEntity.ok().build();
   }

   //Atualizar o usuário logado pegando o e-mail que está dentro do token JWT.
   @PutMapping
   @Operation(summary = "Atualizar dados do usuario por email ", description = "Atualizar dados do usuário")
   @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
   @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
   @ApiResponse(responseCode = "500", description = "Erro no servidor ")
   public ResponseEntity<UsuarioDTOResponse> atualizarUsuarioPorEmail(@RequestBody UsuarioDTORequest dto,
                                                                      @RequestHeader(name = "Authorization", required = false)
                                                                      String token) {
      return ResponseEntity.ok(usuarioService.atualizarUsuarioPorEmail(token, dto));
   }

   @PutMapping("/endereco")
   @Operation(summary = "Atualizar endereço do usuario ", description = "Atualizar endereço do usuário")
   @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
   @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
   @ApiResponse(responseCode = "500", description = "Erro no servidor ")
   public ResponseEntity<EnderecoDTOResponse> atualizarEnderecoPorId(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                     @RequestParam("id") Long id,
                                                                     @RequestHeader(name = "Authorization", required = false)
                                                                     String token) {
      return ResponseEntity.ok(usuarioService.atualizarEnderecoPorId(id, enderecoDTO, token));
   }

   @PutMapping("/telefone")
   @Operation(summary = "Atualizar telefone do usuario ", description = "Atualizar telefone do usuário")
   @ApiResponse(responseCode = "200", description = "telefone atualizado com sucesso")
   @ApiResponse(responseCode = "404", description = "telefone não encontrado")
   @ApiResponse(responseCode = "500", description = "Erro no servidor ")
   public ResponseEntity<TelefoneDTOResponse> atualizaTelefonePorID(@RequestBody TelefoneDTORequest telefoneDTO,
                                                                    @RequestParam("id") Long id,
                                                                    @RequestHeader(name = "Authorization", required = false)
                                                                    String token) {
      return ResponseEntity.ok(usuarioService.atualizaTelefonePorID(id, telefoneDTO, token));
   }

   @GetMapping("/endereco/{cep}")
   @Operation(summary = "Busca endereço por cepdo usuario ", description = "Busca endereço por cep")
   @ApiResponse(responseCode = "200", description = "endereço encontrado com sucesso")
   @ApiResponse(responseCode = "400", description = "CEP inválido ou não encontrado")
   @ApiResponse(responseCode = "500", description = "Erro no servidor ")
   public ResponseEntity<ViaCepDTOResponse> buscarEndereco(@PathVariable("cep") String cep) {
      return ResponseEntity.ok(usuarioService.buscarEndereco(cep));
   }
}

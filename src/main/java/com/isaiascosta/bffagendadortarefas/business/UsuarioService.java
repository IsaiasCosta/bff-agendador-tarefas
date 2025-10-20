package com.isaiascosta.bffagendadortarefas.business;


import com.isaiascosta.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.isaiascosta.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.isaiascosta.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.isaiascosta.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.isaiascosta.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.isaiascosta.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.isaiascosta.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.isaiascosta.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioService {

   //injeção de dependência
   private final UsuarioClient client;

   // Regra de negócio: antes de salvar o usuário, verificar se o e-mail já está registrado no sistema.
   public UsuarioDTOResponse salvarUsuario(UsuarioDTORequest usuarioDTO) {
      return client.salvarUsuario(usuarioDTO);

   }
   public String loginUsuario(LoginRequestDTO usuarioDTO){
    return client.login(usuarioDTO);

   }

   //Buscar usuario por nome
   public UsuarioDTOResponse buscarUsuarioPorEmail(String nome, String token) {
      return client.buscarUsuarioPorEmail(nome, token);
   }

//   //Deleta usuario  Por Id
//   public void excluirPorId(Long id) {
//
//      if (!usuarioRepository.existsById(id)) {
//         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado !");
//      }
//      usuarioRepository.deleteById(id);
//   }

   //Excluir Por email
   public void excluirPorEmail(String email, String token) {
      client.excluirPorEmail(email, token);
   }

   //Regra Atualizar dados do usuario - extrair o email atravez do token
   public UsuarioDTOResponse atualizarUsuarioPorEmail(String token, UsuarioDTORequest dto) {
       return client.atualizarUsuarioPorEmail(dto, token);
   }

   // Atualiza o endereço por ID
   public EnderecoDTOResponse atualizarEnderecoPorId(Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {
      return client.atualizarEnderecoPorId(enderecoDTO,idEndereco,token);
   }

   // Atualiza o telefone por ID
   public TelefoneDTOResponse atualizaTelefonePorID(Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {
      return client.atualizaTelefonePorID(telefoneDTO,idTelefone,token);

   }
}

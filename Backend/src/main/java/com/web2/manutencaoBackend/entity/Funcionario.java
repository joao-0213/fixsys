package com.web2.manutencaoBackend.entity;

import java.time.LocalDateTime;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data; 
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("FUNCIONARIO")
@Data
@NoArgsConstructor
public class Funcionario extends User{
    
    private String nome;
    private String email;
    
    private LocalDateTime dataNascimento; 

    public Funcionario(Long id, String password, UserRole role, String nome, String email, LocalDateTime dataNascimento, LocalDateTime dataRegistro) {
        super(email, password, role, dataRegistro);
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }



//    public FuncionarioRequestDTO getRequestDTO() {
//        FuncionarioRequestDTO dto = new FuncionarioRequestDTO();
//        dto.setNome(this.nome);
//        dto.setEmail(this.email);
//        return dto;
//    }

  //  public FuncionarioResponseDTO getResponseDTO() {
  //      FuncionarioResponseDTO dto = new FuncionarioResponseDTO();
  //      dto.setNome(this.nome);
   //     dto.setEmail(this.email);
  //      return dto;
  //  }
    

}
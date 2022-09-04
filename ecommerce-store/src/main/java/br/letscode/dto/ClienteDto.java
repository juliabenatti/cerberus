package br.letscode.dto;

import br.letscode.models.PerfilEnum;
import lombok.Data;
@Data
public class ClienteDto {

    private long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String sexo;
    private String cpf;
    private String senha;
    private PerfilEnum perfil;

    public ClienteDto(String nome, String sobrenome, String email, String sexo, String cpf){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.sexo = sexo;
        this.cpf = cpf;
    }


    public ClienteDto() {}

}

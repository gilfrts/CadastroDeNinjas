package com.example.CadastroDeNinja.Ninjas;

import com.example.CadastroDeNinja.Missões.MissoesModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//Entity transforma uma classe em uma entidade do banco de dados
@Entity
@Table(name = "tb_cadastro")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "idade")
    private int idade;

    @Column(name = "rank")
    private String rank;

    @ManyToOne
    @JoinColumn(name = "missoes_id") //Foreign Key (Chave Estrangeira)
    private MissoesModel missoes;

}

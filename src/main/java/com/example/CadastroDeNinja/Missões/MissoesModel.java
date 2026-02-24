package com.example.CadastroDeNinja.Missões;

import com.example.CadastroDeNinja.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private char dificuldade;
    @OneToMany(mappedBy = "missoes")
    private List<NinjaModel> ninjas;

}

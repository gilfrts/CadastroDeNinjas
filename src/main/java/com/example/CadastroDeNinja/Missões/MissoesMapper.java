package com.example.CadastroDeNinja.Missões;

import com.example.CadastroDeNinja.Ninjas.NinjaDTO;
import com.example.CadastroDeNinja.Ninjas.NinjaModel;
import org.springframework.stereotype.Component;

@Component
public class MissoesMapper {
    public MissoesModel map(MissoesDTO missoesDTO){
        MissoesModel missoesModel = new MissoesModel();
        missoesModel.setId(missoesDTO.getId());
        missoesModel.setNome(missoesDTO.getNome());
        missoesModel.setDificuldade(missoesDTO.getDificuldade());
        missoesModel.setNinjas(missoesDTO.getNinjas());

        return missoesModel;
    }

    public MissoesDTO map(MissoesModel missoesModel){
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setNome(missoesModel.getNome());
        missoesDTO.setDificuldade(missoesModel.getDificuldade());
        missoesDTO.setId(missoesModel.getId());
        missoesDTO.setNinjas(missoesModel.getNinjas());

        return missoesDTO;
    }
}

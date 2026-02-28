package com.example.CadastroDeNinja.Missões;

import com.example.CadastroDeNinja.Ninjas.NinjaModel;
import org.hibernate.sql.model.internal.OptionalTableInsert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    public List<MissoesModel> listarMissoes(){
        return missoesRepository.findAll();
    }

    public MissoesModel listarPorId(Long id){
        Optional<MissoesModel> missaoPorId = missoesRepository.findById(id);
        return missaoPorId.orElse(null);
    }

    public MissoesModel criarMissoes(MissoesModel missoes){
        return missoesRepository.save(missoes);
    }

    public void deletarMissoesPorId(Long id){
        missoesRepository.deleteById(id);
    }

    public MissoesModel atualizarMissoes(Long id, MissoesModel missaoAtualizada){
        if(missoesRepository.existsById(id)){
            missaoAtualizada.setId(id);
            return missoesRepository.save(missaoAtualizada);
        }
        return null;
    }
}

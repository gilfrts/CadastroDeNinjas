package com.example.CadastroDeNinja.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Listar todos meus ninjas
    public List<NinjaDTO> listarNinjas(){
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    // Listar ninja por ID
    public NinjaDTO listarNinjaPorId(Long id){
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }

    // Criar um novo ninja
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO){
        // Transformo o DTO em model;
        NinjaModel ninjaModel = ninjaMapper.map(ninjaDTO);
        // Salvo o model no banco de dados, agora ele gera ID
        ninjaModel = ninjaRepository.save(ninjaModel);
        // Uso o Mapper para transformar o model com ID gerado para DTO novamente, e assim o retorno
        return ninjaMapper.map(ninjaModel);
    }

    // Deletar um ninja - TEM QUE SER UM MÉTODO VOID
    public void deletarNinjaPorId(Long id){
        ninjaRepository.deleteById(id);
    }

    // Atualizar um ninja
    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO){
        // Verifico se existe o ninja pelo ID
        Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id);
        if(ninjaExistente.isPresent()) {
            // Se o ninja existir, transformo o DTO num model e seto seu ID
            NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO);
            ninjaAtualizado.setId(id);
            // Salvo no repository
            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado);
            // E faço a transformação para retornar um DTO novamente
            return ninjaMapper.map(ninjaSalvo);
        }
        return null;
    }
}

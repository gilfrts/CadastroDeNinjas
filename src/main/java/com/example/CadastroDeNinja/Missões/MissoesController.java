package com.example.CadastroDeNinja.Missões;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // GET: Requisição para mostrar missões
    @GetMapping("/listar")
    public List<MissoesModel> listarMissao(){
        return missoesService.listarMissoes();
    }

    @GetMapping("/listar/{id}")
    public MissoesModel listarPorId(@PathVariable Long id){
        return missoesService.listarPorId(id);
    }
    // POST: Requisição para criar missões
    @PostMapping("/criar")
    public MissoesModel criarMissao(@RequestBody MissoesModel missoesModel){
        return missoesService.criarMissoes(missoesModel);
    }

    //PUT: Requisição para alterar missões
    @PutMapping("/atualizar/{id}")
    public MissoesModel atualizarMissao(@PathVariable Long id, @RequestBody MissoesModel missaoAtualizada){
        return missoesService.atualizarMissoes(id, missaoAtualizada);
    }

    // DELETE: Requisição para deletar missões
    @DeleteMapping("/deletar/{id}")
    public void deletarMissao(@PathVariable Long id){
        missoesService.deletarMissoesPorId(id);
    }
}

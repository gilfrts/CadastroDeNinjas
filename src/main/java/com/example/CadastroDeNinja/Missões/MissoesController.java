package com.example.CadastroDeNinja.Missões;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
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
    public ResponseEntity<List<MissoesDTO>> listarMissao(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id){
        MissoesDTO missaoPorId = missoesService.listarPorId(id);
        if (missaoPorId != null){
            return ResponseEntity.ok(missaoPorId);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Missão de ID " + id + " não encontrada nos nossos registros.");
    }
    // POST: Requisição para criar missões
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoesDTO){
        MissoesDTO missaoCriada = missoesService.criarMissoes(missoesDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão criada com sucesso: " + missaoCriada.getNome());
    }

    //PUT: Requisição para alterar missões
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarMissao(@PathVariable Long id, @RequestBody MissoesDTO missaoAtualizada){
        if(missoesService.listarPorId(id) != null){
            missoesService.atualizarMissoes(id, missaoAtualizada);
            return ResponseEntity.ok(missaoAtualizada);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão de ID " + id + " não foi encontrada.");
    }

    // DELETE: Requisição para deletar missões
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id){
        if(missoesService.listarPorId(id) != null){
            missoesService.deletarMissoesPorId(id);
            return ResponseEntity.ok("Missão de ID " + id + " foi deletada com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("A missão com o ID " + id + " não foi encontrada.");
    }
}

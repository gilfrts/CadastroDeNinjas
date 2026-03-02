package com.example.CadastroDeNinja.Ninjas;

import com.example.CadastroDeNinja.CadastroDeNinjaApplication;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<?> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO ninjaCriado = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ninjaCriado);
    }

    // Mostrar ninjas (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> listaDeNinjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(listaDeNinjas);
    }

    // Mostrar ninjas por ID (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id){
        NinjaDTO ninjaPorId = ninjaService.listarNinjaPorId(id);
        if(ninjaPorId != null){
            return ResponseEntity.ok(ninjaPorId);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja com ID " + id + " não foi encontrado.");
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){
        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
        if(ninja != null){
            return ResponseEntity.ok("Ninja atualizado: " + ninja);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja de ID " + id + " não foi encontrado nos nossos registros");
    }
    // Deletar ninjas (DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){
        if(listarNinjasPorId(id) != null){
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja de ID " + id + " foi deletado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja de ID " + id + " não foi encontrado nos nossos registros");
    }
}
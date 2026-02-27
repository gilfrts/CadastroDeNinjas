package com.example.CadastroDeNinja.Missões;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    // GET: Requisição para mostrar missões
    @GetMapping("/listar")
    public String listarMissao(){
        return "Missão listada com sucesso";
    }

    // POST: Requisição para criar missões
    @PostMapping("/criar")
    public String criarMissao(){
        return "Missão criada com sucesso";
    }

    //PUT: Requisição para alterar missões
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missão alterada com sucesso";
    }

    // DELETE: Requisição para deletar missões
    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missão deletada com sucesso";
    }
}

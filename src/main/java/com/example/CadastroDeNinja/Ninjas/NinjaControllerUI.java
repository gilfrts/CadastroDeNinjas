package com.example.CadastroDeNinja.Ninjas;

import com.example.CadastroDeNinja.Missões.MissoesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUI {

    private final NinjaService ninjaService;
    private final MissoesService missoesService; // Variável no plural

    public NinjaControllerUI(MissoesService missoesService, NinjaService ninjaService) {
        this.missoesService = missoesService;
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> listaDeNinjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", listaDeNinjas);
        return "listarNinjas";
    }

    @GetMapping ("/deletar/{id}")
    public String deletarNinjaPorId(@PathVariable Long id){
        ninjaService.deletarNinjaPorId(id);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarNinjasPorId(@PathVariable Long id, Model model){
        NinjaDTO ninjaPorId = ninjaService.listarNinjaPorId(id);
        if(ninjaPorId != null){
            model.addAttribute("ninja", ninjaPorId);
            return "detalhesNinja";
        }
        model.addAttribute("mensagem", "Ninja não encontrado.");
        return "listarNinjas";
    }

    // ADICIONAR: Método único e com a lista de missões
    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        // Usando missoesService (plural) conforme declarado no topo
        model.addAttribute("missoes", missoesService.listarMissoes());
        return "adicionarNinja";
    }

    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {
        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja cadastrado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }

    // EDITAR: Agora também enviando a lista de missões
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjaPorId(id);
        model.addAttribute("ninja", ninja);
        model.addAttribute("missoes", missoesService.listarMissoes());
        return "editarNinja";
    }

    @PostMapping("/editar/{id}")
    public String atualizarNinja(@PathVariable Long id, @ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {
        ninjaService.atualizarNinja(id, ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja atualizado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }
}
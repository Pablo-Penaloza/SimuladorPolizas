package edu.uisrael.poliza.controller;

import edu.uisrael.poliza.dto.SimuladorDto;
import edu.uisrael.poliza.entity.Poliza;
import edu.uisrael.poliza.service.SimuladorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SimuladorController {

    private static final Logger log = LoggerFactory.getLogger(SimuladorController.class);

    private final SimuladorService simuladorService;

    public SimuladorController(SimuladorService simuladorService) {
        this.simuladorService = simuladorService;
    }

    @GetMapping
    public String index() {
        log.info("Retorna pagina: index");
        return "index";
    }

    @GetMapping("/simulador")
    public String simulador(Model model) {
    	
        log.info("Retorna pagina: simulador");
        
        model.addAttribute("polizas",new ArrayList<Poliza>());
        return "simulador";
    }

    @GetMapping("/reporte")
    public String reporte(Model model) {
    	
    	log.info("Retorna pagina: reporte");
    	model.addAttribute("polizas",simuladorService.findAll());
    	log.info("listado de polizas: {}",model.getAttribute("polizas"));
        return "reporte";
    }

    @GetMapping("/poliza/{dni}")
    @ResponseBody
    public ResponseEntity<?> reporte(@PathVariable String dni) {
        log.info("Retorna lista polizas por dni usuario: {}", dni);

        return ResponseEntity.ok(simuladorService.findByUser(dni));
    }

    @PostMapping("/simulador")
    @ResponseBody
    public ResponseEntity<?> simular(@Valid @ModelAttribute SimuladorDto data) {
        log.info("Petición post simulador:");
        log.info("Simulador data: {}", data);

        return ResponseEntity.ok(simuladorService.saveSimulator(data)) ;
    }

    @GetMapping("/usuario/{dni}")
    @ResponseBody
    public ResponseEntity<?> usuarioByCedula(@PathVariable String dni) {
        log.info("Retorna usuario por dni usuario: {}", dni);

        return ResponseEntity.ok(simuladorService.getUsuarioByCedula(dni));
    }
}

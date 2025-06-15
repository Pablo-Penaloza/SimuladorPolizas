package edu.uisrael.poliza.service;

import edu.uisrael.poliza.controller.SimuladorController;
import edu.uisrael.poliza.dto.SimuladorDto;
import edu.uisrael.poliza.entity.Poliza;
import edu.uisrael.poliza.entity.Usuario;
import edu.uisrael.poliza.repo.PolizaRepository;
import edu.uisrael.poliza.repo.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SimuladorService {
    private static final Logger log = LoggerFactory.getLogger(SimuladorController.class);
    private final PolizaRepository polizaRepository;
    private final UsuarioRepository usuarioRepository;

    public SimuladorService(PolizaRepository polizaRepository, UsuarioRepository usuarioRepository) {
        this.polizaRepository = polizaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Poliza> getAll() {
        return polizaRepository.findAll();
    }

    public Poliza getById(Integer id) {
        return polizaRepository.findById(id).get();
    }

    public Poliza save(Poliza poliza) {
        return polizaRepository.save(poliza);
    }

    public Poliza update(Poliza poliza) {
        return polizaRepository.save(poliza);
    }

    public void delete(Integer id) {
        polizaRepository.deleteById(id);
    }

    public List<Poliza> findByUser(String dni) {
        Optional<Usuario> user = usuarioRepository.findByCedula((dni).trim());
        if (!user.isPresent()) {
            return new ArrayList<>();
        }
        return polizaRepository.findByIdUsuario(user.get());
    }
    
    public List<Poliza> findAll() {
        
        return polizaRepository.findAll();
    }

    public List<Poliza> saveSimulator(SimuladorDto data) {
        Optional<Usuario> _user = usuarioRepository.findByCedula((data.getCedula()).trim());
        Usuario usuario = new Usuario();

        if (!_user.isPresent()) {
            usuario.setCedula(data.getCedula());
            usuario.setNombre(data.getNombre());
            usuario.setApellido(data.getApellido());
            usuario.setCorreo(data.getEmail());
            usuario = usuarioRepository.save(usuario);
        } else {
            usuario = _user.get();
        }

        Poliza poliza = new Poliza();
        poliza.setIdUsuario(usuario);
        poliza.setCapitali(data.getMonto());
        poliza.setCapitalizacion(data.getPeriodo());
        poliza.setTiempo(data.getAnios());
        poliza.setMontof(calcularMontoFinal(data));
        log.info("Poliza calculada :{}",calcularMontoFinal(data));
        poliza.setTasaInteres(data.getTasaInteres());
        polizaRepository.save(poliza);

        return this.findByUser(data.getCedula());
    }

    public SimuladorDto getUsuarioByCedula(String cedula) {
        SimuladorDto simuladorDto = new SimuladorDto();
        usuarioRepository.findByCedula((cedula).trim()).ifPresent(usuario -> {
            simuladorDto.setCedula(usuario.getCedula());
            simuladorDto.setNombre(usuario.getNombre());
            simuladorDto.setApellido(usuario.getApellido());
            simuladorDto.setEmail(usuario.getCorreo());
        });
        return simuladorDto;
    }

    private BigDecimal calcularMontoFinal(SimuladorDto poliza) {
        //M = P * (1 + r/n)^(n * t)
        BigDecimal r;
        int n = poliza.getPeriodo();
        int t = poliza.getAnios();

        BigDecimal p = poliza.getMonto();
        switch (n) {
            case 12:
                r = new BigDecimal(0.11);
                break;
            case 4:
                r = new BigDecimal(0.07);
                break;
            case 2:
                r = new BigDecimal(0.5);
                break;
            case 1:
                r = new BigDecimal(0.5);
                break;

            default:
                throw new IllegalArgumentException("Periodo no soportado");
        }
        poliza.setTasaInteres(r);
        int exponente = n * t;
        BigDecimal base = BigDecimal.ONE.add(r.divide(BigDecimal.valueOf(n)));
        BigDecimal aux = base.pow(exponente);
        BigDecimal montoFinal = poliza.getMonto().multiply(aux).setScale(2, RoundingMode.HALF_UP);

        return montoFinal;
    }
}

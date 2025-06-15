package edu.uisrael.poliza.repo;

import edu.uisrael.poliza.entity.Poliza;
import edu.uisrael.poliza.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolizaRepository extends JpaRepository<Poliza, Integer> {
  List<Poliza> findByIdUsuario(Usuario idUsuario);
}

package com.example.api.repository;

import com.example.api.model.UserModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author maxim
 */
 

public interface IUserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByCorreoElectronico(String correoElectronico);
    boolean existsByCorreoElectronicoAndContrasena(String correoElectronico, String contrasena);
      boolean existsByCorreoElectronico(String correoElectronico);
   

}

    


package org.software.user;

import org.software.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserService {
    private UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Servicio que me crea usuarios
     * @param user usuario a añadir en bd
     * @return usuario añadido
     */
    @PostMapping("/")
    public ResponseEntity<User> add(@RequestBody User user){
        ResponseEntity<User> response;
        try {
            response = new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        }catch (Exception e){
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * Servicio que obtiene los usuarios de la BD
     * @return Usuarios en BD
     */
    @GetMapping("/")
    public ResponseEntity<UserList> getAll(){
        ResponseEntity<UserList> response;
        UserList users = new UserList();
        try {
            users.setItems(userRepository.findAll());
            response = new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * Servicio que me actualiza el usuario
     * @param user datos del usuario que se van a actualizar
     * @param id id del usuario que se va a actualizar
     * @return usuario actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable long id){
        ResponseEntity<?> response;
        User prevUser;
        try {
            prevUser = userRepository.findById(id).orElse(null);
            if(prevUser != null){
                prevUser.updateAll(user);
                prevUser.setUpdatedAt(LocalDateTime.now());
                userRepository.save(prevUser);
            }
            response = new ResponseEntity<>(prevUser,HttpStatus.OK);
        }catch (Exception e){
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * Servicio que me elimina un usuario de BD
     * @param id id del usuario que se va eliminar
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        ResponseEntity<?> response;
        try{
            userRepository.deleteById(id);
            response = new ResponseEntity<>("se borró correctamente", HttpStatus.OK);
        }catch (Exception e){
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }



}

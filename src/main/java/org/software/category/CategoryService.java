package org.software.category;

import org.software.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/category")
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(@Autowired CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    /**
     * Servicio que me permite agregar una categoría
     * @param category Categoría a añadir
     * @return la categoría añadida
     */
    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody Category category) {
        ResponseEntity<?> response;
        try {
            response = new ResponseEntity<>(categoryRepository.save(category)
                    , HttpStatus.ACCEPTED);
        }catch (Exception e){
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * Servicio que obtiene todas las categorías
     * @return todas las categorías en BD
     */
    @GetMapping("/")
    public ResponseEntity<CategoryList> getAll(){
        ResponseEntity<CategoryList> response;
        CategoryList categoryList;
        try {
            categoryList = new CategoryList(categoryRepository.findAll());
            response = new ResponseEntity<>(categoryList,HttpStatus.OK);
        }catch (Exception e){
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * Servicio que me actualiza una categoría dado un id
     * @param category categoría nueva
     * @param id id de la categoría a actualizar
     * @return la categoría actualizada
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Category category, @PathVariable long id ){
        ResponseEntity<?> response;
        try {
            Category prevCategory = categoryRepository.findById(id).orElse(null);
            if(prevCategory != null){
                prevCategory.updateCategory(category);
                prevCategory.setUpdatedAt(LocalDateTime.now());
                categoryRepository.save(prevCategory);
            }
            response = new ResponseEntity<>(prevCategory, HttpStatus.OK);
        }catch (Exception e){
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    /**
     * Servicio que me elimina una categoría
     * @param id id de la categoría a eliminar
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        ResponseEntity<?> response;
        try {
            categoryRepository.deleteById(id);
            response = new ResponseEntity<>("se borró correctamente",HttpStatus.OK);
        } catch (Exception e){
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }



}

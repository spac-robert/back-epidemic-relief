package ro.robert.epidemicrelief.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.robert.epidemicrelief.dto.ProductDTO;
import ro.robert.epidemicrelief.facade.ProductFacade;
import ro.robert.epidemicrelief.model.Media;
import ro.robert.epidemicrelief.repository.MediaRepository;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import static ro.robert.epidemicrelief.utils.AppConstants.*;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductFacade productFacade;
    private final MediaRepository repository;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return ResponseEntity.ok().body(productFacade.getProducts(pageSize, pageNo, sortBy, sortDir));
    }

    @PostMapping(value = "/add")
    public void addProduct(@RequestBody ProductDTO productDTO) {
        productFacade.addProduct(productDTO);
    }

    @PutMapping(value = "/update")
    public void updateProduct(@RequestBody ProductDTO productDTO) {
        productFacade.updateProduct(productDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void removeProduct(@PathVariable("id") Integer id) {
        productFacade.deleteProduct(id);
    }

    //TODO SIUUU
    @PostMapping("/upload")
    public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException, SQLException {
        Media img = new Media(file.getOriginalFilename(), new SerialBlob(file.getBytes()));
        repository.save(img);
    }

}

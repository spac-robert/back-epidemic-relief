package ro.robert.epidemicrelief.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.robert.epidemicrelief.dto.ProductDTO;
import ro.robert.epidemicrelief.facade.ProductFacade;

import java.util.List;

import static ro.robert.epidemicrelief.utils.AppConstants.*;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductFacade productFacade;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        //TODO DA EROARE CU MEDIA
        return ResponseEntity.ok().body(productFacade.getProducts(pageSize, pageNo, sortBy, sortDir));
    }

    @PostMapping(value = "/add")
    public void addProduct(@ModelAttribute ProductDTO productDTO) {
        productFacade.addProduct(productDTO);
    }

//    @PutMapping(value = "/update")
//    public void updateProduct(@RequestBody ProductDTO productDTO) {
//        productFacade.updateProduct(productDTO);
//    }
//
//    @DeleteMapping(value = "/delete/{id}")
//    public void removeProduct(@PathVariable("id") Integer id) {
//        productFacade.deleteProduct(id);
//    }

//    @PostMapping("/upload")
//    public ResponseEntity.BodyBuilder uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
//        System.out.println("Original Image Byte Size - " + file.getBytes().length);
//        Media img = new Media(file.getOriginalFilename(), file.getBytes(), file.getContentType());
//        repository.save(img);
//        return ResponseEntity.status(HttpStatus.OK);
//    }

//    //TODO asta merge sa iau media img
//    @GetMapping(path = {"/get/{file}"})
//    public Media getImage(@PathVariable("file") String imageName) throws IOException {
//        final Optional<Media> retrievedImage = repository.findByName(imageName);
//        Media img = new Media();
//        if (retrievedImage.isPresent()) {
//            img = new Media(retrievedImage.get().getName(),
//                    retrievedImage.get().getData(), retrievedImage.get().getType());
//        }
//        return img;
//    }
}


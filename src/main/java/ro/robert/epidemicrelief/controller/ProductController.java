package ro.robert.epidemicrelief.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.robert.epidemicrelief.dto.LotDTO;
import ro.robert.epidemicrelief.dto.ProductDTO;
import ro.robert.epidemicrelief.facade.ProductFacade;

import static ro.robert.epidemicrelief.utils.AppConstants.*;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductFacade productFacade;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getProducts(
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return ResponseEntity.ok().body(productFacade.getProducts(pageSize, pageNo, sortBy, sortDir));
    }

    @PostMapping(value = "/add")
    public void addProduct(@ModelAttribute ProductDTO productDTO) {
        productFacade.addProduct(productDTO);
    }

    @PostMapping(value = "/add/lot")
    public void addProductLot(@ModelAttribute LotDTO lotDTO) {
        //productFacade.addProductLot(lotDTO);
        //TODO de continuat flow-ul
        try {
            this.productFacade.addLot(lotDTO);
        } catch (Exception e) {

        }
        System.out.println(lotDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Integer id) {
        ProductDTO product;
        try {
            product = productFacade.getById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok().body(product);
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

}


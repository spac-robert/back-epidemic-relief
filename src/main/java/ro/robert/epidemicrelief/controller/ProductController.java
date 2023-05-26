package ro.robert.epidemicrelief.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.robert.epidemicrelief.dto.LotDTO;
import ro.robert.epidemicrelief.dto.ProductDTO;
import ro.robert.epidemicrelief.dto.response.ProductResponse;
import ro.robert.epidemicrelief.exception.ProductNotFoundException;
import ro.robert.epidemicrelief.facade.ProductFacade;

import java.util.List;
import java.util.Objects;

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
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(value = "searchQuery", defaultValue = DEFAULT_SEARCH_QUERY, required = false) String searchQuery
    ) {
        Page<ProductDTO> products = productFacade.searchProducts(searchQuery, sortBy, sortDir, pageSize, pageNo);
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productFacade.getProducts();
        return ResponseEntity.ok().body(products);
    }


    @PostMapping(value = "/add")
    public ResponseEntity<ProductResponse> addProduct(@ModelAttribute ProductDTO productDTO) {
        try {
            productFacade.addProduct(productDTO);
            return ResponseEntity.ok().body(new ProductResponse("Product was added"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ProductResponse("Product couldn't be added"));
        }
    }

    @PostMapping(value = "/add/lot")
    public ResponseEntity<ProductResponse> addProductLot(@ModelAttribute LotDTO lotDTO) {
        try {
            if (!Objects.equals(lotDTO.getProductId(), productFacade.getById(lotDTO.getProductId()).getId())) {
                return ResponseEntity.badRequest().body(new ProductResponse("Product with id:" + lotDTO.getProductId() + " doesn't exist"));
            }
            this.productFacade.addLot(lotDTO);
            return ResponseEntity.ok().body(new ProductResponse("Lot was added"));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(new ProductResponse(e.getMessage()));
        }
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

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam("query") String query) {
        return ResponseEntity.ok(this.productFacade.search(query));
    }

    @PutMapping(value = "/update")
    public void updateProduct(@ModelAttribute ProductDTO productDTO) {
        productFacade.updateProduct(productDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> removeProduct(@PathVariable("id") Integer id) {
        try {
            if (productFacade.deleteProduct(id)) {
                return ResponseEntity.ok().body("Product deleted");
            }
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return null;
    }

}


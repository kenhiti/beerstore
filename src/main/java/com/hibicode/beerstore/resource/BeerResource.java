package com.hibicode.beerstore.resource;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerResource {

    private final BeerService beerService;

    @Autowired
    public BeerResource(BeerService beerService){
        this.beerService = beerService;

    }

    @GetMapping
    public List<Beer> findAll(){
        return beerService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beer save(@Valid  @RequestBody Beer beer){
        return beerService.save(beer);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Beer beer){
        beer.setId(id);
        beerService.save(beer);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        beerService.remove(id);
    }

}

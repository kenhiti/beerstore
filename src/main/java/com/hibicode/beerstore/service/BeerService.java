package com.hibicode.beerstore.service;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.model.BeerType;
import com.hibicode.beerstore.repository.BeerRepository;
import com.hibicode.beerstore.service.exception.BeerAlreadyExistException;
import com.hibicode.beerstore.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerService {

    private final BeerRepository beerRepository;

    @Autowired
    public BeerService(BeerRepository beerRepository){
        this.beerRepository = beerRepository;
    }

    public List<Beer> findAll(){
        return beerRepository.findAll();
    }

    public Beer save(Beer beer) {
        verifyIfBeerExists(beer);
        return beerRepository.save(beer);
    }

    public void remove(Long id){
        final Optional<Beer> beerById = this.findById(id);
        final Beer beerToDelete = beerById.orElseThrow(EntityNotFoundException::new);
        beerRepository.delete(beerToDelete);
    }

    public Optional<Beer> findById(Long id){
        return beerRepository.findById(id);
    }

    private Optional<Beer> findByNameAndType(String name, BeerType type){
        return beerRepository.findByNameAndType(name, type);
    }

    private void verifyIfBeerExists(final Beer beer){
        Optional<Beer> beerByNameAndType = beerRepository.findByNameAndType(beer.getName(), beer.getType());

        if(beerByNameAndType.isPresent() && (beer.isNew() || isUpdatingToADifferentBeer(beer, beerByNameAndType))){
            throw new BeerAlreadyExistException();
        }
    }

    private boolean isUpdatingToADifferentBeer(Beer beer, Optional<Beer> beerByNameAndType){
        return beer.alreadyExist() && !beerByNameAndType.get().equals(beer);
    }
}

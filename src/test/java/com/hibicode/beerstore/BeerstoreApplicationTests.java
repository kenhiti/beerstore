package com.hibicode.beerstore;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.model.BeerType;
import com.hibicode.beerstore.repository.BeerRepository;
import com.hibicode.beerstore.service.BeerService;
import com.hibicode.beerstore.service.exception.BeerAlreadyExistException;
import com.hibicode.beerstore.service.exception.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeerstoreApplicationTests {

	@Mock
	private BeerRepository beerRepository;

	private BeerService beerService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		beerService = new BeerService(beerRepository);
	}

	@Test(expected = BeerAlreadyExistException.class)
	public void should_deny_creation_of_beer_that_exist(){

		final Beer beerInDatabase = new Beer(null, "Heineken", BeerType.PILSEN, BigDecimal.TEN);
		when(beerRepository.findByNameAndType("Heineken", BeerType.PILSEN)).thenReturn(Optional.of(beerInDatabase));


		final Beer newBeer = new Beer(null, "Heineken", BeerType.PILSEN, BigDecimal.TEN);
		beerService.save(newBeer);
	}

	@Test
	public void creation_of_beer_that_not_exist(){

		final Beer newBeer = new Beer(null, "Heineken", BeerType.PILSEN, BigDecimal.TEN);
		final Beer beerInDatabase = new Beer(10l, "Heineken", BeerType.PILSEN, BigDecimal.TEN);

		when(beerRepository.findByNameAndType("Heineken", BeerType.PILSEN)).thenReturn(Optional.empty());
		when(beerRepository.save(newBeer)).thenReturn(beerInDatabase);

		final Beer beerSaved = beerService.save(newBeer);

		assertThat(beerSaved.getId(), equalTo(10l));
		assertThat(beerSaved.getName(), equalTo("Heineken"));
		assertThat(beerSaved.getType(), equalTo(BeerType.PILSEN));
	}

	@Test
	public void should_update_beer_volume(){
		final Beer beerInDatabase = new Beer(10l, "Heineken", BeerType.PILSEN, new BigDecimal("600"));
		final Beer beerToUpdate = new Beer(10l, "Heineken", BeerType.PILSEN, new BigDecimal("500"));

		when(beerRepository.findByNameAndType("Heineken", BeerType.PILSEN)).thenReturn(Optional.of(beerInDatabase));
		when(beerRepository.save(beerToUpdate)).thenReturn(beerToUpdate);

		final Beer beerUpdated = beerService.save(beerToUpdate);

		assertThat(beerUpdated.getId(), equalTo(10l));
		assertThat(beerUpdated.getName(), equalTo("Heineken"));
		assertThat(beerUpdated.getType(), equalTo(BeerType.PILSEN));
		assertThat(beerUpdated.getVolume(), equalTo(new BigDecimal("500")));

	}

	@Test(expected = BeerAlreadyExistException.class)
	public void should_deny_update_of_an_existing_beer_that_already_exists(){
		final Beer beerInDatabase = new Beer(10l, "Heineken", BeerType.PILSEN, new BigDecimal("600"));
		final Beer beerToUpdate = new Beer(5l, "Heineken", BeerType.PILSEN, new BigDecimal("600"));

		when(beerRepository.findByNameAndType("Heineken", BeerType.PILSEN)).thenReturn(Optional.of(beerInDatabase));

		beerService.save(beerToUpdate);
	}

	@Test
	public void should_delete_beer_if_exists(){
		final Beer beerInDatabase = new Beer(10l, "Heineken", BeerType.PILSEN, new BigDecimal("600"));
		when(beerRepository.findById(10l)).thenReturn(Optional.of(beerInDatabase));
		Mockito.doNothing().when(beerRepository).delete(beerInDatabase);
		beerService.remove(10l);
		verify(beerRepository, times(1)).delete(beerInDatabase);
	}

	@Test(expected = EntityNotFoundException.class)
	public void should_deny_delete_beer_if_not_exists(){
		when(beerRepository.findById(10l)).thenReturn(Optional.empty());
		beerService.remove(10l);
	}

}

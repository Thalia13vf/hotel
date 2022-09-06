package br.com.hotel.test;


import br.com.hotel.entity.Hotel;
import br.com.hotel.entity.Quarto;
import br.com.hotel.repository.HotelRepository;
import br.com.hotel.repository.QuartoRepositoy;
import br.com.hotel.service.HotelService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

public class HotelServiceTest {

    @Mock
    private HotelService hotelService = new HotelService(new HotelRepository(), new QuartoRepositoy());

    @Test
    public void deveCriarHotelComSucessoAdicionandoNaLista() {
        hotelService.criarHotel(1L, "nome");
        List<Hotel> hoteis = hotelService.listarHoteis();
        Assert.assertTrue(hoteis.size() > 0);
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveCriarHotelComMesmoId() {
        hotelService.criarHotel(1L, "nome");
        hotelService.criarHotel(1L, "nome");
    }

    @Test
    public void deveCriarQuarto() {
        hotelService.criarHotel(900L, "hotel");
        hotelService.definirQuarto(900L, 1, "COMUM");

        Hotel hotel = hotelService.buscarHotelPorId(900L);
        Quarto quarto = hotel.getQuartos().get(0);

        Assert.assertNotNull(quarto);
        Assert.assertEquals(1,(int) quarto.getNumeroQuarto());
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveCriarQuartoEmHotelQueNaoExiste() {
        hotelService.definirQuarto(0L, 2, "COMUM");
        Assert.assertThrows("Não foi possível criar o quarto! Pois esse hotel não existe.", RuntimeException.class, () -> {});
    }

    @Test
    public void deveAlterarOQuarto() {
        hotelService.criarHotel(900L, "hotel");
        hotelService.definirQuarto(900L, 1, "COMUM");

        hotelService.definirQuarto(900L, 1, "TESTE");
        Hotel hotel = hotelService.buscarHotelPorId(900L);
        Quarto quarto = hotel.getQuartos().get(0);

        Assert.assertEquals(quarto.getTipoQuarto(), "TESTE");
    }


}

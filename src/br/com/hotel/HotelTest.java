package br.com.hotel;

import br.com.hotel.entity.Hotel;
import br.com.hotel.repository.HotelRepository;
import br.com.hotel.repository.QuartoRepositoy;
import br.com.hotel.service.HotelService;

public class HotelTest {
    public static void main(String[] args) {
        HotelService hotelService = new HotelService(new HotelRepository(), new QuartoRepositoy());

        hotelService.criarHotel(1L, "hotel1");
        hotelService.criarHotel(2L, "hotel2");

        hotelService.definirQuarto(1L, 1, "comum");
        hotelService.definirQuarto(1L, 2, "comum");
        hotelService.definirQuarto(2L, 1, "comum");
        hotelService.definirQuarto(2L, 5, "presidencial");

        System.out.println("Hotel" + hotelService.listarHoteis());



    }
}

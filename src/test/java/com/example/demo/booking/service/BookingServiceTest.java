package com.example.demo.booking.service;

import com.example.demo.balance.model.Balance;
import com.example.demo.balance.repository.BalanceRepository;
import com.example.demo.booking.dto.BookingDTO;
import com.example.demo.booking.exceptions.BookingNotFoundException;
import com.example.demo.booking.exceptions.NotEnoughMoneyToBookException;
import com.example.demo.booking.model.Booking;
import com.example.demo.booking.repository.BookingRepository;
import com.example.demo.classes.model.Classes;
import com.example.demo.classes.service.ClassesService;
import com.example.demo.client.model.Client;
import com.example.demo.client.service.ClientService;
import com.example.demo.utils.UtilsObjects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.demo.utils.UtilsObjects.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private ClassesService classesService;
    @Mock
    private ClientService clientService;
    @Mock
    private BalanceRepository balanceRepository;
    @InjectMocks
    private BookingService bookingService;

    private final Long ID = 1L;
    private final UtilsObjects utilsObjects = new UtilsObjects();

    @Test
    void getBookingByIdOk() {
        Booking booking = new Booking();
        booking.setId(ID);
        when(bookingRepository.findById(ID)).thenReturn(Optional.of(booking));
        Booking result = bookingService.getBookingById(ID);

        assertEquals(booking, result);
        verify(bookingRepository, times(1)).findById(eq(ID));
    }

    @Test
    void getBookingByIdThrowBookingNotFound() {
        assertThrows(BookingNotFoundException.class, () -> bookingService.getBookingById(ID));
    }

    @Test
    void addBookingOk() {
        Client client = utilsObjects.CLIENT;
        Classes classes = utilsObjects.CLASSES;
        BookingDTO bookingDTO = new BookingDTO(
                CLIENT_ID,
                CLASSES_ID
        );

        when(clientService.getClient(CLIENT_ID)).thenReturn(client);
        when(classesService.getClassById(CLASSES_ID)).thenReturn(classes);
        doNothing().when(balanceRepository).setNewBalanceById(anyLong(), anyFloat());
        Booking booking = new Booking(null, client, classes);
        when(bookingRepository.save(any())).thenReturn(booking);

        assertEquals(booking, bookingService.addBooking(bookingDTO));
        verify(bookingRepository, times(1)).save(any());
        verify(balanceRepository, times(2)).setNewBalanceById(anyLong(), anyFloat());
    }

    @Test
    void addBookingThrowNotEnoughMoney() {
        Client client = utilsObjects.CLIENT;
        Classes classes = utilsObjects.CLASSES;
        client.getBalance().setValue(50F);

        BookingDTO bookingDTO = new BookingDTO(
                CLIENT_ID,
                CLASSES_ID
        );

        when(clientService.getClient(CLIENT_ID)).thenReturn(client);
        when(classesService.getClassById(CLASSES_ID)).thenReturn(classes);

        assertThrows(NotEnoughMoneyToBookException.class, () -> bookingService.addBooking(bookingDTO));
    }

}

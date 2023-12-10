package com.example.demo.booking.service;

import com.example.demo.balance.model.Balance;
import com.example.demo.balance.repository.BalanceRepository;
import com.example.demo.booking.dto.BookingDTO;
import com.example.demo.booking.exceptions.NotEnoughMoneyToBookException;
import com.example.demo.booking.model.Booking;
import com.example.demo.booking.repository.BookingRepository;
import com.example.demo.classes.model.Classes;
import com.example.demo.classes.service.ClassesService;
import com.example.demo.client.model.Client;
import com.example.demo.client.service.ClientService;
import com.example.demo.studio.model.Position;
import com.example.demo.studio.model.Studio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import static com.example.demo.utils.UtilsObjects.STUDIO_BALANCE;
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
    private final Long CLIENT_ID = 1L;
    private final Long CLASS_ID = 1L;
    private final Date TOMORROW = new Date(new java.util.Date().getTime() + 86400000);
    private final Time CLASS_START = Time.valueOf("10:00:00");
    private final Time CLASS_END = Time.valueOf("12:00:00");
    private final Studio STUDIO = new Studio(
            null,
            null,
            null,
            null,
            null,
            STUDIO_BALANCE
    );
    private final Position POS = new Position(
            null,
            null,
            null,
            STUDIO,
            null
    );
    private final Classes CLASS1 = new Classes(
            ID,
            "CLASS_NAME",
            TOMORROW,
            CLASS_START,
            CLASS_END,
            100,
            POS,
            null
    );
    private final Client CLIENT = new Client();

    @Test
    void getBookingById() {
        Booking booking = new Booking();
        booking.setId(ID);
        when(bookingRepository.findById(ID)).thenReturn(Optional.of(booking));
        Booking result = bookingService.getBookingById(ID);

        assertEquals(booking, result);
        verify(bookingRepository, times(1)).findById(eq(ID));
    }

    @Test
    void addBookingOk() {
        Balance clientBalance = new Balance();
        clientBalance.setId(ID);
        clientBalance.setValue(200F);
        CLIENT.setId(CLIENT_ID);
        CLIENT.setBalance(clientBalance);

        BookingDTO bookingDTO = new BookingDTO(
                CLIENT_ID,
                CLASS_ID
        );

        when(clientService.getClient(CLIENT_ID)).thenReturn(CLIENT);
        when(classesService.getClassById(CLASS_ID)).thenReturn(CLASS1);
        doNothing().when(balanceRepository).setNewBalanceById(anyLong(), anyFloat());
        Booking booking = new Booking(null, CLIENT, CLASS1);
        when(bookingRepository.save(any())).thenReturn(booking);

        assertEquals(booking, bookingService.addBooking(bookingDTO));
        verify(bookingRepository, times(1)).save(any());
        verify(balanceRepository, times(2)).setNewBalanceById(anyLong(), anyFloat());
    }

    @Test
    void addBookingThrowNotEnoughMoney() {
        Balance clientBalance = new Balance();
        clientBalance.setId(ID);
        clientBalance.setValue(50F);
        CLIENT.setId(CLIENT_ID);
        CLIENT.setBalance(clientBalance);

        BookingDTO bookingDTO = new BookingDTO(
                CLIENT_ID,
                CLASS_ID
        );

        when(clientService.getClient(CLIENT_ID)).thenReturn(CLIENT);
        when(classesService.getClassById(CLASS_ID)).thenReturn(CLASS1);

        assertThrows(NotEnoughMoneyToBookException.class, () -> bookingService.addBooking(bookingDTO));
    }

}

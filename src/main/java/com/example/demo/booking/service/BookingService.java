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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private ClassesService classesService;
    private ClientService clientService;
    private BalanceRepository balanceRepository;
    @Autowired
    public BookingService(
            BookingRepository bookingRepository,
            ClassesService classesService,
            ClientService clientService,
            BalanceRepository balanceRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.classesService = classesService;
        this.clientService = clientService;
        this.balanceRepository = balanceRepository;
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
    }

    public Booking addBooking(BookingDTO bookingDTO)  {
        Client client = clientService.getClient(bookingDTO.getClientId());
        Classes classes = classesService.getClassById(bookingDTO.getClassId());

        if (client.getBalance().getValue() < classes.getAmount())
            throw new NotEnoughMoneyToBookException();

        Balance balance = client.getBalance();
        balanceRepository.setNewBalanceById(balance.getId(), balance.getValue() - classes.getAmount());
        Balance balanceStudio = classes.getPosition().getStudio().getBalance();
        balanceRepository.setNewBalanceById(balanceStudio.getId(), balanceStudio.getValue() + classes.getAmount());

        return bookingRepository.save(new Booking(bookingRepository.count() + 1, client, classes));
    }
}

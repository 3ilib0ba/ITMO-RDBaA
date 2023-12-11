package com.example.demo.client.service;

import com.example.demo.balance.repository.BalanceRepository;
import com.example.demo.booking.exceptions.BookingNotFoundException;
import com.example.demo.booking.exceptions.NotEnoughMoneyToBookException;
import com.example.demo.classifiers.model.Classifier;
import com.example.demo.client.dto.ClientDTO;
import com.example.demo.client.exceptions.ClientNotFoundException;
import com.example.demo.client.exceptions.RoleNotFoundException;
import com.example.demo.client.model.Client;
import com.example.demo.client.model.Role;
import com.example.demo.client.repository.ClientRepository;
import com.example.demo.client.repository.RoleRepository;
import com.example.demo.common.exceptions.MailIsAlreadyExistException;
import com.example.demo.common.exceptions.PhoneIsAlreadyExistException;
import com.example.demo.config.RoleConfig;
import com.example.demo.utils.UtilsObjects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.demo.utils.RoleObjects.USER_ROLE;
import static com.example.demo.utils.UtilsObjects.CLIENT_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private BalanceRepository balanceRepository;
    @InjectMocks
    private ClientService clientService;
    private final UtilsObjects utilsObjects = new UtilsObjects();

    @Test
    void getClientOk() {
        Client client = utilsObjects.CLIENT;
        when(clientRepository.findById(CLIENT_ID)).thenReturn(Optional.of(client));

        Client result = clientService.getClient(CLIENT_ID);
        assertEquals(client, result);
        verify(clientRepository, times(1)).findById(eq(CLIENT_ID));
    }

    @Test
    void getClientClientNotFound() {
        assertThrows(ClientNotFoundException.class, () -> clientService.getClient(CLIENT_ID));
    }

    @Test
    void addUserOk() {
        Client client = utilsObjects.CLIENT;

        ClientDTO clientDTO = new ClientDTO(
                client.getName(),
                client.getMail(),
                client.getPhone()
        );
        clientDTO.setGender(client.getGender());

        when(clientRepository.findByMail(client.getMail())).thenReturn(null);
        when(clientRepository.findByPhone(client.getPhone())).thenReturn(null);
        when(roleRepository.getRoleByRoleIgnoreCase(RoleConfig.ROLE_USER.toString())).thenReturn(USER_ROLE);
        when(balanceRepository.count()).thenReturn(0L);
        when(balanceRepository.save(any())).thenReturn(utilsObjects.CLIENT_BALANCE);
        when(clientRepository.save(any())).thenReturn(client);

        assertEquals(client, clientService.addUser(clientDTO));
        verify(balanceRepository, times(1)).save(any());
        verify(clientRepository, times(1)).save(any());
        verify(balanceRepository, times(2)).count();
        verify(roleRepository, times(1)).getRoleByRoleIgnoreCase(anyString());
        verify(clientRepository, times(1)).findByMail(anyString());
        verify(clientRepository, times(1)).findByMail(anyString());
    }

    @Test
    void addUserMailIsAlreadyExist() {
        Client client = utilsObjects.CLIENT;

        ClientDTO clientDTO = new ClientDTO(
                client.getName(),
                client.getMail(),
                client.getPhone()
        );
        clientDTO.setGender(client.getGender());

        when(clientRepository.findByMail(client.getMail())).thenReturn(client);
        assertThrows(MailIsAlreadyExistException.class, () -> clientService.addUser(clientDTO));
    }

    @Test
    void addUserPhoneIsAlreadyExist() {
        Client client = utilsObjects.CLIENT;

        ClientDTO clientDTO = new ClientDTO(
                client.getName(),
                client.getMail(),
                client.getPhone()
        );
        clientDTO.setGender(client.getGender());

        when(clientRepository.findByMail(client.getMail())).thenReturn(null);
        when(clientRepository.findByPhone(client.getPhone())).thenReturn(client);
        assertThrows(PhoneIsAlreadyExistException.class, () -> clientService.addUser(clientDTO));
    }

    @Test
    void addUserRoleNotFound() {
        Client client = utilsObjects.CLIENT;

        ClientDTO clientDTO = new ClientDTO(
                client.getName(),
                client.getMail(),
                client.getPhone()
        );
        clientDTO.setGender(client.getGender());

        when(clientRepository.findByMail(client.getMail())).thenReturn(null);
        when(clientRepository.findByPhone(client.getPhone())).thenReturn(null);
        when(roleRepository.getRoleByRoleIgnoreCase(RoleConfig.ROLE_USER.toString())).thenReturn(null);
        assertThrows(RoleNotFoundException.class, () -> clientService.addUser(clientDTO));
    }

    @Test
    void getAllClients() {
        Client client1 = utilsObjects.CLIENT;

        Client client2 = utilsObjects.CLIENT;
        client2.setId(client1.getId() + 1);

        when(clientRepository.findAll()).thenReturn(List.of(client1, client2));

        List<Client> result = clientService.getAllClients();
        assertEquals(List.of(client1, client2), result);
        verify(clientRepository, times(1)).findAll();
    }
}

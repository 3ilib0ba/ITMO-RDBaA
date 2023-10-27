package com.example.demo.client.service;

import com.example.demo.balance.model.Balance;
import com.example.demo.balance.repository.BalanceRepository;
import com.example.demo.client.dto.ClientDTO;
import com.example.demo.client.exceptions.*;
import com.example.demo.client.model.Client;
import com.example.demo.client.model.Role;
import com.example.demo.client.repository.ClientRepository;
import com.example.demo.client.repository.RoleRepository;
import com.example.demo.common.exceptions.MailIsAlreadyExistException;
import com.example.demo.common.exceptions.PhoneIsAlreadyExistException;
import com.example.demo.config.RoleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private ClientRepository clientRepository;
    private RoleRepository roleRepository;
    private BalanceRepository balanceRepository;
    @Autowired
    public ClientService(
            ClientRepository clientRepository,
            RoleRepository roleRepository,
            BalanceRepository balanceRepository
    ) {
        this.clientRepository = clientRepository;
        this.roleRepository = roleRepository;
        this.balanceRepository = balanceRepository;
    }

    public Client getClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    public Client addUser(ClientDTO clientDTO) {
        Client clientMail = clientRepository.findByMail(clientDTO.getMail());
        if (clientMail != null)
            throw new MailIsAlreadyExistException(clientDTO.getMail());
        Client clientPhone = clientRepository.findByPhone(clientDTO.getPhone());
        if (clientPhone != null)
            throw new PhoneIsAlreadyExistException(clientDTO.getPhone());

        Role role = roleRepository.getRoleByRoleIgnoreCase(RoleConfig.ROLE_USER.toString());
        Client client = new Client(
                null,
                clientDTO.getName(),
                clientDTO.getMail(),
                clientDTO.getPhone(),
                clientDTO.getGender(),
                role,
                null
        );
        Balance balance = balanceRepository.save(
                new Balance(balanceRepository.count() + 1, 0f, role)
        );
        client.setBalance(balance);
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}

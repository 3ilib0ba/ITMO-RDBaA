package com.example.demo.studio.service;

import com.example.demo.balance.model.Balance;
import com.example.demo.balance.repository.BalanceRepository;
import com.example.demo.client.exceptions.RoleNotFoundException;
import com.example.demo.common.exceptions.MailIsAlreadyExistException;
import com.example.demo.client.model.Role;
import com.example.demo.client.repository.RoleRepository;
import com.example.demo.config.RoleConfig;
import com.example.demo.studio.dto.StudioDTO;
import com.example.demo.studio.exceptions.StudioNotFoundException;
import com.example.demo.studio.exceptions.TinIsAlreadyExistException;
import com.example.demo.studio.model.LegalInfo;
import com.example.demo.studio.model.Studio;
import com.example.demo.studio.repository.LegalInfoRepository;
import com.example.demo.studio.repository.PositionRepository;
import com.example.demo.studio.repository.StudioRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudioService {
    private LegalInfoRepository legalInfoRepository;
    private BalanceRepository balanceRepository;
    private RoleRepository roleRepository;
    private StudioRepository studioRepository;
    @Autowired
    public StudioService(
            LegalInfoRepository legalInfoRepository,
            BalanceRepository balanceRepository,
            RoleRepository roleRepository,
            StudioRepository studioRepository
    ) {
        this.legalInfoRepository = legalInfoRepository;
        this.balanceRepository = balanceRepository;
        this.roleRepository = roleRepository;
        this.studioRepository = studioRepository;
    }

    @Transactional
    public LegalInfo addLegalInfo(
            String fullDescription,
            @Nullable
            String phone,
            String mail,
            String TIN
    ) {
        LegalInfo legalInfo = new LegalInfo(
                legalInfoRepository.count() + 1,
                fullDescription,
                phone == null ? "" : phone,
                mail,
                TIN
        );
        return legalInfoRepository.save(legalInfo);
    }

    public Studio getStudioById(Long studioId) {
        return studioRepository.findById(studioId).orElseThrow(() -> new StudioNotFoundException(studioId));
    }

    public Studio getStudioByName(String studioName) {
        Studio studio = studioRepository.findByNameIgnoreCase(studioName);
        if (studio == null)
            throw new StudioNotFoundException(studioName);
        return studio;
    }

    @Transactional
    public Studio addStudioAndLegalInfo(StudioDTO studioDTO) {
        LegalInfo legalInfoMail = legalInfoRepository.findByMail(studioDTO.getMail());
        if (legalInfoMail != null)
            throw new MailIsAlreadyExistException(studioDTO.getMail());
        LegalInfo legalInfoPhone = legalInfoRepository.findByTIN(studioDTO.getTin());
        if (legalInfoPhone != null)
            throw new TinIsAlreadyExistException(studioDTO.getTin());

        Role role = roleRepository.getRoleByRoleIgnoreCase(RoleConfig.ROLE_MANAGER.toString());
        if (role == null) {
            throw new RoleNotFoundException(RoleConfig.ROLE_MANAGER.toString());
        }
        LegalInfo legalInfo = addLegalInfo(
                studioDTO.getFullDescription(),
                studioDTO.getPhone(),
                studioDTO.getMail(),
                studioDTO.getTin());
        Balance balance = new Balance(
                balanceRepository.count() + 1,
                0f,
                role
        );
        balance = balanceRepository.save(balance);
        Studio studio = new Studio(
                null,
                studioDTO.getName(),
                studioDTO.getDescription(),
                role,
                legalInfo,
                balance
        );
        return studioRepository.save(studio);
    }

    public List<Studio> getAllStudios() {
        return studioRepository.findAll();
    }

}

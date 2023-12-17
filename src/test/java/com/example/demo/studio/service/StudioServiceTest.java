package com.example.demo.studio.service;

import com.example.demo.balance.model.Balance;
import com.example.demo.balance.repository.BalanceRepository;
import com.example.demo.client.exceptions.RoleNotFoundException;
import com.example.demo.client.repository.RoleRepository;
import com.example.demo.common.exceptions.MailIsAlreadyExistException;
import com.example.demo.studio.dto.StudioDTO;
import com.example.demo.studio.exceptions.StudioNotFoundException;
import com.example.demo.studio.exceptions.TinIsAlreadyExistException;
import com.example.demo.studio.model.LegalInfo;
import com.example.demo.studio.model.Studio;
import com.example.demo.studio.repository.LegalInfoRepository;
import com.example.demo.studio.repository.StudioRepository;
import com.example.demo.utils.UtilsObjects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.demo.utils.RoleObjects.MANAGER_ROLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudioServiceTest {
    @Mock
    private LegalInfoRepository legalInfoRepository;
    @Mock
    private BalanceRepository balanceRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private StudioRepository studioRepository;
    @InjectMocks
    private StudioService studioService;
    private final UtilsObjects utilsObjects = new UtilsObjects();
    @Test
    void addLegalInfo() {
        LegalInfo legalInfo = utilsObjects.LEGAL_INFO;
        when(legalInfoRepository.count()).thenReturn(1L);
        when(legalInfoRepository.save(any())).thenReturn(legalInfo);
        LegalInfo result = studioService.addLegalInfo(
                legalInfo.getFullDescription(),
                legalInfo.getPhone(),
                legalInfo.getMail(),
                legalInfo.getTIN()
        );

        assertEquals(legalInfo, result);
        verify(legalInfoRepository, times(1)).count();
        verify(legalInfoRepository, times(1)).save(any());
    }
    @Test
    void getStudioByIdOk() {
        Studio studio = utilsObjects.STUDIO;

        when(studioRepository.findById(studio.getId())).thenReturn(Optional.of(studio));
        Studio result = studioService.getStudioById(studio.getId());

        assertEquals(studio, result);
        verify(studioRepository, times(1)).findById(eq(studio.getId()));
    }
    @Test
    void getStudioByIdStudioNotFound() {
        Studio studio = utilsObjects.STUDIO;

        when(studioRepository.findById(studio.getId())).thenReturn(Optional.empty());
        assertThrows(StudioNotFoundException.class, () -> studioService.getStudioById(studio.getId()));
    }
    @Test
    void getStudioByNameOk() {
        Studio studio = utilsObjects.STUDIO;

        when(studioRepository.findByNameIgnoreCase(studio.getName())).thenReturn(studio);
        Studio result = studioService.getStudioByName(studio.getName());

        assertEquals(studio, result);
        verify(studioRepository, times(1)).findByNameIgnoreCase(studio.getName());
    }
    @Test
    void getStudioByNameStudioNotFound() {
        Studio studio = utilsObjects.STUDIO;

        when(studioRepository.findByNameIgnoreCase(studio.getName())).thenReturn(null);
        assertThrows(StudioNotFoundException.class, () -> studioService.getStudioByName(studio.getName()));
    }
    @Test
    void addStudioAndLegalInfoOk() {
        Studio studio = utilsObjects.STUDIO;
        LegalInfo legalInfo = utilsObjects.LEGAL_INFO;
        Balance balance = utilsObjects.STUDIO_BALANCE;

        StudioDTO studioDTO = new StudioDTO(
                legalInfo.getMail(),
                legalInfo.getPhone(),
                legalInfo.getTIN()
        );
        studioDTO.setName(studio.getName());
        studioDTO.setDescription(studio.getDescription());
        studioDTO.setFullDescription(legalInfo.getFullDescription());

        when(legalInfoRepository.findByMail(studioDTO.getMail())).thenReturn(null);
        when(legalInfoRepository.findByTIN(studioDTO.getTin())).thenReturn(null);
        when(roleRepository.getRoleByRoleIgnoreCase(MANAGER_ROLE.getRole())).thenReturn(MANAGER_ROLE);
        when(balanceRepository.count()).thenReturn(1L);
        when(balanceRepository.save(any())).thenReturn(balance);
        when(studioRepository.save(any())).thenReturn(studio);
        Studio result = studioService.addStudioAndLegalInfo(studioDTO);

        assertEquals(studio, result);
        verify(legalInfoRepository, times(1)).findByMail(anyString());
        verify(legalInfoRepository, times(1)).findByTIN(anyString());
        verify(roleRepository, times(1)).getRoleByRoleIgnoreCase(anyString());
        verify(balanceRepository, times(1)).count();
        verify(balanceRepository, times(1)).save(any());
        verify(studioRepository, times(1)).save(any());
    }
    @Test
    void addStudioAndLegalInfoMailIsAlreadyExist() {
        Studio studio = utilsObjects.STUDIO;
        LegalInfo legalInfo = utilsObjects.LEGAL_INFO;

        StudioDTO studioDTO = new StudioDTO(
                legalInfo.getMail(),
                legalInfo.getPhone(),
                legalInfo.getTIN()
        );
        studioDTO.setName(studio.getName());
        studioDTO.setDescription(studio.getDescription());
        studioDTO.setFullDescription(legalInfo.getFullDescription());

        when(legalInfoRepository.findByMail(studioDTO.getMail())).thenReturn(legalInfo);
        assertThrows(MailIsAlreadyExistException.class, () -> studioService.addStudioAndLegalInfo(studioDTO));
    }
    @Test
    void addStudioAndLegalInfoTinIsAlreadyExist() {
        Studio studio = utilsObjects.STUDIO;
        LegalInfo legalInfo = utilsObjects.LEGAL_INFO;

        StudioDTO studioDTO = new StudioDTO(
                legalInfo.getMail(),
                legalInfo.getPhone(),
                legalInfo.getTIN()
        );
        studioDTO.setName(studio.getName());
        studioDTO.setDescription(studio.getDescription());
        studioDTO.setFullDescription(legalInfo.getFullDescription());

        when(legalInfoRepository.findByMail(studioDTO.getMail())).thenReturn(null);
        when(legalInfoRepository.findByTIN(studioDTO.getTin())).thenReturn(legalInfo);
        assertThrows(TinIsAlreadyExistException.class, () -> studioService.addStudioAndLegalInfo(studioDTO));
    }
    @Test
    void addStudioAndLegalInfoRoleNotFound() {
        Studio studio = utilsObjects.STUDIO;
        LegalInfo legalInfo = utilsObjects.LEGAL_INFO;

        StudioDTO studioDTO = new StudioDTO(
                legalInfo.getMail(),
                legalInfo.getPhone(),
                legalInfo.getTIN()
        );
        studioDTO.setName(studio.getName());
        studioDTO.setDescription(studio.getDescription());
        studioDTO.setFullDescription(legalInfo.getFullDescription());

        when(legalInfoRepository.findByMail(studioDTO.getMail())).thenReturn(null);
        when(legalInfoRepository.findByTIN(studioDTO.getTin())).thenReturn(null);
        when(roleRepository.getRoleByRoleIgnoreCase(MANAGER_ROLE.getRole())).thenReturn(null);
        assertThrows(RoleNotFoundException.class, () -> studioService.addStudioAndLegalInfo(studioDTO));
    }
    @Test
    void getAllStudios() {
        Studio studio1 = utilsObjects.STUDIO;
        Studio studio2 = utilsObjects.STUDIO;
        studio2.setId(studio1.getId() + 1);

        when(studioRepository.findAll()).thenReturn(List.of(studio1, studio2));
        List<Studio> result = studioService.getAllStudios();
        assertEquals(List.of(studio1, studio2), result);
        verify(studioRepository, times(1)).findAll();
    }
}

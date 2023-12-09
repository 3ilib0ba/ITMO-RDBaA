package com.example.demo.studio.service;

import com.example.demo.studio.dto.PositionDTO;
import com.example.demo.studio.exceptions.PositionsNotFoundException;
import com.example.demo.studio.model.Position;
import com.example.demo.studio.model.Studio;
import com.example.demo.studio.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PositionService {
    private PositionRepository positionRepository;
    private StudioService studioService;

    @Autowired
    public PositionService(
            PositionRepository positionRepository,
            StudioService studioService
    ) {
        this.positionRepository = positionRepository;
        this.studioService = studioService;
    }

    public Position getPositionByAddress(String address) {
        Position result = positionRepository.findByAddressIgnoreCase(address);
        if (result == null)
            throw new PositionsNotFoundException(address);
        return result;
    }

    public Position getPositionById(Long id) {
        return positionRepository.findById(id).orElseThrow(() -> new PositionsNotFoundException(id));
    }

    public List<Position> getAllPositionByStudioId(Long studioId) {
        studioService.getStudioById(studioId);
        List<Position> positions = positionRepository.findByStudio_Id(studioId);
        if (positions == null)
            throw new PositionsNotFoundException(studioId);
        return positions;
    }

    @Transactional
    public Position addPositionToStudio(PositionDTO positionDTO) {
        Studio studio = studioService.getStudioById(positionDTO.getStudioId());
        return positionRepository.save(
                new Position(
                        null,
                        positionDTO.getAddress(),
                        positionDTO.getHours(),
                        studio,
                        List.of())
        );
    }
}

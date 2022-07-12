package com.mateusz.home.service;

import com.mateusz.home.command.CreateInmateCommand;
import com.mateusz.home.dto.InmateDto;
import com.mateusz.home.exception.InvalidParameterException;
import com.mateusz.home.exception.ResourceNotFoundException;
import com.mateusz.home.model.Inmate;
import com.mateusz.home.repository.InmateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InmateService {
    final private InmateRepository inmateRepository;

    public InmateDto createInmate(CreateInmateCommand newInmate) {
        if(newInmate.getName()==null || newInmate.getName().isEmpty()){
            throw new InvalidParameterException("Inmate has no name!");
        }
        if(newInmate.getSurname()==null || newInmate.getSurname().isEmpty()){
            throw new InvalidParameterException("Inmate has no surname!");
        }

        return InmateDto.from(inmateRepository.save(Inmate.from(newInmate)));
    }

    public List<InmateDto> getInmates() {
        List<Inmate> inmates = inmateRepository.findAll();
        return inmates.stream().map(inmate -> InmateDto.from(inmate)).toList();
    }

    public InmateDto getInmateById(long id) {
        Optional<Inmate> inmate = inmateRepository.findById(id);
        if (inmate.isEmpty()) {
            throw new ResourceNotFoundException("Inmate not found!");
        }

        return InmateDto.from(inmate.get());
    }

    public InmateDto deleteInmate(long id) {
        Optional<Inmate> inmate = inmateRepository.findById(id);
        if (inmate.isEmpty()) {
            throw new ResourceNotFoundException("Inmate not found!");
        }

        inmateRepository.deleteById(id);
        return InmateDto.from(inmate.get());
    }

    public InmateDto updateInmate(long id, CreateInmateCommand changedInmate) {
        if(changedInmate.getName()==null || changedInmate.getName().isEmpty()){
            throw new InvalidParameterException("Inmate has no name!");
        }
        if(changedInmate.getSurname()==null || changedInmate.getSurname().isEmpty()){
            throw new InvalidParameterException("Inmate has no surname!");
        }
        Optional<Inmate> inmate = inmateRepository.findById(id);
        if (inmate.isEmpty()) {
            throw new ResourceNotFoundException("Inmate not found!");
        }

        inmate.get().update(changedInmate);
        return InmateDto.from(inmateRepository.save(inmate.get()));
    }
}

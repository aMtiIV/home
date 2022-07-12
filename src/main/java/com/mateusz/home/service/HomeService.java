package com.mateusz.home.service;

import com.mateusz.home.command.CreateHomeCommand;
import com.mateusz.home.dto.HomeDto;
import com.mateusz.home.dto.HomeWithInmatesDto;
import com.mateusz.home.exception.ResourceNotFoundException;
import com.mateusz.home.model.Home;
import com.mateusz.home.model.Inmate;
import com.mateusz.home.repository.HomeRepository;
import com.mateusz.home.repository.InmateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HomeService {
    final private HomeRepository homeRepository;
    final private InmateRepository inmateRepository;

    public HomeDto createHome(CreateHomeCommand newHome) {
        return HomeDto.from(homeRepository.save(Home.from(newHome)));
    }

    public List<HomeDto> getHomes() {
        List<Home> homes = homeRepository.findAll();
        return homes.stream().map(home -> HomeDto.from(home)).toList();
    }

    public HomeDto getHomeById(long id) {
        Optional<Home> home = homeRepository.findById(id);
        if (home.isEmpty()) {
            throw new ResourceNotFoundException("Home not found!");
        }

        return HomeDto.from(home.get());
    }

    public HomeDto deleteHome(long id) {
        Optional<Home> home = homeRepository.findById(id);
        if (home.isEmpty()) {
            throw new ResourceNotFoundException("Home not found!");
        }

        homeRepository.deleteById(id);
        return HomeDto.from(home.get());
    }

    public HomeWithInmatesDto addInmate(long homeId, long inmateId) {
        Optional<Home> home = homeRepository.findById(homeId);
        if (home.isEmpty()) {
            throw new ResourceNotFoundException("Home not found!");
        }
        Optional<Inmate> inmate = inmateRepository.findById(inmateId);
        if (inmate.isEmpty()) {
            throw new ResourceNotFoundException("Inmate not found!");
        }

        inmate.get().setHome(home.get());
        inmateRepository.save(inmate.get());
        return HomeWithInmatesDto.from(home.get());
    }

    public List<HomeWithInmatesDto> getHomesWithInmates() {
        List<Home> homes = homeRepository.findAll();
        return homes.stream().map(home -> HomeWithInmatesDto.from(home)).toList();
    }

    public HomeWithInmatesDto getHomeWithInmatesById(long id) {
        Optional<Home> home = homeRepository.findById(id);
        if (home.isEmpty()) {
            throw new ResourceNotFoundException("Home not found!");
        }

        return HomeWithInmatesDto.from(home.get());
    }

    public HomeWithInmatesDto removeInmate(long homeId, long inmateId) {
        Optional<Home> home = homeRepository.findById(homeId);
        if (home.isEmpty()) {
            throw new ResourceNotFoundException("Home not found!");
        }
        Optional<Inmate> inmate = inmateRepository.findById(inmateId);
        if (inmate.isEmpty()) {
            throw new ResourceNotFoundException("Inmate not found!");
        }

        inmate.get().setHome(null);
        inmateRepository.save(inmate.get());
        return HomeWithInmatesDto.from(home.get());
    }
}

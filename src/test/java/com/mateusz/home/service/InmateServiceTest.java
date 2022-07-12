package com.mateusz.home.service;

import com.mateusz.home.command.CreateInmateCommand;
import com.mateusz.home.dto.InmateDto;
import com.mateusz.home.model.Inmate;
import com.mateusz.home.repository.InmateRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InmateServiceTest {

    @Mock
    InmateRepository inmateRepository;

    @InjectMocks
    InmateService inmateService;

    private static Stream<Arguments> providerForCreateInmates(){
        return Stream.of(
                Arguments.of(
                        new CreateInmateCommand("testName","testSurname"),
                        new Inmate(1L, "testName", "testSurname", null),
                        new InmateDto(1L, "testName", "testSurname")),
                Arguments.of(
                        new CreateInmateCommand("","testSurname"),
                        new Inmate(1L,"","testSurname",null),
                        null),
                Arguments.of(
                        new CreateInmateCommand(null,"testSurname"),
                        new Inmate(1L,"","testSurname",null),
                        null),
                Arguments.of(
                        new CreateInmateCommand("testName",""),
                        new Inmate(1L,"testName","",null),
                        null),
                Arguments.of(
                        new CreateInmateCommand("testName",null),
                        new Inmate(1L,"testName","",null),
                        null),
                Arguments.of(
                        new CreateInmateCommand("gfqagw67etrge7w3@#$fda","testSurname"),
                        new Inmate(1L,"gfqagw67etrge7w3@#$fda","testSurname",null),
                        null),
                Arguments.of(
                        new CreateInmateCommand("testName","gfqagw67etrge7w3@#$fda"),
                        new Inmate(1L,"testName","gfqagw67etrge7w3@#$fda",null),
                        null)
        );
    }

    @ParameterizedTest
    @MethodSource("providerForCreateInmates")
    void createInmate(CreateInmateCommand input, Inmate mock, InmateDto expected) {
        lenient().when(inmateRepository.save(any())).thenReturn(mock);

        try {
            InmateDto inmate = inmateService.createInmate(input);
            assertEquals(inmate, expected);
        }catch (Exception e) {
            assertNull(expected);
            return;
        }
    }


    private static Stream<Arguments> providerForGetInmates(){
        return Stream.of(
                Arguments.of(
                        Stream.of(new Inmate(1L, "testName", "testSurname", null),new Inmate(2L, "testTwoName", "testTwoSurname",null)).collect(Collectors.toList()),
                        Stream.of(new InmateDto(1L, "testName", "testSurname"),new InmateDto(2L, "testTwoName", "testTwoSurname")).collect(Collectors.toList())),
                Arguments.of(
                        Stream.of(new Inmate(1L, "testName", "testSurname", null)).collect(Collectors.toList()),
                        Stream.of(new InmateDto(1L, "testName", "testSurname")).collect(Collectors.toList())),
                Arguments.of(
                        Stream.of().collect(Collectors.toList()),
                        Stream.of().collect(Collectors.toList()))
        );
    }

    @ParameterizedTest
    @MethodSource("providerForGetInmates")
    void getInmates(List<Inmate> mock, List<InmateDto> expected) {
        when(inmateRepository.findAll()).thenReturn(mock);

        List<InmateDto> inmates = inmateService.getInmates();
        assertEquals(inmates, expected);
    }

    private static Stream<Arguments> providerForGetInmateByIdAndDeleteInmate(){
        return Stream.of(
                Arguments.of(
                        1L,
                        Optional.of(new Inmate(1L, "testName", "testSurname", null)),
                        new InmateDto(1L, "testName", "testSurname")),
                Arguments.of(
                        1L,
                        Optional.empty(),
                        null)
        );
    }

    @ParameterizedTest
    @MethodSource("providerForGetInmateByIdAndDeleteInmate")
    void getInmateById(long input, Optional<Inmate> mock, InmateDto expected) {
        when(inmateRepository.findById(any())).thenReturn(mock);

        try {
            InmateDto inmate = inmateService.getInmateById(input);
            assertEquals(inmate, expected);
        }catch (Exception e){
            assertNull(expected);
        }
    }

    @ParameterizedTest
    @MethodSource("providerForGetInmateByIdAndDeleteInmate")
    void deleteInmate(long input, Optional<Inmate> mock, InmateDto expected) {
        when(inmateRepository.findById(any())).thenReturn(mock);

        try {
            InmateDto inmate = inmateService.deleteInmate(input);
            assertEquals(inmate, expected);
        }catch (Exception e){
            assertNull(expected);
        }
    }

    private static Stream<Arguments> providerForUpdateInmate(){
        return Stream.of(
                Arguments.of(
                        1L,
                        new CreateInmateCommand("testNewName","testNewSurname"),
                        Optional.of(new Inmate(1L, "testName", "testSurname", null)),
                        new Inmate(1L, "testNewName", "testNewSurname",null),
                        new InmateDto(1L, "testNewName", "testNewSurname")),
                Arguments.of(
                        1L,
                        new CreateInmateCommand("","testNewSurname"),
                        Optional.of(new Inmate(1L, "testName", "testSurname", null)),
                        new Inmate(1L, "", "testNewSurname",null),
                        null),
                Arguments.of(
                        1L,
                        new CreateInmateCommand(null,"testNewSurname"),
                        Optional.of(new Inmate(1L, "testName", "testSurname", null)),
                        new Inmate(1L, "", "testNewSurname",null),
                        null),
                Arguments.of(
                        1L,
                        new CreateInmateCommand("testNewName",""),
                        Optional.of(new Inmate(1L, "testName", "testSurname", null)),
                        new Inmate(1L, "testNewName", "",null),
                        null),
                Arguments.of(
                        1L,
                        new CreateInmateCommand("testNewName",null),
                        Optional.of(new Inmate(1L, "testName", "testSurname", null)),
                        new Inmate(1L, "testNewName", "",null),
                        null),
                Arguments.of(
                        1L,
                        new CreateInmateCommand("gfqagw67etrge7w3@#$fda","testNewSurname"),
                        Optional.of(new Inmate(1L, "testName", "testSurname", null)),
                        new Inmate(1L, "gfqagw67etrge7w3@#$fda", "testNewSurname",null),
                        null),
                Arguments.of(
                        1L,
                        new CreateInmateCommand("testNewName","gfqagw67etrge7w3@#$fda"),
                        Optional.of(new Inmate(1L, "testName", "testSurname", null)),
                        new Inmate(1L, "testNewName", "gfqagw67etrge7w3@#$fda",null),
                        null),
                Arguments.of(
                        1L,
                        new CreateInmateCommand("testNewName","testNewSurname"),
                        Optional.empty(),
                        null,
                        null)
        );
    }

    @ParameterizedTest
    @MethodSource("providerForUpdateInmate")
    void updateInmate(long inputId, CreateInmateCommand inputInmate, Optional<Inmate> mockFindById, Inmate mockSave, InmateDto expected) {
        lenient().when(inmateRepository.findById(any())).thenReturn(mockFindById);
        lenient().when(inmateRepository.save(any())).thenReturn(mockSave);

        try {
            InmateDto inmate = inmateService.updateInmate(inputId, inputInmate);
            assertEquals(inmate, expected);
        }catch (Exception e){
            assertNull(expected);
        }
    }
}

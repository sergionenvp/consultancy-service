package com.consultancygroup.consultant.ConsultantService;

import com.consultancygroup.consultant.ConsultantRepository.ConsultantRepository;
import com.consultancygroup.consultant.Model.Consultant;
import com.consultancygroup.consultant.Model.ConsultantResume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ConsultantServiceTest {
    @MockBean
    ConsultantRepository consultantMockRepository;
    @Autowired
    ConsultantService consultantService;
    @Autowired
    ModelMapper modelMapper;

    @Test
    void testSaveUserValidUser() {
        Consultant consultant = new Consultant("Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        Consultant expectedConsultant = new Consultant(1L, "Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        when(consultantMockRepository.save(any(Consultant.class))).thenReturn(expectedConsultant);
        Assertions.assertEquals(expectedConsultant,consultantService.saveConsultant(consultant));
    }
    @Test
    void testFindById(){
        Consultant expectedConsultant = new Consultant(1L, "Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        when(consultantMockRepository.findByConsultantId(1L)).thenReturn(expectedConsultant);
        Assertions.assertEquals(expectedConsultant,consultantService.findConsultantById(1L));
    }
    @Test
    void testGetUserByIdInvalidId() {
        Consultant consultant = new Consultant();
        when(consultantMockRepository.findById(1L)).thenReturn(null);

        // Assert
        Assertions.assertNull(consultantService.findConsultantById(1L));
        verify(consultantMockRepository, times(1)).findByConsultantId(1L);
    }
    @Test
    void testFindByName(){
        Consultant consultant1 = new Consultant(1L, "Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        Consultant consultant2 = new Consultant(2L, "Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        List<Consultant> expectedConsultants = new LinkedList<>();
        expectedConsultants.add(consultant1);
        expectedConsultants.add(consultant2);
        when(consultantMockRepository.findAllByFullName("Andre Vella")).thenReturn(expectedConsultants);
        Assertions.assertEquals(expectedConsultants,consultantService.findAllByFullName("Andre Vella"));
    }
    @Test
    void testFindByResume(){
        Consultant consultant1 = new Consultant(1L, "Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        Consultant consultant2 = new Consultant(2L, "Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        List<Consultant> expectedConsultants = new LinkedList<>();
        expectedConsultants.add(consultant1);
        expectedConsultants.add(consultant2);
        when(consultantMockRepository.findAllByConsultantResume(ConsultantResume.JUNIOR)).thenReturn(expectedConsultants);
        Assertions.assertEquals(expectedConsultants,consultantService.findAllByConsultantResume(ConsultantResume.JUNIOR));
    }
    @Test
    void testFindAll(){
        Consultant consultant1 = new Consultant(1L, "Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        Consultant consultant2 = new Consultant(2L, "Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        List<Consultant> expectedConsultants = new LinkedList<>();
        expectedConsultants.add(consultant1);
        expectedConsultants.add(consultant2);
        when(consultantMockRepository.findAll()).thenReturn(expectedConsultants);
        Assertions.assertEquals(expectedConsultants,consultantService.findAll());

    }
    @Test
    void testGetAllByMinAge(){
        Consultant consultant1 = new Consultant(1L, "Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        Consultant consultant2 = new Consultant(2L, "Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        List<Consultant> expectedConsultants = new LinkedList<>();
        expectedConsultants.add(consultant1);
        expectedConsultants.add(consultant2);
        when(consultantMockRepository.findAllByAgeAfter(19)).thenReturn(expectedConsultants);
        Assertions.assertEquals(expectedConsultants,consultantService.getConsultantsOlderThanMinAge(19));

    }

    @Test
    void testDeleteConsultantByIdValidId() {
        // Act
        consultantService.deleteByConsultantId(1L);

        // Assert
        verify(consultantMockRepository, times(1)).deleteAllByConsultantId(1L);
    }
    @Test
    void testDeleteAll() {
        // Act
        consultantService.deleteAllConsultants();
        // Assert
        verify(consultantMockRepository, times(1)).deleteAll();
    }
}

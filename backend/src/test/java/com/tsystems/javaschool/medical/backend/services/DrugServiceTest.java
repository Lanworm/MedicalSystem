package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dao.DrugRepository;
import com.tsystems.javaschool.medical.backend.dto.DrugDto;
import com.tsystems.javaschool.medical.backend.entities.DrugsEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DrugServiceTest {

    private DrugService sut;
    private DrugRepository drugRepositoryMock;
    private ModelMapper modelMapperMock;

    @BeforeEach
    public void SetUp() {

        drugRepositoryMock = Mockito.mock(DrugRepository.class);
        modelMapperMock = Mockito.mock(ModelMapper.class);

        sut = new DrugService(drugRepositoryMock, modelMapperMock);


    }

    @Test
    void testGetAll_singleResult_returnCorrect() {

        Mockito.when(drugRepositoryMock.findAll()).thenReturn(Collections.singletonList(new DrugsEntity()));

        List<DrugDto> result = sut.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetAll_checkModelMapper_runSingleTime() {

        DrugsEntity drugsEntity = new DrugsEntity();
        drugsEntity.setDescription("test");
        Mockito.when(drugRepositoryMock.findAll()).thenReturn(Collections.singletonList(drugsEntity));

        sut.getAll();

        Mockito.verify(modelMapperMock, Mockito.times(1)).map(drugsEntity,DrugDto.class);

    }

    @Test
    void add() {
    }
}

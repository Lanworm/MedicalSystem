package com.tsystems.javaschool.medical.backend.mapper;

import com.tsystems.javaschool.medical.backend.dto.prescriptions.PrescriptionDto;
import com.tsystems.javaschool.medical.backend.dto.prescriptions.PrescriptionRequestDto;
import com.tsystems.javaschool.medical.backend.entities.PrescriptionsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PrescriptionMapper {
    PrescriptionMapper PRESCRIPTION_MAPPER = Mappers.getMapper(PrescriptionMapper.class);

    @Mappings({
            @Mapping(target = "patientsByPatientId.id", source = "patientId"),
            @Mapping(target = "proceduresByProcedureId.id", source = "procedureId"),
            @Mapping(target = "drugsByDrugId.id", source = "drugId")
    })
    PrescriptionsEntity toPrescription(PrescriptionRequestDto dto);

    PrescriptionDto fromPrescription (PrescriptionsEntity entity);


}

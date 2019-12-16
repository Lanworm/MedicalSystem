package com.tsystems.javaschool.medical.backend.mapper;

import com.tsystems.javaschool.medical.backend.dto.prescriptions.PrescriptionDto;
import com.tsystems.javaschool.medical.backend.entities.DrugsEntity;
import com.tsystems.javaschool.medical.backend.entities.PrescriptionsEntity;
import com.tsystems.javaschool.medical.backend.entities.ProceduresEntity;
import com.tsystems.javaschool.medical.backend.entities.enums.IsDeleted;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PrescriptionMapperTest {

    public static final BigInteger TEST_ID = BigInteger.valueOf(1);
    public static final String TEST_TIME_PATTERN = "0 30 4 * * ?";
    public static final Timestamp TEST_START_DATE = new Timestamp(Long.parseLong("1576656233501"));
    public static final Timestamp TEST_END_DATE = new Timestamp(Long.parseLong("1576656233501"));
    public static final String TEST_DOSAGE = "10mg";
    public static final String TEST_DESCRIPTION = "Test description text";
    public static final Timestamp TEST_UPDATED_AT = new Timestamp(Long.parseLong("1576656233501"));
    public static final Timestamp TEST_CREATED_AT = new Timestamp(Long.parseLong("1576656233501"));
    public static final IsDeleted TEST_DELETED = IsDeleted.N;
    public static final ProceduresEntity TEST_PROCEDURES_BY_PROCEDURE_ID = new ProceduresEntity();
    public static final DrugsEntity TEST_DRUGS_BY_DRUG_ID = new DrugsEntity();

    public static PrescriptionsEntity preparePrescriptionEntity() {
        PrescriptionsEntity prescriptionsEntity = new PrescriptionsEntity();
        prescriptionsEntity.setId(TEST_ID);
        prescriptionsEntity.setTimePattern(TEST_TIME_PATTERN);
        prescriptionsEntity.setStartDate(TEST_START_DATE);
        prescriptionsEntity.setEndDate(TEST_END_DATE);
        prescriptionsEntity.setDosage(TEST_DOSAGE);
        prescriptionsEntity.setDescription(TEST_DESCRIPTION);
        prescriptionsEntity.setUpdatedAt(TEST_UPDATED_AT);
        prescriptionsEntity.setCreatedAt(TEST_CREATED_AT);
        prescriptionsEntity.setDeleted(TEST_DELETED);
        prescriptionsEntity.setProceduresByProcedureId(TEST_PROCEDURES_BY_PROCEDURE_ID);
        prescriptionsEntity.setDrugsByDrugId(TEST_DRUGS_BY_DRUG_ID);
        return prescriptionsEntity;
    }

    @Test
    void toPrescriptionNullEntityReturnNull() {
        assertNull(PrescriptionMapper.PRESCRIPTION_MAPPER.toPrescription(null));
    }

    @Test
    void fromPrescription_EmptyField_ReturnNull() {
        PrescriptionDto result = PrescriptionMapper.PRESCRIPTION_MAPPER.fromPrescription(preparePrescriptionEntity());
        assertNull(result.getPatientsByPatientId());
    }

    @Test
    void fromPrescription_IdField_ShouldMapCorrect() {
        PrescriptionDto result = PrescriptionMapper.PRESCRIPTION_MAPPER.fromPrescription(preparePrescriptionEntity());
        assertEquals(TEST_ID, result.getId());
    }
}

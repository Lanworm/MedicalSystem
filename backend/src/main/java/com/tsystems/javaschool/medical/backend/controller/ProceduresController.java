package com.tsystems.javaschool.medical.backend.controller;

import com.tsystems.javaschool.medical.backend.dto.ProceduresDto;
import com.tsystems.javaschool.medical.backend.services.ProceduresService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProceduresController {


    private final ProceduresService proceduresService;

    public ProceduresController(ProceduresService proceduresService) {
        this.proceduresService = proceduresService;
    }

    @RequestMapping(value = "/procedures", method = RequestMethod.GET)
    public List<ProceduresDto> getProcedures() {
        return proceduresService.getProceduresList();
    }

    @RequestMapping(value = "/procedures", method = RequestMethod.PUT)
    public List<ProceduresDto> addProcedure(
            @RequestParam(value = "description") String description
    ) {
        proceduresService.addProcedure(description);
        return proceduresService.getProceduresList();
    }

    @RequestMapping(value = "/procedures/{id}", method = RequestMethod.DELETE)
    public List<ProceduresDto> deleteProcedure(@PathVariable("id") final BigInteger Id) {
        proceduresService.deleteProcedure(Id);
        return proceduresService.getProceduresList();
    }

    @RequestMapping(value = "/procedures", method = RequestMethod.POST)
    public List<ProceduresDto> editProcedure(@RequestBody final ProceduresDto params) {
        proceduresService.updateProcedure(params);
        return proceduresService.getProceduresList();
    }
}

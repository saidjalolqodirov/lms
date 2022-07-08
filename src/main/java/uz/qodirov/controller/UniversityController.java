package uz.qodirov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.dto.univercity.UniversityCreateDTO;
import uz.qodirov.dto.univercity.UniversityDTO;
import uz.qodirov.dto.univercity.UniversityUpdateDTO;
import uz.qodirov.services.university.UniversityService;
import uz.qodirov.services.university.UniversityServiceImpl;

import javax.validation.Valid;
import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 12:50 AM
 */
@RestController
@RequestMapping("/api/v1/university")
public class UniversityController extends AbstractController<UniversityService> {

    public UniversityController(UniversityServiceImpl service) {
        super(service);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created University",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
    })
    @Operation(summary = "create university", description = "This method for create university")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UniversityCreateDTO createDTO) {
        return service.create(createDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "University not found", content = @Content)
    })
    @Operation(summary = "get university", description = "This method for get university")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return service.get(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content)
    })
    @Operation(summary = "get all universities", description = "This method for get all university")
    @GetMapping
    public ResponseEntity<DataDTO<List<UniversityDTO>>> getAll() {
        return service.getAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated University",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    @Operation(summary = "update university", description = "This method for update university")
    @PutMapping
    public ResponseEntity<DataDTO<UniversityDTO>> update(@RequestBody @Valid UniversityUpdateDTO universityUpdateDTO) {
        return service.update(universityUpdateDTO);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted University",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    @Operation(summary = "update university", description = "This method for deleted university")
    @DeleteMapping("/{id}")
    public ResponseEntity<DataDTO<Void>> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}

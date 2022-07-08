package uz.qodirov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.qodirov.dto.faculty.FacultyCreateDTO;
import uz.qodirov.dto.faculty.FacultyDTO;
import uz.qodirov.dto.faculty.FacultyUpdateDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.services.faculty.FacultyService;
import uz.qodirov.services.faculty.FacultyServiceImpl;

import javax.validation.Valid;
import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 9:57 PM
 */
@RestController
@RequestMapping("/api/v1/faculty")
public class FacultyController extends AbstractController<FacultyService> {

    public FacultyController(FacultyServiceImpl service) {
        super(service);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "faculty not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    @Operation(summary = "create faculty", description = "This method for create faculty")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody FacultyCreateDTO createDTO) {
        return service.create(createDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "faculty not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    @Operation(summary = "update faculty", description = "This method for update faculty")
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody FacultyUpdateDTO updateDTO) {
        return service.update(updateDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "faculty not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    @Operation(summary = "delete faculty", description = "This method for delete faculty")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "University not found", content = @Content)
    })
    @Operation(summary = "get faculty", description = "This method for get faculty")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return service.get(id);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content)
    })
    @Operation(summary = "get all faculty", description = "This method for get all faculty")
    @GetMapping
    public ResponseEntity<DataDTO<List<FacultyDTO>>> getAll() {
        return service.getAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content)
    })
    @Operation(summary = "get all faculty by university id", description = "This method for get all faculty")
    @GetMapping("/getAll/{id}")
    public ResponseEntity<DataDTO<List<FacultyDTO>>> getAllByUniversityId(@PathVariable Long id) {
        return service.getAllByUniversityId(id);
    }

}

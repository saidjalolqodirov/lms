package uz.qodirov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.qodirov.dto.subject.SubjectCreateDTO;
import uz.qodirov.dto.subject.SubjectDTO;
import uz.qodirov.dto.subject.SubjectUpdateDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.services.subject.SubjectService;

import javax.validation.Valid;
import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 4:37 PM
 */
@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController extends AbstractController<SubjectService> {

    public SubjectController(SubjectService service) {
        super(service);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully created",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "subject not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),})
    @Operation(summary = "create subject", description = "This method for create subject")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SubjectCreateDTO createDTO) {
        return service.create(createDTO);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "subject not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),})
    @Operation(summary = "update subject", description = "This method for update subject")
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody SubjectUpdateDTO updateDTO) {
        return service.update(updateDTO);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "subject not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),})
    @Operation(summary = "delete subject", description = "This method for delete subject")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "subject not found", content = @Content)})
    @Operation(summary = "get subject", description = "This method for get subject")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return service.get(id);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content)})
    @Operation(summary = "get all subject", description = "This method for get all subject")
    @GetMapping
    public ResponseEntity<DataDTO<List<SubjectDTO>>> getAll() {
        return service.getAll();
    }

}

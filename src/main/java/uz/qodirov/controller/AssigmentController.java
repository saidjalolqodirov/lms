package uz.qodirov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.qodirov.dto.assigment.AssigmentCreateDTO;
import uz.qodirov.dto.assigment.AssigmentUpdateDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.services.assigment.AssigmentService;

import javax.validation.Valid;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/8/2022 12:28 AM
 */
@RestController
@RequestMapping("/api/v1/assigment")
public class AssigmentController extends AbstractController<AssigmentService> {

    public AssigmentController(AssigmentService service) {
        super(service);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully ",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
    })
    @Operation(summary = "create assigment", description = "This method for create assigment")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AssigmentCreateDTO createDTO) {
        return service.create(createDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "not found", content = @Content)
    })
    @Operation(summary = "get assigment", description = "This method for get assigment")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return service.get(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content)
    })
    @Operation(summary = "get all assigment", description = "This method for get all assigment")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully ",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    @Operation(summary = "update assigment", description = "This method for update assigment")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid AssigmentUpdateDTO universityUpdateDTO) {
        return service.update(universityUpdateDTO);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully ",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    @Operation(summary = "update assigment", description = "This method for deleted assigment")
    @DeleteMapping("/{id}")
    public ResponseEntity<DataDTO<Void>> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}

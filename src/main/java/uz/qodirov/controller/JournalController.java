package uz.qodirov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.qodirov.dto.journal.JournalCreateDTO;
import uz.qodirov.dto.journal.JournalUpdateDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.services.journal.JournalService;

import javax.validation.Valid;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 10:15 PM
 */
@RestController
@RequestMapping("/api/v1/journal")
public class JournalController extends AbstractController<JournalService> {

    public JournalController(JournalService service) {
        super(service);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created journal",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
    })
    @Operation(summary = "create journal", description = "This method for create journal")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody JournalCreateDTO createDTO) {
        return service.create(createDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "journal not found", content = @Content)
    })
    @Operation(summary = "get journal", description = "This method for get journal")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return service.get(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content)
    })
    @Operation(summary = "get all journal", description = "This method for get all journal")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated journal",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    @Operation(summary = "update journal", description = "This method for update journal")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid JournalUpdateDTO universityUpdateDTO) {
        return service.update(universityUpdateDTO);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted journal",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    })
    @Operation(summary = "update journal", description = "This method for deleted journal")
    @DeleteMapping("/{id}")
    public ResponseEntity<DataDTO<Void>> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}

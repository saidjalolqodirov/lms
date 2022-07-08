package uz.qodirov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.qodirov.dto.mark.MarkCreateDTO;
import uz.qodirov.services.mark.MarkService;

import javax.validation.Valid;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 11:29 PM
 */
@RestController
@RequestMapping("/api/v1/mark")
public class MarkController extends AbstractController<MarkService> {

    public MarkController(MarkService service) {
        super(service);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully created",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "mark not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),})
    @Operation(summary = "create mark", description = "This method for create mark")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MarkCreateDTO createDTO) {
        return service.create(createDTO);
    }

}

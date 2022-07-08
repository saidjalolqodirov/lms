package uz.qodirov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.qodirov.dto.group.GroupCreateDTO;
import uz.qodirov.dto.group.GroupDTO;
import uz.qodirov.dto.group.GroupUpdateDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.services.group.GroupService;
import uz.qodirov.services.group.GroupServiceImpl;

import javax.validation.Valid;
import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 10:07 AM
 */
@RestController
@RequestMapping("/api/v1/group")
public class GroupController extends AbstractController<GroupService> {

    public GroupController(GroupServiceImpl service) {
        super(service);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully created",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "group not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),})
    @Operation(summary = "create group", description = "This method for create group")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody GroupCreateDTO createDTO) {
        return service.create(createDTO);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "group not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),})
    @Operation(summary = "add subject for group", description = "This method for add subject for group")
    @PostMapping("/addSubject")
    public ResponseEntity<?> addSubject(Long groupId, Long subjectId) {
        return service.addSubject(groupId, subjectId);
    }


    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "group not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),})
    @Operation(summary = "list subject in group", description = "This method for get list subject")
    @GetMapping("/listSubject/{groupId}")
    public ResponseEntity<?> listSubject(@PathVariable Long groupId) {
        return service.listSubjectInGroup(groupId);
    }


    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "group not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),})
    @Operation(summary = "remove subject in group", description = "This method for remove subject in group")
    @DeleteMapping("/removeSubject")
    public ResponseEntity<?> removeSubject(Long groupId, Long subjectId) {
        return service.removeSubject(groupId, subjectId);
    }


    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "group not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),})
    @Operation(summary = "update group", description = "This method for update group")
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody GroupUpdateDTO updateDTO) {
        return service.update(updateDTO);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "group not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),})
    @Operation(summary = "delete group", description = "This method for delete group")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "group not found", content = @Content)})
    @Operation(summary = "get group", description = "This method for get group")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return service.get(id);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "group not found", content = @Content)})
    @Operation(summary = "get all group by faculty", description = "This method for get group")
    @GetMapping("/getByFacultyId/{id}")
    public ResponseEntity<?> getAllByFaculty(@PathVariable Long id) {
        return service.getAllByFacultyId(id);
    }


    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully",
            content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content)})
    @Operation(summary = "get all group", description = "This method for get all group")
    @GetMapping
    public ResponseEntity<DataDTO<List<GroupDTO>>> getAll() {
        return service.getAll();
    }

}

package uz.qodirov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.dto.student.StudentCreateDTO;
import uz.qodirov.dto.student.StudentDTO;
import uz.qodirov.dto.student.StudentUpdateDTO;
import uz.qodirov.dto.subject.SubjectDTO;
import uz.qodirov.services.student.StudentService;

import javax.validation.Valid;
import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 6:07 PM
 */
@RestController
@RequestMapping("/api/v1/student")
public class StudentController extends AbstractController<StudentService> {

    public StudentController(StudentService service) {
        super(service);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully created", content = @Content(schema = @Schema(implementation = String.class))), @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content), @ApiResponse(responseCode = "404", description = "student not found", content = @Content), @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),})
    @Operation(summary = "create student", description = "This method for create student")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody StudentCreateDTO createDTO) {
        return service.create(createDTO);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully", content = @Content(schema = @Schema(implementation = String.class))), @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content), @ApiResponse(responseCode = "404", description = "student not found", content = @Content), @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),})
    @Operation(summary = "update student", description = "This method for update student")
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody StudentUpdateDTO updateDTO) {
        return service.update(updateDTO);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully", content = @Content(schema = @Schema(implementation = String.class))), @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content), @ApiResponse(responseCode = "404", description = "student not found", content = @Content), @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),})
    @Operation(summary = "delete student", description = "This method for delete student")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully", content = @Content(schema = @Schema(implementation = String.class))), @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content), @ApiResponse(responseCode = "404", description = "University not found", content = @Content)})
    @Operation(summary = "get student", description = "This method for get student")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return service.get(id);
    }

    @Operation(summary = "get student by username", description = "This method for get student")
    @GetMapping("/getByUsername/{name}")
    public ResponseEntity<?> getByUsername(@PathVariable String name) {
        return service.getByUsername(name);
    }


    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully", content = @Content(schema = @Schema(implementation = String.class))), @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content)})
    @Operation(summary = "get all student", description = "This method for get all student")
    @GetMapping
    public ResponseEntity<DataDTO<List<StudentDTO>>> getAll() {
        return service.getAll();
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully", content = @Content(schema = @Schema(implementation = String.class))), @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content)})
    @Operation(summary = "get list subject", description = "This method for get list subject")
    @GetMapping("/listSubjects/{studentId}")
    public ResponseEntity<DataDTO<List<SubjectDTO>>> getListSubjectsByStudentId(@PathVariable Long studentId) {
        return service.getListSubjects(studentId);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully", content = @Content(schema = @Schema(implementation = String.class))), @ApiResponse(responseCode = "500", description = "Parameters is incorrect", content = @Content)})
    @Operation(summary = "get list subject", description = "This method for get list subject")
    @GetMapping("/average/{groupId}")
    public ResponseEntity<?> averageMark(@PathVariable Long groupId) {
        return service.averageMark(groupId);
    }

}

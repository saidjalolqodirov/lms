package uz.qodirov.services.group;

import org.springframework.http.ResponseEntity;
import uz.qodirov.dto.group.GroupCreateDTO;
import uz.qodirov.dto.group.GroupDTO;
import uz.qodirov.dto.group.GroupUpdateDTO;
import uz.qodirov.dto.response.DataDTO;
import uz.qodirov.dto.subject.SubjectDTO;
import uz.qodirov.entity.Group;
import uz.qodirov.services.GenericCrudService;

import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 8:03 AM
 */
public interface GroupService extends GenericCrudService<Group, GroupDTO, GroupCreateDTO, GroupUpdateDTO, Long> {

    @Override
    ResponseEntity<DataDTO<GroupDTO>> create(GroupCreateDTO createDto);

    ResponseEntity<DataDTO<String>> addSubject(Long groupId, Long subjectId);

    ResponseEntity<DataDTO<Boolean>> removeSubject(Long groupId, Long subjectId);

    @Override
    ResponseEntity<DataDTO<Void>> delete(Long id);

    @Override
    ResponseEntity<DataDTO<GroupDTO>> update(GroupUpdateDTO updateDto);

    @Override
    ResponseEntity<DataDTO<List<GroupDTO>>> getAll();

    ResponseEntity<DataDTO<List<SubjectDTO>>> listSubjectInGroup(Long groupId);

    ResponseEntity<DataDTO<List<GroupDTO>>> getAllByFacultyId(Long id);

    @Override
    ResponseEntity<DataDTO<GroupDTO>> get(Long id);

}

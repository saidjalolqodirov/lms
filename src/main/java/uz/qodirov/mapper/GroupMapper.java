package uz.qodirov.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.qodirov.dto.group.GroupCreateDTO;
import uz.qodirov.dto.group.GroupDTO;
import uz.qodirov.dto.group.GroupUpdateDTO;
import uz.qodirov.entity.Group;

import java.util.List;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/7/2022 8:00 AM
 */
@Component
@Mapper(componentModel = "spring")
public interface GroupMapper extends GenericMapper<Group, GroupDTO, GroupCreateDTO, GroupUpdateDTO, Number> {
    @Override
    GroupDTO toDto(Group group);

    @Override
    List<GroupDTO> toDto(List<Group> e);

    @Override
    Group fromCreateDto(GroupCreateDTO groupCreateDTO);

    @Override
    Group fromUpdateDto(GroupUpdateDTO d);
}

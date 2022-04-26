package com.sportshub.service.role;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.role.RoleCreateDto;
import com.sportshub.dto.role.RoleDto;
import com.sportshub.dto.role.RoleUpdateDto;

import java.util.List;

public interface RoleService {
    RoleDto create(RoleCreateDto roleDto);
    List<RoleDto> findAll(int page, int limit);
    CountDto getCount();
    RoleDto find(Long id);
    void update(Long id, RoleUpdateDto roleDto);
    void delete(Long id);
}

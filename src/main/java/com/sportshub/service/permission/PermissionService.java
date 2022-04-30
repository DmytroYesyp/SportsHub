package com.sportshub.service.permission;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.permission.PermissionCreateDto;
import com.sportshub.dto.permission.PermissionDto;
import com.sportshub.dto.permission.PermissionUpdateDto;

import java.util.List;

public interface PermissionService {
    PermissionDto create(PermissionCreateDto permissionDto);
    List<PermissionDto> findAll(int page, int limit);
    CountDto getCount();
    PermissionDto find(Long id);
    void update(Long id, PermissionUpdateDto permissionDto);
    void delete(Long id);
}

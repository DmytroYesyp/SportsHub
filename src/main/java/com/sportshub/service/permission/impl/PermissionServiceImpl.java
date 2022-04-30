package com.sportshub.service.permission.impl;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.permission.PermissionCreateDto;
import com.sportshub.dto.permission.PermissionDto;
import com.sportshub.dto.permission.PermissionUpdateDto;
import com.sportshub.entity.permission.PermissionEntity;
import com.sportshub.exception.ConflictException;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.permission.PermissionMapper;
import com.sportshub.repository.permission.PermissionRepository;
import com.sportshub.service.permission.PermissionService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    @Override
    public PermissionDto create(PermissionCreateDto permissionDto) {
        PermissionEntity permissionEntity = permissionMapper.toEntity(permissionDto);
        try {
            permissionEntity = permissionRepository.save(permissionEntity);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("permissions_name_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("Permission with name '%s' already exists!", permissionEntity.getName());
                    throw new ConflictException(errorMessage);
                }
            }

            throw e;
        }
        return permissionMapper.toDto(permissionEntity);
    }

    @Override
    public List<PermissionDto> findAll(int page, int limit) {
        List<PermissionEntity> permissionEntities = permissionRepository.findAllPermissions(PageRequest.of(page - 1, limit));
        return permissionMapper.toDtoList(permissionEntities);
    }

    @Override
    public CountDto getCount() {
        int sportKindsCount = (int) permissionRepository.count();
        return new CountDto(sportKindsCount);
    }

    @Override
    public PermissionDto find(Long id) {
        PermissionEntity permissionEntity = permissionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sport kind not found!"));
        return permissionMapper.toDto(permissionEntity);
    }

    @Override
    @Transactional
    public void update(Long id, PermissionUpdateDto permissionDto) {
        PermissionEntity permissionEntity = permissionMapper.toEntity(permissionDto);
        try{
            int affectedRaws = permissionRepository.update(id, permissionEntity);
            if (affectedRaws == 0) {
                new NotFoundException("Permission not found!");
            }
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("permissions_name_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("Permission with name '%s' already exists!", permissionEntity.getName());
                    throw new ConflictException(errorMessage);
                }
            }
            throw e;
        }

        permissionRepository.update(id, permissionEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        int affectedRaws = permissionRepository.removeById(id);
        if (affectedRaws == 0) {
            throw new NotFoundException("Permission not found!");
        }
    }
}

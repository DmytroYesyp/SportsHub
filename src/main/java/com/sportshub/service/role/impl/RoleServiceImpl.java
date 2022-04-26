package com.sportshub.service.role.impl;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.role.RoleCreateDto;
import com.sportshub.dto.role.RoleDto;
import com.sportshub.dto.role.RoleUpdateDto;
import com.sportshub.entity.role.RoleEntity;
import com.sportshub.exception.ConflictException;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.role.RoleMapper;
import com.sportshub.repository.role.RoleRepository;
import com.sportshub.service.role.RoleService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleDto create(RoleCreateDto roleDto) {
        RoleEntity roleEntity = roleMapper.toEntity(roleDto);
        try {
            roleEntity = roleRepository.save(roleEntity);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("roles_name_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("Role with name '%s' already exists!", roleEntity.getName());
                    throw new ConflictException(errorMessage);
                } else if ("permission_roles_permission_id_fkey".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("Not find a suitable permission! Problems with foreign key!");
                    throw new ConflictException(errorMessage);
                }
            }

            throw e;
        }
        return roleMapper.toDto(roleEntity);
    }

    @Override
    public List<RoleDto> findAll(int page, int limit) {
        List<RoleEntity> roleEntities = roleRepository.findAllRoles(PageRequest.of(page - 1, limit));
        return roleMapper.toDtoList(roleEntities);
    }

    @Override
    public CountDto getCount() {
        int sportKindsCount = (int) roleRepository.count();
        return new CountDto(sportKindsCount);
    }

    @Override
    public RoleDto find(Long id) {
        RoleEntity roleEntity = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found!"));
        return roleMapper.toDto(roleEntity);
    }

    @Override
    @Transactional
    public void update(Long id, RoleUpdateDto roleDto) {
        RoleEntity roleEntity = roleMapper.toEntity(roleDto);
        try {
            int affectedRaws = roleRepository.update(id, roleEntity);
            if (affectedRaws == 0) {
                new NotFoundException("Role not found!");
            }
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("roles_name_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("Role with name '%s' already exists!", roleEntity.getName());
                    throw new ConflictException(errorMessage);
                } else if ("permission_roles_permission_id_fkey".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("Not find a suitable permission! Problems with foreign key!");
                    throw new ConflictException(errorMessage);
                }
            }
            throw e;
        }

        roleRepository.update(id, roleEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        int affectedRaws = roleRepository.removeById(id);
        if (affectedRaws == 0) {
            throw new NotFoundException("Role not found!");
        }
    }
}


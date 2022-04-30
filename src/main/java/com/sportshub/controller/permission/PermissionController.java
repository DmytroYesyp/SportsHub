package com.sportshub.controller.permission;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.permission.PermissionCreateDto;
import com.sportshub.dto.permission.PermissionDto;
import com.sportshub.dto.permission.PermissionUpdateDto;
import com.sportshub.service.permission.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissionDto create(@RequestBody @Valid PermissionCreateDto permissionDto) {
        return permissionService.create(permissionDto);
    }

    @GetMapping
    public List<PermissionDto> findAll(@RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "1000") int limit) {

        return permissionService.findAll(page, limit);
    }

    @GetMapping("{id}")
    public PermissionDto find(@PathVariable Long id) {
        return permissionService.find(id);
    }

    @GetMapping("count")
    public CountDto getCount() {
        return permissionService.getCount();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody PermissionUpdateDto permissionDto) {
        permissionService.update(id, permissionDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        permissionService.delete(id);
    }
}

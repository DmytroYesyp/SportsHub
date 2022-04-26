package com.sportshub.controller.role;
import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.role.RoleCreateDto;
import com.sportshub.dto.role.RoleDto;
import com.sportshub.dto.role.RoleUpdateDto;
import com.sportshub.service.role.RoleService;
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
@RequestMapping("roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDto create(@RequestBody @Valid RoleCreateDto roleDto) {
        return roleService.create(roleDto);
    }

    @GetMapping
    public List<RoleDto> findAll(@RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "1000") int limit) {

        return roleService.findAll(page, limit);
    }

    @GetMapping("{id}")
    public RoleDto find(@PathVariable Long id) {
        return roleService.find(id);
    }

    @GetMapping("count")
    public CountDto getCount() {
        return roleService.getCount();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody RoleUpdateDto roleDto) {
        roleService.update(id, roleDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }
}

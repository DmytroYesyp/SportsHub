package com.sportshub.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

@RestController
public class Controller1 {
    List<String> list = new LinkedList<>();

    @PostMapping(path = "api/addItem{String}")
    public ResponseEntity<Object> regNewUser(@RequestParam() String string) {
        list.add(string);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("regNewUser").toUriString());
        return ResponseEntity.created(uri).body(null);
    }

    @GetMapping(path = "api/get{Id}")
    public ResponseEntity<Object> get(@RequestParam() int Id) {
        try {
            if (Id > list.size() || Id < 0)
                throw new IndexOutOfBoundsException();
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok((list.get(Id)));
    }

    @GetMapping(path = "api/getAll")
    public ResponseEntity<Object> getList() {
        return ResponseEntity.ok((list.toString()));
    }

    @PutMapping(path = "api/update{Id}{String}")
    public ResponseEntity<Object> updateUser(
            @RequestParam int Id,
            @RequestParam String string) {
        try {
            if (Id > list.size() || Id < 0)
                throw new IndexOutOfBoundsException();
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }
        String tmp;
        tmp = list.get(Id);
        list.set(Id, string);
        return ResponseEntity.ok(tmp + "updated to " + list.get(Id));
    }

    @DeleteMapping(path = "api/delete{Id}")
    public ResponseEntity<Object> deleteUser(@RequestParam() int Id) {
        try {
            if (Id > list.size() || Id < 0)
                throw new IndexOutOfBoundsException();
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }
        String temp = list.get(Id);
        list.remove(Id);
        return ResponseEntity.ok("Deleted item" + temp);
    }

}

package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.component.UserDaoService;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;

@RestController
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping(value = "/findAll")
	public List<User> findAll() {
		return userDaoService.findAll();
	}

	@GetMapping(value = "/findById/{id}")
	public EntityModel<User> findById(@PathVariable(value = "id") Integer id) {
		User user = userDaoService.findById(id);
		if (Objects.isNull(user)) {
			throw new UserNotFoundException("id-" + id);
		}
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).findAll());
		entityModel.add(linkToUsers.withRel("all-users"));
		return entityModel;
	}

	@PostMapping
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		user = userDaoService.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	@DeleteMapping(value = "/deleteById/{id}")
	public User deleteById(@PathVariable(value = "id") Integer id) {
		User user = userDaoService.deleteById(id);
		if (Objects.isNull(user)) {
			throw new UserNotFoundException("id-" + id);
		}
		return user;
	}

}

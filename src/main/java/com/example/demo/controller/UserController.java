package com.example.demo.controller;

import com.example.demo.controller.dto.UserCreateRequestDto;
import com.example.demo.controller.dto.UserResponseDto;
import com.example.demo.service.IRepository;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserService userService;

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/bean")
    @ResponseBody
    public String bean() {
        return applicationContext.getBean(IRepository.class).toString();
    }

    @GetMapping("")
    public String userPage(Model model) {
        List<UserResponseDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "/users/list";
    }

    @GetMapping("/detail")
    public String detailPage(@RequestParam Integer id, Model model) {
        UserResponseDto user = userService.findById(id);
        model.addAttribute("id", user.getId());
        model.addAttribute("name", user.getName());
        model.addAttribute("age", user.getAge());
        model.addAttribute("job", user.getJob());
        model.addAttribute("specialty", user.getSpecialty());
        return "/users/detail";
    }

    @GetMapping("/data")
    @ResponseBody
    public UserResponseDto detailData(@RequestParam Integer id) {
        UserResponseDto user = userService.findById(id);
        return user;
    }

    @PostMapping("")
    @ResponseBody
    public UserResponseDto save(@RequestBody @Valid UserCreateRequestDto request) {
        UserResponseDto user = userService.save(request.getName(), request.getAge(), request.getJob(), request.getSpecialty());
        return user;
    }
}

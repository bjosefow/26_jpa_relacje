package com.example.jpa_relacje_26_zadaniekoncowe.access.web;

import com.example.jpa_relacje_26_zadaniekoncowe.access.user.UserService;
import com.example.jpa_relacje_26_zadaniekoncowe.access.user.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserPanelController {

    private final UserService userService;

    public UserPanelController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userData")
    public String userPage(Model model) {
        Optional<UserDto> currentUserOpt = userService.getCurrentUserDto();
        currentUserOpt.ifPresent(user -> model.addAttribute("currentUser", user));
        return "userDataPage";
    }

    @GetMapping("/update-user")
    public String updateUser(Model model) {
        Optional<UserDto> currentUserOpt = userService.getCurrentUserDto();
        if (currentUserOpt.isPresent()) {
            model.addAttribute("userToInsert", currentUserOpt.get());
            return "update-form";
        } else {
            return "error";
        }
    }

    @PostMapping("/update")
    public String updateUser(UserDto userDto) {
        userService.updateUser(userDto);
        return "redirect:/userData";
    }
}

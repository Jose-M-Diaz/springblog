package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiceController {
    @GetMapping("/roll-dice")
    public String guessNumber() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDice(@PathVariable int n, Model model) {
        int randomNum = (int)(Math.random() * 6) + 1;
        if(n == randomNum) {
            model.addAttribute("result", "You guessed correctly");
        } else {
            model.addAttribute("result", "Sorry, you are incorrect");
        }
        return "roll-dice";
    }
}

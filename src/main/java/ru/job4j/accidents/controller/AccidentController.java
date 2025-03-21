package ru.job4j.accidents.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.entity.AccidentEntity;
import ru.job4j.accidents.model.entity.AccidentTypeEntity;
import ru.job4j.accidents.model.entity.RuleEntity;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleService;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService<AccidentEntity> accidentServiceImplData;
    private final AccidentTypeService<AccidentTypeEntity> accidentTypeServiceImplData;
    private final RuleService<RuleEntity> ruleServiceImplData;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("statements", accidentServiceImplData.findAll());
        return "statements/list";
    }

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentTypeServiceImplData.findAll());
        model.addAttribute("rules", ruleServiceImplData.findAll());
        return "statements/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute AccidentEntity accident, HttpServletRequest req) {
        String[] ruleIds = req.getParameterValues("rIds");
        accidentServiceImplData.save(accident, ruleIds);
        return "redirect:/";
    }

    @PostMapping("/editAccident")
    public String edit(@ModelAttribute AccidentEntity accident, HttpServletRequest req) {
        String[] ruleIds = req.getParameterValues("rIds");
        accidentServiceImplData.update(accident, ruleIds);
        return "redirect:/";
    }

    @GetMapping("/accident/{id}")
    public String getById(Model model, @PathVariable int id) {
        var accident = accidentServiceImplData.findById(id);
        if (accident.isEmpty()) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "errors/404";
        }
        model.addAttribute("accident", accident.get());
        model.addAttribute("types", accidentTypeServiceImplData.findAll());
        model.addAttribute("rules", ruleServiceImplData.findAll());
        model.addAttribute("selectedTypeId", accident.get().getType().getId());
        model.addAttribute("selectedRuleIds", accident.get().getRule());
        return "statements/one";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        var accident = accidentServiceImplData.findById(id);
        if (accident.isEmpty()) {
            model.addAttribute("message", "Ничего не найдено");
            return "statements/error";
        }
        model.addAttribute("accident", accident.get());
        return "statements/update";
    }
}
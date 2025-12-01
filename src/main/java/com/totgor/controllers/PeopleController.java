package com.totgor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.totgor.dao.PersonDAO;
import com.totgor.models.Person;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonDAO personDAO;

    @GetMapping()
    public String index(Model model) {
        //Получим всех людей из DAO и передадим на отображение в представление index.html
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        //Получим одного человека по id из DAO и передадим на отображение в представление show.html
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        //Создадим нового человека в форме new.html
        return "people/new";
    }
    
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return "people/new";

        //Сохраним нового человека в БД (Коллекции) через DAO
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        //Откроем в форме edit.html человека за заполненными полями
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }
    
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) return "people/edit";

        //Обновим поля человека, которого редактировали
        personDAO.update(id, person);
        return "redirect:/people";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        //Удалим выбранного человека по его id
        personDAO.delete(id);
        return "redirect:/people";
    }
}

package com.example.demo.controller;

import com.example.demo.model.Basket;
import com.example.demo.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shop")
@SessionAttributes("basket")
public class AppController {

    @ModelAttribute("basket")
    public Basket createBasket() {
        return new Basket();
    }

    @GetMapping
    public String showProducts(Model model) {
        Product bicycle = new Product("Bicycle", 299.99, 10, "Red", 2023, "Mountain bike with 21 gears");
        model.addAttribute("product", bicycle);
        return "product";
    }

    @PostMapping("/add-to-basket")
    public String addToBasket(@ModelAttribute("basket") Basket basket, @RequestParam String name,
                              @RequestParam double price, @RequestParam int quantity,
                              @RequestParam String color, @RequestParam int productionYear,
                              @RequestParam String description) {
        Product product = new Product(name, price, quantity, color, productionYear, description);
        basket.addProduct(product);
        return "redirect:/shop/basket";
    }

    @GetMapping("/basket")
    public String viewBasket(@ModelAttribute("basket") Basket basket, Model model) {
        model.addAttribute("basket", basket);
        return "basket";
    }
}

package jpabook.jpashop.controller;

import jpabook.jpashop.item.Book;
import jpabook.jpashop.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @GetMapping("/items")
    public String createList(Model model){
        List<Item> list = itemService.findAll();
        model.addAttribute("items", list);
        return "items/itemList";
    }

    @PostMapping("/items/new")
    public String create(BookForm bookForm){
        Book book = new Book();
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());
        itemService.save(book);

        return "redirect:/";

    }


    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){

        Book item = (Book) itemService.findOne(itemId);

        BookForm bookForm = new BookForm();
        bookForm.setId(item.getId());
        bookForm.setAuthor(item.getAuthor());
        bookForm.setIsbn(item.getIsbn());
        bookForm.setStockQuantity(item.getStockQuantity());
        bookForm.setPrice(item.getPrice());
        bookForm.setName(item.getName());

        model.addAttribute("form", bookForm);
        return "items/updateItemForm";
    }


    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm form){

        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/items";

    }



}

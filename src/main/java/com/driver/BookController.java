package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){

        bookList.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()
    @GetMapping("/get-book-by-id/{id}")
    public Book bookById(@PathVariable ("id") int id){
        for(Book output:bookList){
            if(output.getId()==id){
                return output;
            }
        }
        return null;
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()
    @DeleteMapping("/delete-book-by-id/{id}")
    public void deleteBookById(@PathVariable ("id") int id){
        for(Book delBook:bookList){
            if(delBook.getId()==id) {
                bookList.remove(delBook);
                return;
            }
        }
    }


    // get request /get-all-books
    // getAllBooks()
    @GetMapping("/get-all-books")
    public List<Book> getAllBooks(){
        return bookList;

    }

    // delete request /delete-all-books
    // deleteAllBooks()
    @DeleteMapping("/delete-all-books")
    public void deleteAllBooks(){
        bookList=new ArrayList<Book>();

    }

    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
    @GetMapping("/get-books-by-author")
    public String getBooksByAuthor(@RequestParam ("author") String author){
        for(Book output:bookList){
            if(output.getAuthor()==author){
                return output.getAuthor()+" "+output.getName();
            }
        }
        return null;
    }

    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
    @GetMapping("/get-books-by-genre")
    public String getBooksByGenre(@RequestParam ("genre") String genre){
        for(Book output:bookList){
            if(output.getGenre()==genre){
                return output.getGenre()+" "+output.getName();
            }
        }
        return null;
    }
}

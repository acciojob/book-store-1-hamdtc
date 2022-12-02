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
    public ResponseEntity<Book> getBookById(@PathVariable ("id") int id){
        for(Book output:bookList){
            if(output.getId()==id){
                break;
            }
        return new ResponseEntity<>(output,HttpStatus.ACCEPTED);
        }
        return null;
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()
    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity deleteBookById(@PathVariable ("id") int id){
        for(Book delBook:bookList){
            if(delBook.getId()==id) {
                bookList.remove(delBook);
                break;
            }
        }
        return new ResponseEntity<>("Deleted",HttpStatus.ACCEPTED);
    }


    // get request /get-all-books
    // getAllBooks()
    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>>getAllBooks(){
        return new ResponseEntity<>(this.bookList,HttpStatus.ACCEPTED);
    }

    // delete request /delete-all-books
    // deleteAllBooks()
    @DeleteMapping("/delete-all-books")
    public ResponseEntity deleteAllBooks(){
        bookList=new ArrayList<Book>();
        return new ResponseEntity<>("all deleted",HttpStatus.ACCEPTED);
    }

    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam ("author") String author){
        List<Book> ans=new ArrayList<>();
        for(Book output:bookList){
            if(output.getAuthor().equals(author)){
                ans.add(output);
            }
        }
        return new ResponseEntity<>(ans,HttpStatus.CREATED);
    }

    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam ("genre") String genre){
        List<Book> ans=new ArrayList<>();
        for(Book output:bookList){
            if(output.getAuthor().equals(genre)){
                ans.add(output);
            }
        }
        return new ResponseEntity<>(ans,HttpStatus.CREATED);
    }
}

package com.loicmaria.webapp.proxies;

import com.loicmaria.webapp.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@FeignClient(name = "api", url = "localhost:9000")
public interface ApiProxy{


    //Book

    @RequestMapping(value = "/books/id/{id}", method = RequestMethod.GET)
    Book getBook(@RequestParam("id") int id);

    @RequestMapping(value = "/books/all", method = RequestMethod.GET)
    Collection<Book> getBooks();

    @RequestMapping(value = "/books/title/{title}", method = RequestMethod.GET)
    Collection<Book> getBooksBySearch(@PathVariable("title") String title);

    //---------------------------------------------------------------------------
    
    //Copy

    @RequestMapping(value = "/copies/book_id/{id}", method = RequestMethod.GET)
    Collection<Copy> findByBook_Id(@PathVariable int id);


    //---------------------------------------------------------------------------
    
    //Booking

    /**
     * Create - Add a new booking
     *
     * @param booking An object booking
     * @return The booking object saved
     */
    @RequestMapping(value = "/bookings/create", method = RequestMethod.POST)
    ResponseEntity<?> createBooking(@RequestBody int userId,@RequestBody int copyId);

    /**
     * Update - booking extension update
     *
     * @param booking - The booking object updated
     * @return The currentBooking if he is present or null
     */
    @RequestMapping(value = "/bookings/extend", method = RequestMethod.PUT)
    Booking extendBooking(@RequestBody Booking booking);

    @RequestMapping(value = "/bookings/by_user_id/{id}", method = RequestMethod.GET)
    Collection<Booking> getBookingsByUser(@PathVariable("id") int id, String status);



    //---------------------------------------------------------------------------

    //User

    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    ResponseEntity<?> createUser(@RequestBody User user);

    /**
     * Read - Get one user
     *
     * @param id The id of the user
     * @return An User object full filled
     */
    @RequestMapping(value = "/users/id/{id}", method = RequestMethod.GET)
    User getUser(@PathVariable("id") int id);

    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    Collection<User> getUsers();

    @RequestMapping(value = "/users/id/{id}", method = RequestMethod.PUT)
    User updateUser(@PathVariable("id") int id, @RequestBody User user);

    @RequestMapping(value = "/users/id/{id}", method = RequestMethod.DELETE)
    void deleteUser(@PathVariable("id") int id);

    @RequestMapping(value = "/users/username/{username}", method = RequestMethod.GET)
    User getUserByUsername(@PathVariable("username") String username);


}

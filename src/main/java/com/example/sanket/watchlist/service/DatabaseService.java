package com.example.sanket.watchlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.sanket.watchlist.entity.Movie;
import com.example.sanket.watchlist.repository.MovieRepo;

@Service
public class DatabaseService {

	@Autowired
	MovieRepo movieRepo;

	@Autowired
	RatingService ratingService;
	
	
    public void create(Movie movie) {
    	String rating=ratingService.getMovieRating(movie.getTitle());
    	if(rating !=null) {
    		try {
    			movie.setRating(Float.parseFloat(rating));    			
    		}catch(NumberFormatException e){
    			System.out.println("Invalid rating format: "+rating);
    		}
    	}
    	movieRepo.save(movie);    		
	}
    
    public List<Movie> getAllMovies() {
    	return movieRepo.findAll();
    }
	
    public Movie getMovieById(Integer id) {
    	return movieRepo.findById(id).get();
    }

	public void update(Movie movie, Integer id) {
		Movie toBeUpdated=getMovieById(id);
		toBeUpdated.setTitle(movie.getTitle());
		toBeUpdated.setRating(movie.getRating());
		toBeUpdated.setComment(movie.getComment());
		toBeUpdated.setPriority(movie.getPriority());
		movieRepo.save(toBeUpdated);
	}

}

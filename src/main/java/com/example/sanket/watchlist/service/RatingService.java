package com.example.sanket.watchlist.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;


@Service
public class RatingService {
	
//	String apiUrl="https://www.omdbapi.com/?apikey=a938d9b3=";
	String apiUrl="https://www.omdbapi.com/?&apikey=a938d9b3&t=";
	public String getMovieRating(String title) {
		// TODO Auto-generated method stub
		try {
			// try to fetch the rating by calling omdbApi.
			RestTemplate template=new RestTemplate();
			// apiUrl + title
			
			ResponseEntity<ObjectNode> response=template.getForEntity(apiUrl+title,ObjectNode.class );
			
			ObjectNode jsonObject=response.getBody();
			System.out.println(jsonObject.path("imdbRating").asText());
			return jsonObject.path("imdbRating").asText();
			
		} catch (Exception e) {
			// to user entered rating will be taken.
			System.out.println("Either Movie Name not Available or API is Down"+e.getMessage());
			return null;
		}
	}
	
}

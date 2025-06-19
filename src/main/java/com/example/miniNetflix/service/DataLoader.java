package com.example.miniNetflix.service;

import com.example.miniNetflix.domain.entity.LoadStatus;
import com.example.miniNetflix.domain.entity.MovieEntity;
import com.example.miniNetflix.domain.entity.Rating;
import com.example.miniNetflix.domain.entity.User;
import com.example.miniNetflix.repository.LoadStatusRepository;
import com.example.miniNetflix.repository.MovieRepository;
import com.example.miniNetflix.repository.RatingRepository;
import com.example.miniNetflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final LoadStatusRepository loadStatusRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner is running...");
        if (!loadStatusRepository.existsById("movies")) {
            loadMovies();
            loadStatusRepository.save(new LoadStatus("movies",true));
        }
        if (!loadStatusRepository.existsById("ratings")) {
            loadRating();
            loadStatusRepository.save(new LoadStatus("ratings",true));
        }

    }
    /* PS
       There are too many ways to read CSV(Comma Separated Values) files like
       ## BufferReader
       ## Using CSV library like OpenCSV or Apache Commons CSV
    */
    private void loadMovies() throws IOException{
        Path path = Paths.get("src/main/resources/data/movies.csv");
        List<String> allLines = Files.readAllLines(path);
        List<String>  lines = allLines.subList(1,allLines.size());
              //  .subList(1,Integer.MAX_VALUE);
        for (String line:lines){
            String[] parts = line.split(",",3);
            Long movieId = Long.parseLong(parts[0]);
            String title = parts[1].replace("\"","");
            String genres = parts[2];
            MovieEntity movie = MovieEntity.builder().
                    movieId(movieId).title(title).genres(genres).build();
            System.out.println("MOVIE DONE "+movie.getTitle());
                    movieRepository.save(movie);
        }
    }
    private void loadRating() throws IOException{
        Path path = Paths.get("src/main/resources/data/ratings.csv");
        List<String> allLines = Files.readAllLines(path);
        List<String>  lines = allLines.subList(1,allLines.size());
        for (String line:lines){
            String[] parts = line.split(",");
            Long movieId = Long.parseLong(parts[1]);
            Long userId = Long.parseLong(parts[0]);
            double ratingValue = Double.parseDouble(parts[2]);

            userRepository.findById(userId).orElseGet(()->{
                User user = User.builder().id(userId).build();
                return userRepository.save(user);
            });
            Rating rating = Rating.builder().id(new Rating.RatingId(userId,movieId)).rating(ratingValue).build();
            System.out.println("RATING" + rating.getRating());
            ratingRepository.save(rating);

        }
    }
}

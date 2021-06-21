package pl.skorpjdk.youtubeapirecruitment;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.skorpjdk.youtubeapirecruitment.model.ListOfSearch;

import java.io.IOException;

@RestController
@RequestMapping("/api/youtube/search")
@AllArgsConstructor
public class YouTubeController {

    private final YouTubeApiService youTubeApiService;
    @GetMapping
    public ResponseEntity<?> searchVideo(@RequestParam("searchVideo") String searchVideo){
        try {
            ListOfSearch listOfSearch = youTubeApiService.find(searchVideo);

            return new ResponseEntity<>(listOfSearch, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

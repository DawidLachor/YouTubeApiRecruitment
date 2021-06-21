package pl.skorpjdk.youtubeapirecruitment;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.skorpjdk.youtubeapirecruitment.model.ListOfSearch;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class YouTubeApiService {
    private final YouTube youTube;
    private final YouTubeRepository youTubeRepository;

    public ListOfSearch find(String searchVideo) throws IOException {
        Instant minusOneDay = Instant.now().minus(1, ChronoUnit.DAYS);
        Optional<ListOfSearch> search = youTubeRepository.findBySearchTextAndWhenSearchIsAfter(searchVideo, minusOneDay);

        ListOfSearch listOfSearch;
        if (search.isEmpty()) {
            listOfSearch = searchAndSave(searchVideo);
        } else {
            listOfSearch = search.get();
        }
        return listOfSearch;
    }

    private ListOfSearch searchAndSave(String searchVideo) throws IOException {
        SearchListResponse execute = searchInYoutube(searchVideo);
        return saveListOfSearch(searchVideo, execute);
    }

    private SearchListResponse searchInYoutube(String searchVideo) throws IOException {
        YouTube.Search.List request = youTube.search().list(Collections.singletonList("snippet"));
        return request
                .setQ(searchVideo)
                .execute();
    }

    private ListOfSearch saveListOfSearch(String searchVideo, SearchListResponse execute) {
        ListOfSearch listOfSearch = MappingModel.searchListResponseToListOfSearch(execute);
        listOfSearch.setSearchText(searchVideo);
        listOfSearch.setWhenSearch(Instant.now());
        return youTubeRepository.save(listOfSearch);
    }
}

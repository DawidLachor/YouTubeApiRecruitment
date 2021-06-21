package pl.skorpjdk.youtubeapirecruitment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.skorpjdk.youtubeapirecruitment.model.ListOfSearch;

import java.time.Instant;
import java.util.Optional;

public interface YouTubeRepository extends JpaRepository<ListOfSearch, Long> {

    Optional<ListOfSearch> findBySearchTextAndWhenSearchIsAfter(String text, Instant time);
}

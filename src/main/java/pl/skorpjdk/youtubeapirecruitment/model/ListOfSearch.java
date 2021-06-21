package pl.skorpjdk.youtubeapirecruitment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ListOfSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String kind;
    private String etag;
    private String nextPageToken;
    private String prevPageToken;
    private String regionCode;
    @OneToOne(cascade = CascadeType.ALL)
    private PageInfo pageInfo;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;
    private String searchText;
    private Instant whenSearch;
}

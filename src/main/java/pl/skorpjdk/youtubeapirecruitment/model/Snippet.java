package pl.skorpjdk.youtubeapirecruitment.model;

import com.google.api.client.util.DateTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Snippet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Instant publishedAt;
    private String channelId;
    private String title;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private Thumbnails thumbnails;
    private String channelTitle;
    private String liveBroadcastContent;
}

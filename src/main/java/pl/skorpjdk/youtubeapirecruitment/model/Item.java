package pl.skorpjdk.youtubeapirecruitment.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String kindId;
    private String videoId;
    private String chanelId;
    private String playlistId;
    @Column(name = "kind_item")
    private String kind;
    private String etag;
    @OneToOne(cascade = CascadeType.ALL)
    private Snippet snippet;
}

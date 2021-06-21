package pl.skorpjdk.youtubeapirecruitment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Thumbnails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Thumbnail defaultThumbnail;
    @OneToOne(cascade = CascadeType.ALL)
    private Thumbnail medium;
    @OneToOne(cascade = CascadeType.ALL)
    private Thumbnail high;
//    @OneToOne(cascade = CascadeType.ALL)
//    private Thumbnail standard;
//    @OneToOne(cascade = CascadeType.ALL)
//    private Thumbnail maxres;
}

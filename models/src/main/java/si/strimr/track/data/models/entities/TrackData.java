package si.strimr.track.data.models.entities;

import javax.persistence.*;
import java.sql.Blob;

@Entity(name = "track_data")
public class TrackData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "track_type")
    private String trackType;

    @Column(name = "track_metadata_id")
    private Integer trackMetadataId;

    @Column(name = "track_blob")
    private Blob trackBlob;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrackType() {
        return trackType;
    }

    public void setTrackType(String trackType) {
        this.trackType = trackType;
    }

    public Integer getTrackMetadataId() {
        return id;
    }

    public void setTrackMetadataId(Integer trackMetadataId) {
        this.trackMetadataId = trackMetadataId;
    }

    public Blob getTrackBlob() {
        return trackBlob;
    }

    public void setTrackBlob(Blob trackBlob) {
        this.trackBlob = trackBlob;
    }

}
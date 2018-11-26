package si.strimr.track.data.models.entities;

//import si.strimr.track.data.models.dtos.Order;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

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
    private String trackBlob;

    @Column(name = "track_filename")
    private String trackFilename;

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

    public String getTrackBlob() {
        return trackBlob;
    }

    public void setTrackBlob(String trackBlob) {
        this.trackBlob = trackBlob;
    }

    public String getTrackFilename() {
        return trackFilename;
    }

    public void setTrackFilename(String trackFilename) {
        this.trackFilename = trackFilename;
    }
}
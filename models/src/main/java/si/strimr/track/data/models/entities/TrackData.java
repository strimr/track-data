package si.strimr.track.data.models.entities;

import javax.persistence.*;

@Entity(name = "track_data")
public class TrackData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "track_metadata_id")
    private Integer trackMetadataId;

    @Column(name = "track_file_content")
    private String trackFileContent;

    @Column(name = "track_file_type")
    private String trackFileType;

    @Column(name = "track_file_name")
    private String trackFileName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrackMetadataId() {
        return trackMetadataId;
    }

    public void setTrackMetadataId(Integer trackMetadataId) {
        this.trackMetadataId = trackMetadataId;
    }

    public String getTrackFileContent() {
        return trackFileContent;
    }

    public void setTrackFileContent(String trackFileContent) {
        this.trackFileContent = trackFileContent;
    }

    public String getTrackFileType() {
        return trackFileType;
    }

    public void setTrackFileType(String trackFileType) {
        this.trackFileType = trackFileType;
    }

    public String getTrackFileName() {
        return trackFileName;
    }

    public void setTrackFileName(String trackFileName) {
        this.trackFileName = trackFileName;
    }

}
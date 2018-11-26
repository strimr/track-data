package si.strimr.track.data.services.beans;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.strimr.track.data.models.dtos.Order;
import si.strimr.track.data.models.entities.TrackData;
import si.strimr.track.data.services.configuration.AppProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.UriInfo;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@RequestScoped
public class TrackDataBean {

    private Logger log = Logger.getLogger(TrackDataBean.class.getName());

    @Inject
    private EntityManager em;

    @Inject
    private AppProperties appProperties;

    @Inject
    private TrackDataBean trackDataBean;

    private Client httpClient;

//    @Inject
//    @DiscoverService("rso-orders")
//    private Optional<String> baseUrl;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
//        baseUrl = "http://localhost:8081"; // only for demonstration
    }


    public List<TrackData> getAllTracks() {

        return Collections.emptyList();
    }

    public List<TrackData> getTrackDataFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, TrackData.class, queryParameters);
    }

    public TrackData getTrackData(Integer customerId) {

        TrackData trackData = em.find(TrackData.class, customerId);

        if (trackData == null) {
            throw new NotFoundException();
        }

        return trackData;
    }

    public TrackData createTrackData(TrackData trackData) {

        try {
            beginTx();
            em.persist(trackData);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return trackData;
    }

    public TrackData putTrackData(String trackId, TrackData trackData) {

        TrackData trackData1 = em.find(TrackData.class, trackId);

        if (trackData1 == null) {
            return null;
        }

        try {
            beginTx();
            trackData.setId(trackData1.getId());
            trackData = em.merge(trackData);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return trackData;
    }

    public boolean deleteTrackData(String trackId) {

        TrackData trackData = em.find(TrackData.class, trackId);

        if (trackData != null) {
            try {
                beginTx();
                em.remove(trackData);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else
            return false;

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }

    public void loadOrder(Integer n) {


    }
}

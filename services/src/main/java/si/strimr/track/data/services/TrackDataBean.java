package si.strimr.track.data.services;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.strimr.track.data.models.entities.TrackData;
import si.strimr.track.data.services.properties.AppProperties;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


@RequestScoped
public class TrackDataBean {

    private Logger log = Logger.getLogger(TrackDataBean.class.getName());

    @Inject
    private EntityManager em;

    @Inject
    private TrackDataBean trackDataBean;

    @Inject
    private AppProperties appProperties;

    public List<TrackData> getTrackDataFilter(UriInfo uriInfo) {

        if(!appProperties.isApiFilteringEnabled())
            return Collections.emptyList();

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, TrackData.class, queryParameters);
    }

    public TrackData getTrackData(Integer trackId) {

        TrackData trackData = em.find(TrackData.class, trackId);

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
}

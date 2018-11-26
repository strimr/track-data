package si.strimr.track.data.api.v1.resources;

<<<<<<< HEAD
import si.strimr.track.data.models.entities.TrackData;
import si.strimr.track.data.services.TrackDataBean;

import javax.enterprise.context.RequestScoped;
=======
import com.kumuluz.ee.logs.cdi.Log;
import si.strimr.track.data.models.entities.TrackData;
import si.strimr.track.data.services.beans.TrackDataBean;

import javax.enterprise.context.ApplicationScoped;
>>>>>>> master
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

<<<<<<< HEAD

@RequestScoped
@Path("/track-data")
=======
@Log
@ApplicationScoped
@Path("/customers")
>>>>>>> master
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrackDataResource {

    @Inject
    private TrackDataBean trackDataBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Path("/filtered")
    public Response getTrackDataFiltered() {

        List<TrackData> trackData;

        trackData = trackDataBean.getTrackDataFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(trackData).build();
    }

    @GET
    @Path("/{trackId}")
    public Response getTrackData(@PathParam("trackId") Integer trackId) {

        TrackData trackData = trackDataBean.getTrackData(trackId);

        if (trackData == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(trackData).build();
    }

    @POST
    public Response createTrackData(TrackData trackData) {

        if (trackData.getTrackMetadataId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            trackData = trackDataBean.createTrackData(trackData);
        }

        if (trackData.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(trackData).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(trackData).build();
        }
    }

    @PUT
    @Path("{trackId}")
    public Response putTrackData(@PathParam("trackId") String trackId, TrackData trackData) {

        trackData = trackDataBean.putTrackData(trackId, trackData);

        if (trackData == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            if (trackData.getId() != null)
                return Response.status(Response.Status.OK).entity(trackData).build();
            else
                return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("{trackId}")
    public Response deleteTrackData(@PathParam("trackId") String trackId) {

        boolean deleted = trackDataBean.deleteTrackData(trackId);

        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

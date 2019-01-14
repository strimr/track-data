package si.strimr.track.data.api.v1.resources;

//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import si.strimr.track.data.models.entities.TrackData;
import si.strimr.track.data.services.TrackDataBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;


@RequestScoped
@Path("/track-data")
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
    @Path("/{trackDataId}")
    public Response getTrackData(@PathParam("trackDataId") Integer trackDataId) {

        TrackData trackData = trackDataBean.getTrackData(trackDataId);

        if (trackData == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(trackData).build();
    }

    @POST
    public Response createTrackData(TrackData trackData) {

        if ((trackData.getTrackFileContent() == null
                || trackData.getTrackFileContent().isEmpty())
                || (trackData.getTrackFileName() == null
                || trackData.getTrackFileName().isEmpty())
                || (trackData.getTrackFileType() == null
                || trackData.getTrackFileType().isEmpty())
        ) {
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
    @Path("{trackDataId}")
    public Response putTrackData(@PathParam("trackDataId") String trackDataId, TrackData trackData) {

        trackData = trackDataBean.putTrackData(trackDataId, trackData);

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
    @Path("{trackDataId}")
    public Response deleteTrackData(@PathParam("trackDataId") String trackDataId) {

        boolean deleted = trackDataBean.deleteTrackData(trackDataId);

        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

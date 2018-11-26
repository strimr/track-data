package si.strimr.track.data.api.v1;

<<<<<<< HEAD
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

=======
import com.kumuluz.ee.discovery.annotations.RegisterService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@RegisterService
>>>>>>> master
@ApplicationPath("/v1")
public class TrackDataApplication extends Application {
}

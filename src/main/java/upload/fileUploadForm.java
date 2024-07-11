package upload;

import java.io.InputStream;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import jakarta.ws.rs.FormParam;

public class fileUploadForm {

	 @FormParam("file")
	    @PartType("application/octet-stream")
	    public InputStream file;
}

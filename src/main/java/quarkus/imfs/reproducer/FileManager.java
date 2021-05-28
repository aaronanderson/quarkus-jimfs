package quarkus.imfs.reproducer;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystem;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import com.google.common.jimfs.Jimfs;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class FileManager {
    private static final Logger LOGGER = Logger.getLogger("FileManager");

    void onStart(@Observes StartupEvent ev) throws IOException {
        try (FileSystem memFs = Jimfs.newFileSystem()) {
            String classLoaderName = Jimfs.class.getClassLoader().toString();
            Level logLevel = classLoaderName.contains("AppClassLoader") ? Level.INFO : Level.ERROR;
            LOGGER.logf(logLevel, "Loaded from ClassLoader %s", classLoaderName);
            final java.nio.file.Path memFsRoot = memFs.getPath("");
            final URL memFsRootURL = memFsRoot.toUri().toURL();

        }
    }
}

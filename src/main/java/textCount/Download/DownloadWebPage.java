package textCount.Download;

import java.nio.file.Path;

public interface DownloadWebPage {
	void download();
	boolean isDownloadSuccessfully();
	Path getDownloadFile();
}

package textCount.Download;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DownloadHtmlPageToHtmlFile implements DownloadWebPage {

	private URL url;
	private boolean downloadSuccessfully = false;
	private Path downloadFile;

	// для того чтобы исключить попытку создания уже существующего файла
	private Path pathDownloadPage = Paths
			.get("C:\\Users\\Public\\Downloads\\Download(" + System.currentTimeMillis() + ").html");

	public DownloadHtmlPageToHtmlFile(URL urlAdress) {
		this.url = urlAdress;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public Path getPathDownloadPage() {
		return pathDownloadPage;
	}

	public void setPathDownloadPage(Path pathDownloadPage) {
		this.pathDownloadPage = pathDownloadPage;
	}

	public boolean isDownloadSuccessfully() {
		return downloadSuccessfully;
	}

	public Path getDownloadFile() {
		return downloadFile;
	}

	@Override
	public void download() {

		try (InputStream is = url.openStream()) {
			downloadFile = Files.createFile(pathDownloadPage);
			Files.copy(is, downloadFile, StandardCopyOption.REPLACE_EXISTING);
			downloadSuccessfully = true;
			System.out.println(" * File successfully downloaded * ");
			
		} catch (IOException e) {
			System.out.println(" * File not downloaded * ");
			System.out.println("Исключение: " + e);
		}

	}
}
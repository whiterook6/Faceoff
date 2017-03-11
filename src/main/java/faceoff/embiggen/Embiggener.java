package faceoff.embiggen;

import java.io.File;
import java.io.IOException;

public class Embiggener {
	public static File embiggen_file(File toEmbiggen) throws IOException {

		String charset = "utf-8";
		String requestURL = "http://waifu2x.udp.jp/api";
		MultipartUtility multipart = new MultipartUtility(requestURL, charset);

		multipart.addFormField("style", "art");
		multipart.addFormField("noise", "1");
		multipart.addFormField("scale", "2");

		multipart.addFilePart("file", toEmbiggen);
		return multipart.finish();
	}
}

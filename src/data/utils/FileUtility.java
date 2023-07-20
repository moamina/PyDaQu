package data.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtility {

	public static boolean createAndWriteToFile(String file, String content) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			bw.write(content);
			return true;

		} catch (IOException e) {

			e.printStackTrace();
			return false;

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}

}

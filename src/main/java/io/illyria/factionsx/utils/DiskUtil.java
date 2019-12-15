package io.illyria.factionsx.utils;

import java.io.*;

public class DiskUtil {

	/**
	 * Description: Overlays the readFile and converts the bytes to a string.
	 *
	 * @param file File we want to convert to string.
	 * @throws IOException
	 * @return The entire to string.
	 */
	public static String readFileString(File file) throws IOException, FileNotFoundException {
		return new String(readFile(file));
	}

	/**
	 * Description: Writes data to a file using the FileOutPutStream.
	 *
	 * @param file          The file we want to to write out data to.
	 * @param bytes         What is being added into the file.
	 * @param addLineToFile I can't recall what it's called when you add somthing to the end of the file people correct
	 * @throws IOException
	 */
	public static void writeToFile(File file, byte[] bytes, boolean addLineToFile) throws IOException, FileNotFoundException {
		FileOutputStream fileOutputStream = new FileOutputStream(file, addLineToFile);

		fileOutputStream.write(bytes);
		fileOutputStream.flush();
		fileOutputStream.close();
	}

	/**
	 * Description: Reads a file using the FileInputStream then loop through the file
	 *
	 * @param file File we want to read.
	 * @return The returned bytes of the file.
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static byte[] readFile(File file) throws IOException, FileNotFoundException {
		FileInputStream fileInputStream = new FileInputStream(file);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[4096];
			int read = 0;
			while ((read = fileInputStream.read(buffer)) != -1)
				byteArrayOutputStream.write(buffer, 0, read);

		} finally {
			try {
				if (byteArrayOutputStream != null)
					byteArrayOutputStream.close();
			} catch (IOException e) {
			}

			try {
				if (fileInputStream != null)
					fileInputStream.close();
			} catch (IOException e) {
			}
		}
		return byteArrayOutputStream.toByteArray();
	}
}

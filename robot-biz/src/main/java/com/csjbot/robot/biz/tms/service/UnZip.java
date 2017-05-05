package com.csjbot.robot.biz.tms.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;

public class UnZip {
	private static final int BUFFER = 2048;

	public static void unzip(String file, String outDir) throws IOException {
		ZipFile zipFile = null;
		zipFile = new ZipFile(file);
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			File entryDestination = new File(outDir, entry.getName());
			if (entry.isDirectory()) {
				entryDestination.mkdirs();
			} else {
				entryDestination.getParentFile().mkdirs();
				InputStream in = zipFile.getInputStream(entry);
				OutputStream out = new FileOutputStream(entryDestination);
				IOUtils.copy(in, out);
				IOUtils.closeQuietly(in);
				out.close();
			}
		}
		zipFile.close();
	}

	@SuppressWarnings({ "rawtypes", "resource" })
	public static void unzip1(String file, String outParentDir, String outDirName) {
		String outDir = outParentDir + "/";
		try {
			BufferedOutputStream dest = null;
			BufferedInputStream is = null;
			ZipEntry entry;
			ZipFile zipfile = new ZipFile(file);
			Enumeration e = zipfile.entries();
			while (e.hasMoreElements()) {
				entry = (ZipEntry) e.nextElement();
				System.out.println("Extracting: " + entry);
				is = new BufferedInputStream(zipfile.getInputStream(entry));
				int count;
				byte data[] = new byte[BUFFER];
				FileOutputStream fos = new FileOutputStream(outDir + "/" + entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = is.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
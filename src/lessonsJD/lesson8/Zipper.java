package lessonsJD.lesson8;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Zipper {

    public static final String ZIP = "zip";
    public static final String UNZIP = "unzip";

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length != 2) {
            System.out.println("Usage: Zipper <command> <filename>");
            System.out.println("Commands: \n\t zip \n\t unzip");
        } else {

            Zipper zipper = new Zipper();

            if (ZIP.equals(args[0])) {

                File f = new File(args[1]);

                String path = f.getAbsolutePath();
                int idx = path.lastIndexOf(".");
                String namePath;
                if (idx != -1) {
                    namePath = path.substring(0, idx);
                } else {
                    namePath = path;
                }

                File newFile = new File(namePath + ".zip");

                try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(newFile))) {
                    zipper.zip(f, out);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (UNZIP.equals(args[0])) {

                try {
                    ZipFile zipFile = new ZipFile(args[1]);
                    Enumeration<? extends ZipEntry> entries = zipFile.entries();

                    while (entries.hasMoreElements()) {
                        ZipEntry entry = entries.nextElement();

                        File newF = new File(entry.getName());
                        newF.getParentFile().mkdirs();

                        if (!newF.isDirectory()) {
                            OutputStream out = new BufferedOutputStream(new FileOutputStream(newF));
                            zipper.write(zipFile.getInputStream(entry), out);
                            out.close();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void zip(File f, ZipOutputStream out) throws IOException {

        if (f.isDirectory()) {
            for (File file : f.listFiles()) {
                if (file.isDirectory()) {
                    zip(file, out);
                } else {
                    out.putNextEntry(new ZipEntry(file.getPath()));
                    write(new FileInputStream(file), out);
                }
            }
        } else {
            out.putNextEntry(new ZipEntry(f.getPath()));
            write(new FileInputStream(f), out);
        }
    }

    private void write(InputStream in, OutputStream out) throws IOException {

        byte[] array = new byte[1024];
        int i;
        while ((i = in.read(array)) != -1) {
            out.write(array, 0, i);
        }
        in.close();
    }
}

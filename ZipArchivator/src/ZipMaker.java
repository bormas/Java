import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by ultra on 05.08.2017.
 */
public class ZipMaker {
    //size of buffer
    public static final int BUFFER_SIZE = 4096;

    /**
     * type of a name of directory, file, zip-archive and etc
     * we need it to define zip-archive files
     */
    public enum NameType {
        Directory, File, ZipArchive, UnknownFormat;

        /**
         * defines the type of name
         * @param name name which we check
         * @return type of the name
         */
        public static NameType typeDefiner (String name)
        {
            Pattern zip = Pattern.compile(".+\\.(zip)");
            Matcher zipMatcher = zip.matcher(name);
            if (zipMatcher.matches())
                return NameType.ZipArchive;


            //first file is directory
            if (!name.contains("."))
                return NameType.Directory;

            Pattern file = Pattern.compile(".+\\..+");
            Matcher fileMatcher = file.matcher(name);
            if (fileMatcher.matches())
                return  NameType.File;

            System.out.println("Unknown name format");
            return NameType.UnknownFormat;
        }
    };

    /**
     * makes array of elements which path contains
     * @param path array of filenames
     * @return array of items or null when path is null
     */
    @Contract("null -> null")
    @Nullable
    public static File[] makeContent (String[] path) {
        if (path == null || path.length == 0)
            return null;
        File[] result = new File[path.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = new File(path[i]);
        }
        return result;
    }

    /**
     * makes full path of a path (you start from the project folder)
     * instead of full path input .../projectFolder/fileName you can input only /fileName
     * @param name path of an object
     * @return full path
     */
    @NotNull
    public static String makeFullPath (String name)
    {
        return System.getProperty("user.dir") + File.separator + name;
    }

    /**
     * defines what should program do to choose according to args
     * @param args like in main(String[] args)
     * @return 0 - make zip-archive; 1 - extract from archive; 2 - add files to existing archive
     */
    public static int argsParser(String[] args) {
        File[] arrayOfContent = makeContent(args);
        File zipFile = new File(args[0]);

        // Meaning: creating zip-archive
        // Format: zip-archive + file or directory (+ file or directory)
        if (NameType.typeDefiner(arrayOfContent[0].getName()) == NameType.ZipArchive  &&
            arrayOfContent.length > 1 && !zipFile.exists())
            return 0;

        // Meaning: extracting zip-archive
        // Format: zip-archive + destination folder
        // Format: zip-archive (destination folder generates automatically .../name.zip -> .../name
        // P.S.: zip-archive doesn't exist
        if (NameType.typeDefiner(arrayOfContent[0].getName()) == NameType.ZipArchive &&
           (arrayOfContent.length == 1 ||
           (arrayOfContent.length == 2 &&
            NameType.typeDefiner(arrayOfContent[1].getName()) == NameType.Directory &&
            !new File(args[1]).exists())))
            return 1;

        // Meaning: adding
        // Format: existing zip-archive + file or directory (+ file or directory)
        if (zipFile.exists() && arrayOfContent.length > 1)
            return 2;

        System.out.println("Wrong parameters type");
        return -1;
    }

    /**
     * deletes files
     * @param files array of files
     */
    private static void deleteFiles(ArrayList<File> files) {
        for (Iterator<File> f = files.iterator(); f.hasNext();) {
            f.next().delete();
        }
    }

    /**
     * makes one zip-archive
     * @param zipFileName name of zip-archive
     * @param whatToArchive files array which we want to archive
     * @throws Exception
     */
    public static void makeZip(String zipFileName, File[] whatToArchive) throws Exception {
        //when it's nothing to archive
        if (whatToArchive == null)
            return;

        // output file
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFileName));

        ArrayList<File> filesToCopy = new ArrayList<File>();

        //for each file or directory which we need to archive
        for (int i = 0; i < whatToArchive.length; i++) {
            String elemPath = whatToArchive[i].getAbsolutePath();
            File file = new File(elemPath);

            //not empty directory
            if (NameType.typeDefiner(whatToArchive[i].getName()) == NameType.Directory && file.listFiles().length != 0) {
                String[] filesNames = file.list();
                //making full paths
                for (int j = 0; j < filesNames.length; j++)
                    filesNames[j] = file.getPath() + File.separator + filesNames[j];
                //making new zip files in the same folder of their location
                String newZipFileName = elemPath + File.separator + file.getPath().substring(file.getPath().lastIndexOf("\\") + 1) + ".zip";
                //making temporary zip-archives
                makeZip(newZipFileName, makeContent(filesNames));
                //remember their location
                filesToCopy.add(new File(newZipFileName));
            }
            else if ((NameType.typeDefiner(whatToArchive[i].getName()) != NameType.Directory) ||
                     (NameType.typeDefiner(whatToArchive[i].getName()) == NameType.Directory && file.listFiles().length == 0))
                addFile(zout, file);
        }
        zout.close();
        //add all temporary objects to our zip-archive
        addFilesToExistingZip(new File(zipFileName), filesToCopy);
        //delete temporary objects
        deleteFiles(filesToCopy);
        filesToCopy.clear();
    }

    /**
     * adds files to existing zip-archive
     * @param zipFile zip-archive
     * @param files array of files
     * @throws IOException
     */
    private static void addFilesToExistingZip(File zipFile, ArrayList<File> files) throws IOException {
        // get a temp file
        File tempFile = File.createTempFile(zipFile.getName(), null);
        // delete it, otherwise you cannot rename your existing zip to it.
        tempFile.delete();

        boolean renameOk=zipFile.renameTo(tempFile);
        if (!renameOk)
        {
            throw new RuntimeException("could not rename the file " + zipFile.getAbsolutePath() + " to " + tempFile.getAbsolutePath());
        }
        byte[] buf = new byte[1024];

        ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));

        ZipEntry entry = zin.getNextEntry();
        while (entry != null) {
            String name = entry.getName();
            boolean notInFiles = true;
            for (Iterator<File> f = files.iterator(); f.hasNext();) {
                if (f.next().getName().equals(name)) {
                    notInFiles = false;
                    break;
                }
            }
            if (notInFiles) {
                // Add ZIP entry to output stream.
                out.putNextEntry(new ZipEntry(name));
                // Transfer bytes from the ZIP file to the output file
                int len;
                while ((len = zin.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
            entry = zin.getNextEntry();
        }
        // Close the streams
        zin.close();
        // Compress the files
        for (Iterator<File> f = files.iterator(); f.hasNext();) {
            File temp = f.next();
            InputStream in = new FileInputStream(temp);
            // Add ZIP entry to output stream.
            out.putNextEntry(new ZipEntry(temp.getName()));
            // Transfer bytes from the file to the ZIP file
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            // Complete the entry
            out.closeEntry();
            in.close();
        }
        // Complete the ZIP file
        out.close();
        tempFile.delete();
    }

    /**
     * adds file to the stream
     * @param zout ZipOutputStream
     * @param fileSource file which we add
     * @throws Exception
     */
    private static void addFile(ZipOutputStream zout, File fileSource) throws Exception {
        FileInputStream fis = new FileInputStream(fileSource);
        zout.putNextEntry(new ZipEntry(fileSource.getName()));

        byte[] buffer = new byte[BUFFER_SIZE];
        int length;
        while((length = fis.read(buffer)) > 0)
            zout.write(buffer, 0, length);
        //close ZipOutputStream и InputStream
        zout.closeEntry();
        fis.close();
    }

    /**
     * Extracts file from zip-archive recursively
     * @param zipFilePath path of the zip-archive
     * @param destDirectory output path,  if null then it has the same path as @param zipFilePath without ".zip"
     * @throws IOException
     */
    public static void extractZip(String zipFilePath, String destDirectory) throws IOException {
        //destDirectory is optional parameter
        if (destDirectory == null)
            destDirectory = zipFilePath.substring(0, zipFilePath.lastIndexOf("."));

        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();

        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);

                if (NameType.typeDefiner(entry.getName()) == NameType.ZipArchive) {
                    String newDestDirectory = destDirectory + File.separator + entry.getName().substring(0, entry.getName().lastIndexOf("."));
                    //extract files in archive
                    extractZip(filePath, newDestDirectory);
                    //deleting zip archive after extracting
                    File f = new File(filePath);
                    f.delete();
                }

            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    /**
     * Extracts a zip entry (file entry)
     *
     * @param zipIn
     * @param filePath
     * @throws java.io.IOException
     */
    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    /**
     * copies files to existing zip-archive
     * @param zipFileName name of zip-archive
     * @param whatToAdd array of files to copy
     * @throws IOException
     */
    private static void copyToArchive(String zipFileName, File[] whatToAdd) throws Exception {
        //when it's nothing to add
        if (whatToAdd == null)
            return;

        //list of copies
        ArrayList<File> filesToCopy = new ArrayList<File>();
        //list of trash objects
        ArrayList<File> filesToDelete = new ArrayList<File>();

        //for each file or directory which we need to archive
        for (int i = 0; i < whatToAdd.length; i++) {
            String elemPath = whatToAdd[i].getPath();
            if (NameType.typeDefiner(whatToAdd[i].getName()) == NameType.Directory) {
                File[] dir = new File[1];
                dir[0] = new File(elemPath);
                elemPath += ".zip";
                //making zip from folder
                makeZip(elemPath, dir);
                //remember temporary file
                filesToDelete.add(new File(elemPath));
            }

            filesToCopy.add(new File(elemPath));
        }
        //add files to existing zip
        addFilesToExistingZip(new File(zipFileName), filesToCopy);
        //delete temporary files
        deleteFiles(filesToDelete);
        filesToCopy.clear();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(argsParser(args));

        switch (argsParser(args))
        {
            case 0:
                //making zip archive recursively
                makeZip(makeFullPath(args[0]), makeContent(Arrays.copyOfRange(args, 1, args.length)));
                break;
            case 1:
                //extracting zip archive recursively, (destination path is optional)
                extractZip(makeFullPath(args[0]), args.length == 2 ? makeFullPath(args[1]) : null);
                break;
            case 2:
                //copy files or directories to archive
                copyToArchive(makeFullPath(args[0]), makeContent(Arrays.copyOfRange(args, 1, args.length)));
                break;
            case 3:
        }
    }
}

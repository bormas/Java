import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FileSearcher {
    //flag for additional information output
    private boolean additionalAttributesFlag;

    /**
     * Sets flag for additional attributes
     * @param flag this.additionalAttributesFlag
     */
    private void setAdditionalAttributesFlag(String flag) {
        this.additionalAttributesFlag = flag.equals("--a");
    }

    /**
     * Parses args and returns set of all information about demanded directories and files
     * @param args array of names (flag (optional) + file/directory names)
     * @return set of information
     */
    private Set<String[]> argsParser(String[] args)
    {
        Set<String[]> info = new HashSet<>();


        //if first goes flag
        int i = 0;
        setAdditionalAttributesFlag(args[0]);
        if (additionalAttributesFlag)
            i = 1;

        //list of existing files
        Set<String> files = new HashSet<>();

        for (; i < args.length; i++) {
            //temporary array of existing files according to pattern
            files = getFilenames(System.getProperty("user.dir"), args[i]);

            if (files.size() == 0)
                System.out.println("Files with pattern " + args[i] + " do not exist");

            //fills set with file's attributes
            for (Iterator<String> j = files.iterator(); j.hasNext();)
                info.add(attributesMaker(j.next()));
        }

        return info;
    }

    /**
     * Gets files' or directories' names according to pattern
     * @param startingDirectory where we begin our search
     * @param filePattern name pattern for file or directory
     * @return set of paths of existing files or directories
     */
    private Set<String> getFilenames(String startingDirectory, String filePattern) {
        //set starting directory
        File file = new File(startingDirectory);
        File[] allFiles = file.listFiles();

        //string set of file's names
        Set<String> result = new HashSet<>();

        try {
            //when we want info about all files
            if (filePattern.equals("*"))
                filePattern = ".*";
            Pattern pattern = Pattern.compile(filePattern);

            for (File f : allFiles) {
                if (f.isDirectory()) {
                    result.addAll(getFilenames(f.getPath(), filePattern));
                }
                Matcher m = pattern.matcher(f.getName());
                if (m.matches())
                    result.add(f.getAbsolutePath());
            }
        } catch (PatternSyntaxException ex) {
            System.out.println("Wrong pattern format");
        }
        return result;
    }

    /**
     * Checks the name for fullness
     * @param name file name
     * @return true = full name; false = short name
     */
    private boolean nameIsFull(String name) {
        return name.contains(System.getProperty("user.dir"));
    }

    /**
     * Obtains the size of directory (includes inner files)
     * @param directory directory
     * @return size of directory in bytes
     */
    private long directorySize(File directory) {
        long length = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile())
                length += file.length();
            else
                length += directorySize(file);
        }
        return length;
    }


    /**
     * Makes proper table row
     * @param attribute attribute, which is located in first 20 positions
     * @param value attribute's value, which is located in second 80 positions
     * @return formatted string
     */
    private String makeTableRow (String attribute, String value) {
        return String.format("| %-20s | %-100s |", attribute, value);
    }

    /**
     * Makes array of file attributes
     * @param fileName name of existing file or directory
     * @return array of information
     */
    private String[] attributesMaker(String fileName)
    {
        File file = new File(fileName);

        String[] result = new String[6];
        for (int i = 0; i < result.length; i++)
            result[i] = "";

        result[0] += makeTableRow("Name", file.getName());
        result[1] += makeTableRow("Full path", file.getAbsolutePath());

        if (file.isDirectory()) {
            result[2] += makeTableRow("Type", "Directory");
            File[] innerFiles = file.listFiles();

            if (innerFiles.length == 0)
                result[3] += makeTableRow("Content", "Empty");
            else
                result[3] += makeTableRow("Content", "Full");

            if (this.additionalAttributesFlag) {
                Date date = new Date(file.lastModified());
                result[4] += makeTableRow("Last modified", date.toString());
                result[5] += makeTableRow("Size in bytes", Long.toString(directorySize(file)));
            }
        } else {
            result[2] += makeTableRow("Type", "File");

            if (this.additionalAttributesFlag) {
                Date date = new Date(file.lastModified());
                result[3] += makeTableRow("Last modified", date.toString());
                result[4] += makeTableRow("Size in bytes", Long.toString(file.length()));
            }
        }
        return result;
    }

    /**
     * Prints all information about args
     * @param args files' names and flags
     */
    public void printInfo(String[] args) {
        //obtaining all information about demanded files
        Set<String[]> info = argsParser(args);
        if (info.size() == 0)
            return;

        //printing
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        for (Iterator<String[]> iterator = info.iterator(); iterator.hasNext();) {
            String[] temp = iterator.next();
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].length() != 0)
                    System.out.println(temp[i]);
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public static void main(String[] args)
    {
        if (args.length == 0) {
            System.out.println("Input file names or patterns of files");
            System.out.println("For extended attributes input flag '--a' on the first place before files' names");
            System.out.print(">> ");

            // Reading from System.in
            Scanner reader = new Scanner(System.in);
            String string = reader.nextLine();
            args = string.split(" ");
        }

        FileSearcher fs = new FileSearcher();
        //here it goes
        fs.printInfo(args);
    }
}

package other.util;

import java.util.ArrayList;
import java.util.Objects;

/**
 * <p>file1、words1List、words1String: 傲慢与偏见
 * <p>file2、words2List、words2String: 双城记
 */
public class Novel
{

    private Novel()
    {
    }

    /**
     * 傲慢与偏见
     */
    public static final ArrayList<String> words1List = new ArrayList<>();

    /**
     * 双城记
     */
    public static final ArrayList<String> words2List = new ArrayList<>();

    /**
     * 傲慢与偏见
     */
    public static final String words1String;

    /**
     * 双城记
     */
    public static final String words2String;

    static
    {
        String file1 = Objects.requireNonNull(Novel.class.getResource("/pride-and-prejudice.txt")).getPath();
        String file2 = Objects.requireNonNull(Novel.class.getResource("/a-tale-of-two-cities.txt")).getPath();
        FileOperation.readFile(file1, words1List);
        FileOperation.readFile(file2, words2List);
        words1String = FileOperation.readFile(file1);
        words2String = FileOperation.readFile(file2);
    }
}

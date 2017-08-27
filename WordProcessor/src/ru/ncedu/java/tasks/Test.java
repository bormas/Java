package ru.ncedu.java.tasks;

/**
 * Created by ultra on 16.08.2017.
 */
public class Test {
    public static void main(String[] args)
    {
        WordProcessor wp = new WordProcessorImpl();
        wp.setSource("Although he initially explained away the delay in condemnations of white supremacists as necessary for him to gather \"the facts\" of the situation, the nature of the protests were quite evident by the evening before, when demonstrators chanting white supremacist slogans held a torchlight parade through Charlottesville.\n" +
                "In any regard, Mr Trump has shown little reluctance in jumping to conclusions about violent incidents when it appears Islamic extremism is at play.\n" +
                "Upon further questioning, it became clear that the president views the Charlottesville unrest as far from a one-sided affair. Mixed in among the white supremacists, he said, were some good, peaceful people protesting the removal of a statue (of a man who led an army against the US government). And there were plenty of violent individuals among the counter-protesters as well.");
        System.out.println(wp.wordsStartWith("h"));
        System.out.println(wp.wordsStartWith(""));
        System.out.println(wp.wordsStartWith(null));
    }
}

package IteratorPattern;

import java.util.HashSet;
import java.util.Set;

public class DemoIteratorMain {
    public static void main(String[] args) {
        Series series = new Series();

        // Season 1
        Season s1 = new Season();
        s1.addEpisode(new Episode("S1E1 - Pilot", 1200));
        s1.addEpisode(new Episode("S1E2 - The Awakening", 1400));
        series.addSeason(s1);

        // Season 2
        Season s2 = new Season();
        s2.addEpisode(new Episode("S2E1 - Return", 1500));
        s2.addEpisode(new Episode("S2E2 - Finale", 1600));
        series.addSeason(s2);

        System.out.println("== Normal Iterator ==");
        for (Episode e : s1) {
            System.out.println(e);
        }

        System.out.println("\n== Reverse Iterator ==");
        ReverseSeasonIterator rev = new ReverseSeasonIterator(s1);
        while (rev.hasNext()) {
            System.out.println(rev.next());
        }

        System.out.println("\n== Shuffle Iterator ==");
        ShuffleSeasonIterator shuf = new ShuffleSeasonIterator(s1, 42);
        while (shuf.hasNext()) {
            System.out.println(shuf.next());
        }

        System.out.println("\n== Binge Iterator (whole series) ==");
        BingeIterator binge = new BingeIterator(series);
        while (binge.hasNext()) {
            System.out.println(binge.next());
        }

        // NEW PART
        System.out.println("\n== Skip Intro Iterator ==");
        SkipIntroIterator skipIntro = new SkipIntroIterator(new SeasonIterator(s1), 90);
        while (skipIntro.hasNext()) {
            EpisodeView view = skipIntro.next();
            view.play();
        }

        System.out.println("\n== Watch History Iterator (show only UNWATCHED) ==");
        Set<String> watched = new HashSet<>();
        watched.add("S1E1 - Pilot");  // Assume already watched

        WatchHistoryIterator watchHistory = new WatchHistoryIterator(new SeasonIterator(s1), watched);
        while (watchHistory.hasNext()) {
            System.out.println(watchHistory.next());
        }
    }
}


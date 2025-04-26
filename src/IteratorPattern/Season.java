package IteratorPattern;

import java.util.*;

public class Season implements Iterable<Episode> {
    private List<Episode> episodes = new ArrayList<>();

    public void addEpisode(Episode e) {
        episodes.add(e);
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    // Default iterator: normal order
    @Override
    public Iterator<Episode> iterator() {
        return new SeasonIterator(this);
    }
}

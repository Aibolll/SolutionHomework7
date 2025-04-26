package IteratorPattern;

import java.util.Iterator;
import java.util.List;

public class ReverseSeasonIterator implements EpisodeIterator {
    private int index;
    private List<Episode> episodes;

    public ReverseSeasonIterator(Season season) {
        this.episodes = season.getEpisodes();
        this.index = episodes.size() - 1;
    }

    public boolean hasNext() {
        return index >= 0;
    }

    public Episode next() {
        return episodes.get(index--);
    }
}

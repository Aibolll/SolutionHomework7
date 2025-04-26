package IteratorPattern;

import java.util.*;

public class BingeIterator implements EpisodeIterator {
    private Iterator<Season> seasonIterator;
    private EpisodeIterator currentEpisodeIterator;

    public BingeIterator(Series series) {
        this.seasonIterator = series.getSeasons().iterator();
        moveToNextSeason();
    }

    private void moveToNextSeason() {
        if (seasonIterator.hasNext()) {
            currentEpisodeIterator = new SeasonIterator(seasonIterator.next());
        } else {
            currentEpisodeIterator = null;
        }
    }

    public boolean hasNext() {
        while (currentEpisodeIterator != null && !currentEpisodeIterator.hasNext()) {
            moveToNextSeason();
        }
        return currentEpisodeIterator != null && currentEpisodeIterator.hasNext();
    }

    public Episode next() {
        return currentEpisodeIterator.next();
    }
}


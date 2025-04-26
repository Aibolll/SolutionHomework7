package IteratorPattern;

import java.util.Set;
import java.util.HashSet;

public class WatchHistoryIterator implements EpisodeIterator {
    private EpisodeIterator baseIterator;
    private Set<String> watchedTitles; // you could also use Episode references
    private Episode nextUnwatched;

    public WatchHistoryIterator(EpisodeIterator baseIterator, Set<String> watchedTitles) {
        this.baseIterator = baseIterator;
        this.watchedTitles = watchedTitles;
        advance();
    }

    private void advance() {
        nextUnwatched = null;
        while (baseIterator.hasNext()) {
            Episode candidate = baseIterator.next();
            if (!watchedTitles.contains(candidate.getTitle())) {
                nextUnwatched = candidate;
                break;
            }
        }
    }

    public boolean hasNext() {
        return nextUnwatched != null;
    }

    public Episode next() {
        Episode current = nextUnwatched;
        advance();
        return current;
    }
}

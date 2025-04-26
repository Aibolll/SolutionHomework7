package IteratorPattern;

public class SkipIntroIterator {
    private EpisodeIterator baseIterator;
    private int skipSeconds;

    public SkipIntroIterator(EpisodeIterator baseIterator, int skipSeconds) {
        this.baseIterator = baseIterator;
        this.skipSeconds = skipSeconds;
    }

    public boolean hasNext() {
        return baseIterator.hasNext();
    }

    public EpisodeView next() {
        Episode ep = baseIterator.next();
        return new EpisodeView(ep, skipSeconds);
    }
}


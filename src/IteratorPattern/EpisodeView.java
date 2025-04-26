package IteratorPattern;

public class EpisodeView {
    private Episode episode;
    private int startSec;

    public EpisodeView(Episode episode, int startSec) {
        this.episode = episode;
        this.startSec = startSec;
    }

    public void play() {
        System.out.println("Playing " + episode.getTitle() + " starting at " + startSec + " seconds...");
    }
}


package object;

import motor.Interfaccia;

import java.util.Comparator;

public class Anime implements Interfaccia {
    private String title, studio, mangaka, plot, image;
    private int release, season, episodes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }
    public String getMangaka() {
        return mangaka;
    }

    public void setMangaka(String mangaka) {
        this.mangaka = mangaka;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return  title+ formatRegex+
                studio+ formatRegex+
                mangaka+ formatRegex+
                release+ formatRegex+
                season+ formatRegex+
                episodes+ formatRegex+
                plot+ formatRegex+
                image+"\n";
    }

    public static Comparator<Anime> titleComparatorAZ = new Comparator<Anime>() {

        public int compare(Anime a1, Anime a2) {
            String animeName1
                    = a1.getTitle().toUpperCase();
            String animeName2
                    = a2.getTitle().toUpperCase();

            // Returning in ascending order
            return animeName1.compareTo(
                    animeName2);

        }
    };

    public static Comparator<Anime> titleComparatorZA = new Comparator<Anime>() {

        public int compare(Anime a1, Anime a2) {
            String animeName1
                    = a1.getTitle().toUpperCase();
            String animeName2
                    = a2.getTitle().toUpperCase();

            // Returning in descending order
            return animeName2.compareTo(
                    animeName1);

        }
    };
}

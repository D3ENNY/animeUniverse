package motor;

import object.Anime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class List {

    private final java.util.List<Anime> backupList = new ArrayList<>();
    private final java.util.List<Anime> animeList = new ArrayList<>();
    private final Map<String,String> image = new HashMap<>();

    public java.util.List<Anime> getAnimeList() {return animeList;}

    public Map<String, String> getImage() {return image;}

    public void aZ_Sort(){
        backup();
        animeList.sort(Anime.titleComparatorAZ);
    }

    public void zA_Sort(){
        backup();
        animeList.sort(Anime.titleComparatorZA);
    }

    public void normal_Sort(){
        backup();
        animeList.clear();
        animeList.addAll(backupList);
    }

    public void searchList(java.util.List<Anime>scAnimeList){
        backup();
        animeList.clear();
        animeList.addAll(scAnimeList);
    }
    /*class method*/

    private void backup(){
        if(backupList.isEmpty())
            animeList.addAll(backupList);
    }
}

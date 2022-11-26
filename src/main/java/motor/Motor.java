package motor;

import object.Anime;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class Motor implements Interfaccia{
    private  String selectedAnime;

    public  void setSelectedAnime(String selectedAnime) {this.selectedAnime = selectedAnime;}

    private static void newFile(){
        try {
            File myObj = new File(path);
            if (!myObj.exists())
                myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  void query(List lst) throws IOException {
        newFile();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                createObject(line, lst);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public  void createObject(String s, List lst) {
        if (!s.equalsIgnoreCase("")) {
            Anime an = new Anime();
            String[] arr = s.split(formatRegex);
            an.setTitle(arr[0]);
            an.setStudio(arr[1]);
            an.setMangaka(arr[2]);
            an.setRelease(Integer.parseInt(arr[3]));
            an.setSeason(Integer.parseInt(arr[4]));
            an.setEpisodes(Integer.parseInt(arr[5]));
            an.setPlot(arr[6]);
            an.setImage(arr[7]);

            lst.getAnimeList().add(an);
        }

    }

    public  void writeFile(java.util.List<Anime> animeList) {
        newFile();
        try (FileWriter wr = new FileWriter(path)) {
            for (Anime anime : animeList ) {
                wr.append(anime.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Boolean deleteFromFile(List lst) {
        System.out.println("delete from file    ------- "+selectedAnime);
        boolean flag = false;
        for (Anime anime : lst.getAnimeList()) {
            if (anime.getTitle().equals(selectedAnime)) {
                flag = true;
                break;
            }
        }
        if (!flag) return false;


        lst.getAnimeList().removeIf(anime -> anime.getTitle().equals(selectedAnime));
        writeFile(lst.getAnimeList());

        return true;

    }

    public void searchAnime(String data, List lst){
        java.util.List<Anime>scAnimeList=new ArrayList<>();

        if(!data.isBlank()) {
            for (Anime an : lst.getAnimeList()) {
                if (formatStr(an.getTitle()).contains(formatStr(data)) && formatStr(an.getTitle()).startsWith(formatStr(data)))
                    scAnimeList.add(an);
            }
            for (Anime an : lst.getAnimeList()) {
                if(formatStr(an.getTitle()).contains(formatStr(data))&&!formatStr(an.getTitle()).startsWith(formatStr(data))) scAnimeList.add(an);
            }
            lst.searchList(scAnimeList);
        }else lst.normal_Sort();
    }

    private String formatStr(String str){return str.toLowerCase(Locale.ROOT).trim();}

}



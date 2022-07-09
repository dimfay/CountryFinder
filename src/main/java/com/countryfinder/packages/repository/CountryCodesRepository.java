package com.countryfinder.packages.repository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;

@Data
@Slf4j
@Repository
public class CountryCodesRepository {
    private HashMap<String, String> codes = new HashMap<>();

    public HashMap<String, String> getCodes() {
        return codes;
    }

    public CountryCodesRepository() throws IOException {
        var file = new File("src/main/resources/countries");
        if (isFileEmpty(file)){
            extractCodesFromWiki();
            fillFile(file);
        } else {
            fillMapWithCodesFromFile(file);
        }
    }

    private boolean isFileEmpty(File file){
        return file.length()==0;
    }

    private void extractCodesFromWiki(){
        try {
            final String url = "https://en.wikipedia.org/wiki/List_of_country_calling_codes";
            final Document document = Jsoup.connect(url).get();

            Element table = document.select("table.wikitable.sortable").get(0);
            Elements rows = table.select("tr");

            for (Element row : rows){
                if (!row.select("td:nth-of-type(1)").text().equals("")){
                    String countryName = row.select("td:nth-of-type(1)").text();
                    String rowOfCodes = row.select("td:nth-of-type(2)").text();
                    List<String> countryCodes = formatCodes(rowOfCodes);

                    Queue<String> queue = new LinkedList<>(countryCodes);
                    while(!queue.isEmpty()){
                        codes.put(queue.poll(), countryName);
                    }
                }
            }
        } catch(Exception ex){
            log.error("Could not extract info from wiki page", ex);
        }
    }

    private List<String> formatCodes(String rowOfCodes){
        rowOfCodes = rowOfCodes.replace(", ", "-");
        rowOfCodes = rowOfCodes.replace(" ", "");
        rowOfCodes = rowOfCodes.replaceAll("\\[.*\\]", "");
        rowOfCodes = rowOfCodes.replaceAll("[a-zA-Z]", "");
        return Arrays.asList(rowOfCodes.split("-"));
    }

    private void fillFile(File file) throws IOException {
        try {
            var writer = new FileWriter(file);

            for (Map.Entry<String, String> entry : codes.entrySet()) {
                writer.write(entry.toString());
                writer.write("\n");
            }
            writer.flush();
            writer.close();
        } catch (Exception e){
            log.error("Could not fill file with codes", e);
        }
    }

    private void fillMapWithCodesFromFile(File file) throws IOException {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] codeAndName = line.split("=");
                codes.put(codeAndName[0], codeAndName[1]);
            }
            scanner.close();
        } catch (Exception ex){
            log.error("Could not fill map with codes from file", ex);
        }
    }

}

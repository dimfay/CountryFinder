package com.countryfinder.packages.repository;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import java.util.*;

@Data
@Repository
public class CountryCodesRepository {
    private HashMap<String, String> codes = new HashMap<>();

    public HashMap<String, String> getCodes() {
        return codes;
    }

    public CountryCodesRepository(){
        fillMapWithCodesFromWikiPage();
    }

    private void fillMapWithCodesFromWikiPage(){
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
            ex.printStackTrace();
        }
    }

    private List<String> formatCodes(String rowOfCodes){
        rowOfCodes = rowOfCodes.replace(", ", "-");
        rowOfCodes = rowOfCodes.replace(" ", "");
        rowOfCodes = rowOfCodes.replaceAll("\\[.*\\]", "");
        rowOfCodes = rowOfCodes.replaceAll("[a-zA-Z]", "");
        return Arrays.asList(rowOfCodes.split("-"));
    }

}

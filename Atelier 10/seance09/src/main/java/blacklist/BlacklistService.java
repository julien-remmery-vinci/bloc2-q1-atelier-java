package blacklist;

import domaine.Query;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class BlacklistService {
    private static Set<String> blacklistedDomains;

    static {
        try(InputStream input = new FileInputStream("../../../../blacklist.properties")){
            Properties properties = new Properties();
            properties.load(input);
            blacklistedDomains = new HashSet<>();
            blacklistedDomains.addAll(Arrays.asList(properties.getProperty("blacklistedDomains").split(";")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean check(Query query){
        //return !blacklistedDomains.contains(query.getUrl());
        return blacklistedDomains.stream().noneMatch(s -> query.getUrl().contains(s));
    }
}

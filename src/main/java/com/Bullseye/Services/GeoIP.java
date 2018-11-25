package com.Bullseye.Services;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service("GeoIP")
public class GeoIP implements GeoIPService
{
    public DatabaseReader GeoIP()
    {
        InputStream FileLoc = getClass().getResourceAsStream("/GeoLiteCity.mmdb");
        try
        {
            DatabaseReader reader = new DatabaseReader.Builder(FileLoc).build();
            return(reader);
        }
        catch(Exception E)
        {
            System.out.println("An Exception Occured While Trying To Load The GeoLiteCity.mmdb Database! " + E.toString());
        }
        return(null);
    }
}
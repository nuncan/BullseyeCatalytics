package com.Bullseye.Controllers;

import java.net.InetAddress;
import com.Bullseye.Services.GeoIPService;
import com.maxmind.geoip2.model.CityResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController
{
    // Injecting Maxmind GeoIP Dependency
    @Autowired
    GeoIPService hGeo;
    
    //
    //  Returns GeoIP Info For Requesting User
    //
    @RequestMapping(value = { "/API/GeoIP" }, method = RequestMethod.GET)
    public CityResponse getLocationInJSON(HttpServletRequest hRequest)
    {
        String IP = hRequest.getRemoteAddr();
        try
        {
            InetAddress ipAddress = InetAddress.getByName(IP);
            CityResponse response = hGeo.GeoIP().city(ipAddress);
            return(response);
        }
        catch(Exception E)
        {
            System.out.println("An Exception Occured While Trying To Lookup GeoIP Info! " + E.toString());
        }
        return(null);      
    }
    
    //
    //  Returns GeoIP Info For A Specified IP Address
    //
    @RequestMapping(value = { "/API/GeoIP/{argIPAddress}" }, method = RequestMethod.GET)
    public CityResponse getLocationInJSON(@PathVariable("argIPAddress") String argIPAddress, HttpServletRequest hRequest)
    {
        try
        {
            InetAddress ipAddress = InetAddress.getByName(argIPAddress);
            CityResponse response = hGeo.GeoIP().city(ipAddress);
            return(response);
        }
        catch(Exception E)
        {
            System.out.println("An Exception Occured While Trying To Lookup GeoIP Info! " + E.toString());
        }
        return(null);      
    }
}
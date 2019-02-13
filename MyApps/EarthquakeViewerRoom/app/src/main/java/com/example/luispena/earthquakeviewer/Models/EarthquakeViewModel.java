package com.example.luispena.earthquakeviewer.Models;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.example.luispena.earthquakeviewer.Earthquake;
import com.example.luispena.earthquakeviewer.R;
import com.example.luispena.earthquakeviewer.Room.EarthquakeDatabaseAccessor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class EarthquakeViewModel extends AndroidViewModel {

    private static final String TAG = "EarthquakeUpdate";

    // Mutable variable that represents a list of earthquakes.
    // this view model will be cached and maintained across configuration changes.

    private LiveData<List<Earthquake>> earthQuakes;

    public EarthquakeViewModel(Application application) {
        super(application);
    }

    public LiveData<List<Earthquake>> getEarthquakes(){
        if(earthQuakes == null){
            EarthquakeDatabaseAccessor.getInstance(getApplication()).earthquakeDAO().loadAllEarthquakes();
            loadEarthQuakes();
        }

        return earthQuakes;
    }

    @SuppressLint("StaticFieldLeak")
    public void loadEarthQuakes() {
        new AsyncTask<Void, Void, List<Earthquake>>() {
            @Override
            protected List<Earthquake> doInBackground(Void... voids) {
                // Result ArrayList of parsed earthquakes.
                ArrayList<Earthquake> earthquakes = new ArrayList<>(0);


                // Get the XML
                URL url;
                try {
                    String quakeFeed = getApplication().getString(R.string.earthquake_feed);
                    url = new URL(quakeFeed);
                    URLConnection connection;
                    connection = url.openConnection();
                    HttpURLConnection httpConnection = (HttpURLConnection)connection;
                    int responseCode = httpConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream in = httpConnection.getInputStream();
                        DocumentBuilderFactory dbf =
                                DocumentBuilderFactory.newInstance();
                        DocumentBuilder db = dbf.newDocumentBuilder();

                        // Parse the earthquake feed.
                        Document dom = db.parse(in);
                        Element docEle = dom.getDocumentElement();

                        // Get a list of each earthquake entry.
                        NodeList nl = docEle.getElementsByTagName("entry");
                        if (nl != null && nl.getLength() > 0) {
                            for (int i = 0 ; i < nl.getLength(); i++) {
                                // Check to see if our loading has been cancelled, in which
                                // case return what we have so far.
                                if (isCancelled()) {
                                    Log.d(TAG, "Loading Cancelled");
                                    return earthquakes;
                                }
                                Element entry =
                                        (Element)nl.item(i);
                                Element id =
                                        (Element)entry.getElementsByTagName("id").item(0);
                                Element title =
                                        (Element)entry.getElementsByTagName("title").item(0);
                                Element g =
                                        (Element)entry.getElementsByTagName("georss:point")
                                                .item(0);
                                Element when =
                                        (Element)entry.getElementsByTagName("updated").item(0);
                                Element link =
                                        (Element)entry.getElementsByTagName("link").item(0);
                                String idString = id.getFirstChild().getNodeValue();
                                String details = title.getFirstChild().getNodeValue();
                                String hostname = "http://earthquake.usgs.gov";
                                String linkString = hostname + link.getAttribute("href");
                                String point = g.getFirstChild().getNodeValue();
                                String dt = when.getFirstChild().getNodeValue();
                                SimpleDateFormat sdf =
                                        new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
                                Date qdate = new GregorianCalendar(0,0,0).getTime();
                                try {
                                    qdate = sdf.parse(dt);
                                } catch (ParseException e) {
                                    Log.e(TAG, "Date parsing exception.", e);
                                }
                                String[] location = point.split(" ");
                                Location l = new Location("dummyGPS");
                                l.setLatitude(Double.parseDouble(location[0]));
                                l.setLongitude(Double.parseDouble(location[1]));
                                String magnitudeString = details.split(" ")[1];
                                int end = magnitudeString.length()-1;
                                double magnitude =
                                        Double.parseDouble(magnitudeString.substring(0, end));
                                if (details.contains("-"))
                                    details = details.split("-")[1].trim();
                                else
                                    details = "";
                                final Earthquake earthquake = new Earthquake(idString,
                                        qdate,
                                        details, l,
                                        magnitude,
                                        linkString);

                                // Add the new earthquake to our result array.
                                earthquakes.add(earthquake);
                            }
                        }
                    }
                    httpConnection.disconnect();
                } catch (MalformedURLException e) {
                    Log.e(TAG, "MalformedURLException", e);
                } catch (IOException e) {
                    Log.e(TAG, "IOException", e);
                } catch (ParserConfigurationException e) {
                    Log.e(TAG, "Parser Configuration Exception", e);
                } catch (SAXException e) {
                    Log.e(TAG, "SAX Exception", e);
                }

                EarthquakeDatabaseAccessor
                        .getInstance(getApplication())
                        .earthquakeDAO()
                        .insertEarthquakes(earthquakes);

                // Return our result array.
                return earthquakes;
            }

            @Override
            protected void onPostExecute(List<Earthquake> data) {
                // Update the Live Data with the new list.
               // earthQuakes.setValue(data);
            }
        }.execute();
    }

}

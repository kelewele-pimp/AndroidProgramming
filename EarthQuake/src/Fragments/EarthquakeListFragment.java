package Fragments;

import android.app.ListFragment;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.earthquake.Earthquake;

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
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import Model.Quake;

public class EarthquakeListFragment extends ListFragment {

    ArrayAdapter<Quake> adapter;
    ArrayList<Quake> earthQuakes = new ArrayList<Quake>();

    private static final String TAG = "EARTHQUAKE";
    private Handler handler = new Handler();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int layoutID = android.R.layout.simple_list_item_1;
        adapter = new ArrayAdapter<Quake>(getActivity(), layoutID, earthQuakes);
        setListAdapter(adapter);

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                refreshEarthquakes();

            }
        });
        t.start();
    }

    public void refreshEarthquakes() {
        //Get the XML
        URL url;
        try {
            String quakeFeed = getString(com.example.earthquake.R.string.quake_feed);
            url = new URL(quakeFeed);

            URLConnection connection = url.openConnection();

            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpConnection.getInputStream();

                //NEW DOM PARSING INSTANCE
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();

                //Parse the eartqauke feed
                Document dom = db.parse(in);
                Element docElement = dom.getDocumentElement();

                //CLEAR THE OLD EARTHQUAKES
                earthQuakes.clear();

                //Get a list of each earthquake entry
                NodeList nl = docElement.getElementsByTagName("entry");
                if (nl != null && nl.getLength() > 0) {
                    for (int i = 0; i < nl.getLength(); i++) {
                        Element entry = (Element) nl.item(i);
                        Element title = (Element) entry.getElementsByTagName("title").item(0);
                        Element g = (Element) entry.getElementsByTagName("georss:point").item(0);
                        Element when = (Element) entry.getElementsByTagName("updated").item(0);
                        Element link = (Element) entry.getElementsByTagName("link").item(0);

                        String details = title.getFirstChild().getNodeValue();
                        String hostname = "http://earthquake.usgs.gov";
                        String linkString = hostname + link.getAttribute("href");

                        String point = g.getFirstChild().getNodeValue();
                        String dt = when.getFirstChild().getNodeValue().replaceFirst("Z", "+0000");


                        String format = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
                        SimpleDateFormat sdf = new SimpleDateFormat(format);
                        sdf.setTimeZone(TimeZone.getDefault());
                        Date qdate = new GregorianCalendar(0, 0, 0).getTime();
                        try {
                            qdate = sdf.parse(dt);
                        } catch (ParseException e) {
                            Log.d(TAG, "Date parsing exception", e);
                        }

                        String[] location = point.split(" ");
                        Location l = new Location("dummyGPS");
                        l.setLatitude(Double.parseDouble(location[0]));
                        l.setLongitude(Double.parseDouble(location[1]));

                        String magnitudeString = details.split(" ")[1];
                        int end = magnitudeString.length() - 1;
                        double magnitude = Double.parseDouble(magnitudeString.substring(0, end));

//						details = details.split(",")[1].trim(); 
                        if (details.contains(",")) {
                            details = details.split(",")[1].trim();
                        } else {
                            details = details.split("-")[1].trim();
                        }

                        final Quake quake = new Quake(qdate, details, l, magnitude, linkString);

                        //PROCESS A NEWLY FOUND EARTHQUAKE
                        handler.post(new Runnable() {

                            @Override
                            public void run() {
                                addNewQuake(quake);
                            }
                        });
                    }
                }
            }
        } catch (MalformedURLException e) {
            Log.d(TAG, "MalformedURLException");
        } catch (IOException e) {
            Log.d(TAG, "IOException");
        } catch (ParserConfigurationException e) {
            Log.d(TAG, "Parser Configuration Exception");
        } catch (SAXException e) {
            Log.d(TAG, "SAX Exception");
        }
    }

    private void addNewQuake(Quake quake) {
        //Add new earthquake to our list of earthquakes
        Earthquake earthquakeActivity = (Earthquake) getActivity();
        if (quake.getMagnitude() > earthquakeActivity.minimumMagnitude) {
            //Add the new quake to the list of earthquakes
            earthQuakes.add(quake);
        }
        //Notify the array adapter of change
        adapter.notifyDataSetChanged();
    }

}

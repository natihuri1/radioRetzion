package com.mysmarthome.android.retzion;

import android.os.AsyncTask;
import android.util.Log;

import com.mysmarthome.android.retzion.Backendless.RadioBroadcasts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class BroadcastServies extends AsyncTask<Void, Void, String> {
    List<BroadcastModel> mylist;
    BradcastServiesInterface myInterface;

    @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String url = "http://be.repoai.com:5080/WebRTCAppEE/rest/broadcast/getVodList/0/100?fbclid=IwAR2RT0XzuNLLWC5g2HKuy-BBAgSKUtzG6MVS68DssOXvskVIbK4_YzfXyz4s";

            HttpURLConnection connection = null;

            try {
                connection = (HttpURLConnection) new URL(url).openConnection();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder sb = new StringBuilder();

                String line;
                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                return sb.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(connection != null){
                    connection.disconnect();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String aString) {
            String jsonString = aString;
            if(jsonString != null){
                try {
                    JSONArray jsonArray = new JSONArray(jsonString);
                    this.mylist = new ArrayList<>();
                    for(int i=0; i<jsonArray.length(); i+=1){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        RadioBroadcasts radioBroadcasts= new RadioBroadcasts();
                        radioBroadcasts.setBroadcast(jsonObject.getString("vodName"));
                   BroadcastModel newBroadcastModel = new BroadcastModel();
                        newBroadcastModel.streamName = jsonObject.getString("streamName");
                        newBroadcastModel.vodName = jsonObject.getString("vodName");
                        newBroadcastModel.streamId = jsonObject.getString("streamId");
                        newBroadcastModel.filePath = jsonObject.getString("filePath");
                        newBroadcastModel.type = jsonObject.getString("type");

                        newBroadcastModel.duration= jsonObject.getInt("duration");
                        newBroadcastModel.fileSize= jsonObject.getInt("fileSize");
                        newBroadcastModel.vodId= jsonObject.getInt("vodId");

                        newBroadcastModel.creationDate =jsonObject.getLong("creationDate");

                        mylist.add(newBroadcastModel);
                    }
                    this.myInterface.broadcastReturn();
                    Log.i("Broadcast List Length: ", String.valueOf(mylist.size()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }

}

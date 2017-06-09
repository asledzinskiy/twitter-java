/**
 * Created by asledzinskiy on 06.06.17.
 */
/*
 * Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpHost;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

class BaseHttpClient {

}
class TwitterClient {
    String consumerKey = "TgCs2L7UlxzeygG4bvMzO7nMz";
    String consumerSecret = "jbCcTY7NF1qCnPg0BP2UIaVI65AedD08icHXaGhsWaL56zrYjZ";
    String accessToken = "625011534-Fd15DyhL26AdAs82jh9VNhYdqSwdoEWmeZry0JDc";
    String secretToken = "vXLt34KHk2aE5tyth7q4fT8uhSbdludAc4NN1PYstQCjB";

    CloseableHttpClient httpClient;
    OAuthConsumer consumer;

    String twitterHost = "https://api.twitter.com/1.1/";

    TwitterClient() {
        System.out.println("setting secret token");
        httpClient = HttpClients.createDefault();
        consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(accessToken, secretToken);
    }

    public HttpResponse sendGet(String endpoint) throws IOException {
        HttpGet getRequest = new HttpGet(twitterHost + endpoint);
        try {
            consumer.sign(getRequest);
        } catch (OAuthMessageSignerException ex) {
            System.out.println();
        } catch (OAuthExpectationFailedException ex) {
            System.out.println();
        } catch (OAuthCommunicationException ex) {
            System.out.println();
        }
        HttpResponse response = httpClient.execute(getRequest);
        HttpEntity entity = response.getEntity();
        System.out.print(EntityUtils.toString(entity));
        return response;
    }

    public HttpResponse sendPost(String endpoint, List data) throws IOException {
        System.out.println("consumer is " + consumer);
        HttpPost postRequest = new HttpPost("https://api.twitter.com/1.1/statuses/update.json");
        postRequest.setEntity(new UrlEncodedFormEntity(data));
        postRequest.addHeader("content-type", "application/x-www-form-urlencoded");
        /*
        postRequest.setEntity(data);
        postRequest.addHeader("content-type", "application/json");
        postRequest.addHeader("accept","application/json");
        */
        try {
            consumer.sign(postRequest);
        } catch (OAuthMessageSignerException ex) {
            System.out.println();
        } catch (OAuthExpectationFailedException ex) {
            System.out.println();
        } catch (OAuthCommunicationException ex) {
            System.out.println();
        }

        HttpResponse response = httpClient.execute(postRequest);
        HttpEntity entity = response.getEntity();
        System.out.print(EntityUtils.toString(entity));
        return response;
    }

    public void create_tweet(String msg) throws IOException, org.json.JSONException {
/*
        JSONObject json = new JSONObject();
        json.put("source", "httpbuilder");
        json.put("status", msg);
        System.out.println("json is " + json);
        StringEntity se = new StringEntity( json.toString());
        se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
  */
        List se = new ArrayList(1);
        se.add(new BasicNameValuePair("status", msg));
        System.out.println("string entity is " + se);
        sendPost("update.json", se);
    }
}





public class TwitterTest {

    TwitterClient twitter = new TwitterClient();


    @Test
    public void create_tweet() throws IOException, org.json.JSONException {
        //twitter.create_tweet("test tweet");
        twitter.sendGet("statuses/home_timeline.json");
    }
}

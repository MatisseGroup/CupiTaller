/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.Matisse.API;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Jairo
 */
public class ServiciosAPI {

    // ------------------------------
    // Atributos
    // ------------------------------
    private static final String url = "http://localhost/index.php?r=admin/remotecontrol";
    private static HttpPost post;
    private static HttpClient cliente;
    private static String key;
    private static ServiciosAPI instance;

    // -----------------------------
    // Constructor
    // -----------------------------
    private ServiciosAPI() {
        cliente = HttpClients.createDefault();
        post = new HttpPost(url);
        post.setHeader("Content-type", "application/json");
        key = getSessionKey();
    }

    // ----------------------------
    // Metodos utiles para el app
    // ----------------------------
    public static ServiciosAPI getInstance() {
        return (instance == null) ? instance = new ServiciosAPI() : instance;
    }

    public static String parse(String jsonLine) {
        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject jobject = jelement.getAsJsonObject();
        String result = jobject.get("result").getAsString();
        return result;
    }

    public static String getSessionKey() {
        try {
            post.setEntity(new StringEntity("{\"method\": "
                    + "\"get_session_key\", "
                    + "\"params\": {\"username\": \"admin\", \"password\": "
                    + "\"password\" }, \"id\": 1}"));
            HttpResponse response = cliente.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entidad = response.getEntity();
                key = parse(EntityUtils.toString(entidad));
            }
        } catch (IOException e) {
            System.err.println("error para conseguir el key");
        } catch (ParseException e) {
            System.err.println("error para conseguir el key");
        }
        return key;
    }

    public String transformFromB64(String toConvert) {
        return new String(Base64.decodeBase64(toConvert));
    }

    public JSONObject getQuestionsProperties(int qId) {
        JSONObject pedido = new JSONObject();
        try {
            JsonObject request = new JsonObject();
            JsonObject params = new JsonObject();
            request.addProperty("id", 1);
            request.addProperty("method", "get_question_properties");

            params.addProperty("sSessionKey", key);

            params.addProperty("iQuestionID", qId);
            JsonArray jsonarray = new JsonArray();
            JsonPrimitive qid = new JsonPrimitive("qid");
            JsonPrimitive title = new JsonPrimitive("title");
            JsonPrimitive answeroptions = new JsonPrimitive("answeroptions");
            JsonPrimitive questions = new JsonPrimitive("question");
            jsonarray.add(qid);
            jsonarray.add(title);
            jsonarray.add(answeroptions);
            jsonarray.add(questions);
            params.add("aQuestionSettings", jsonarray);
            request.add("params", params);
            post.setEntity(new StringEntity(request.toString()));
            HttpResponse response = cliente.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entidad = response.getEntity();
                JSONObject json = new JSONObject(EntityUtils.toString(entidad));
                pedido = json.getJSONObject("result");
            }
            return pedido;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public JSONArray listQuestions(int survey, int group) {
        JSONArray pedido = new JSONArray();
        JsonObject request = new JsonObject();
        JsonObject params = new JsonObject();
        request.addProperty("id", 1);
        request.addProperty("method", "list_questions");
        params.addProperty("sSessionKey", key);
        params.addProperty("iSurveyID", survey);
        params.addProperty("iGroupID", group);
        request.add("params", params);
        try {
            post.setEntity(new StringEntity(request.toString()));
            HttpResponse response = cliente.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entidad = response.getEntity();
                JSONObject json = new JSONObject(EntityUtils.toString(entidad));
                pedido = json.getJSONArray("result");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return pedido;
    }

    public JSONArray listGroups(int survey) {
        JSONArray pedido = new JSONArray();
        JsonObject request = new JsonObject();
        JsonObject params = new JsonObject();
        request.addProperty("id", 1);
        request.addProperty("method", "list_groups");
        params.addProperty("sSessionKey", key);
        params.addProperty("iSurveyID", survey);
        request.add("params", params);
        try {
            post.setEntity(new StringEntity(request.toString()));
            HttpResponse response = cliente.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entidad = response.getEntity();
                JSONObject json = new JSONObject(EntityUtils.toString(entidad));
                pedido = json.getJSONArray("result");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return pedido;
    }

    public JSONObject exportResponses() {
        JSONObject pedido = new JSONObject();
        JsonObject request = new JsonObject();
        JsonObject params = new JsonObject();
        request.addProperty("id", 1);
        request.addProperty("method", "export_responses");
        params.addProperty("sSessionKey", key);
        params.addProperty("iSurveyID", 68124);
        params.addProperty("sDocumentType", "json");
        request.add("params", params);
        try {
            post.setEntity(new StringEntity(request.toString()));
            HttpResponse response = cliente.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entidad = response.getEntity();
                JSONObject json = new JSONObject(EntityUtils.toString(entidad));
                String result = transformFromB64(json.getString("result"));
                pedido = new JSONObject(result);
            }
        } catch (UnsupportedEncodingException ex) {
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return pedido;
    }
}

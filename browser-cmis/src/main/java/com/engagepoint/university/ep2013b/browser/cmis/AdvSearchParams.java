package com.engagepoint.university.ep2013b.browser.cmis;

import org.apache.chemistry.opencmis.client.api.QueryStatement;
import org.apache.chemistry.opencmis.client.runtime.ObjectIdImpl;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;


public class AdvSearchParams implements Serializable {

    private Map<String, Object> params = new HashMap<String, Object>();

    public AdvSearchParams() {

        params.put("folderId", null);
        params.put("docType", null); //// ???
        params.put("fromDate",null);
        params.put("toDate", null);
        params.put("contentType", null);
        params.put("size", null);
        params.put("text", null);

    }


    public AdvSearchParams(
            String id, String docType, Date fromDate,
            Date toDate, String contentType, Integer size, String text) {

        params.put("folderId", id);
        params.put("docType", docType);
        params.put("fromDate", fromDate);
        params.put("toDate", toDate);
        params.put("contentType", contentType);
        params.put("size", size);
        params.put("text", text);

    }

    public String getFolderId() {
        return (String) params.get("folderId");
    }

    public String getDocumentType() {
        return (String) params.get("docType");
    }

    public Date getDateFrom() {
        return (Date) params.get("fromDate");
    }

    public Date getDateTo() {
        return (Date) params.get("toDate");
    }

    public String getContentType() {
        return (String) params.get("contentType");
    }

    public Integer getSize() {
        return (Integer) params.get("size");
    }

    public String getText() {
        return (String) params.get("text");
    }

    public void setFolderId(String id) {
        params.put("folderId", id);
    }

    public void setDocumentType(String docType) {
        if ((docType != null) && (!"".equals(docType))) params.put("docType", docType);
    }

    public void setDateFrom(Date fromDate) {
        params.put("fromDate", fromDate);
    }

    public void setDateTo(Date toDate) {
        params.put("toDate", toDate);
    }

    public void setContentType(String contentType) {
        if ((contentType != null) && (!"".equals(contentType))) params.put("contentType", contentType);
    }

    public void setSize(Integer size) {
        if (size > 0) params.put("size", size);
    }

    public void setText(String text) {
        if ((text != null) && (!"".equals(text))) params.put("text", text);
    }

    // Empty if every search criteria is not set
    public boolean isEmpty()
    {
        return (getDocumentType() == null);
    }


    public String createQueryString() {

        String preparedQueryString = "";

        String inDocums = "SELECT * FROM ?";
        String inFolder = " WHERE IN_FOLDER(?)";
        String multiple = " AND ";


        String paramDateFrom = "cmis:creationDate >= TIMESTAMP ?";
        String paramDateTo = "cmis:creationDate <= TIMESTAMP ?";

        String paramContType = "cmis:objectTypeId = ?"; // "cmis:contentStreamMimeType = ?";
        String paramSize = "cmis:contentStreamLength = ?";
        String paramText = "CONTAINS(?)";

        preparedQueryString = inDocums + inFolder;
        //// anyOptions + paramDateFrom + multiple + paramDateTo

        boolean flagAnyOptions = false;
        boolean flagMulti = false;


        if(params.get("fromDate") != null){
             preparedQueryString = preparedQueryString +  multiple +  paramDateFrom;
        }


        if(params.get("toDate") != null){
            preparedQueryString = preparedQueryString +  multiple + paramDateTo;
        }


        if(params.get("contentType") != null){
            preparedQueryString = preparedQueryString + multiple + paramContType;
        }


        if(params.get("size") != null){
            preparedQueryString = preparedQueryString + multiple + paramSize;
        }


        if(params.get("text") != null){
            preparedQueryString = preparedQueryString + multiple + paramText;
        }

//        System.out.println("preparedQueryString = " + preparedQueryString);

        return preparedQueryString;

    }


    public QueryStatement setQueryParams(QueryStatement query) {

        String type = "cmis:document";   /// ???

        //// everytimes
        if(params.get("docType") != null){
            query.setType(1, params.get("docType").toString());
        } else {
            query.setType(1, type);
        }
        query.setId(2, new ObjectIdImpl(params.get("folderId").toString()));

        int paramCounter = 3;  // have already 2 params


		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        if(params.get("fromDate") != null){
//            query.setDateTime(paramCounter++, (Date) params.get("fromDate"););
			Date date = (Date) params.get("fromDate");
			String d = format.format(date) + "T00:00:00";
			query.setString(paramCounter++, d);
        }


        if(params.get("toDate") != null){
			Date date = (Date) params.get("toDate");

			String time = "T00:00:00";
			if (params.get("toDate").equals(params.get("fromDate"))) time = "T23:59:59";
			String d = format.format(date) + time;


			query.setString(paramCounter++, d);
//            query.setDateTime(paramCounter++, d);
        }


        if(params.get("contentType") != null){
            query.setString(paramCounter++, params.get("contentType").toString());
        }


        if(params.get("size") != null){
            query.setNumber(paramCounter++, (Number) params.get("size"));
        }


        if(params.get("text") != null){
            query.setString(paramCounter++, params.get("text").toString());
        }

//        System.out.println("QueryStatement = " + query.toQueryString());

        return query;

    }

}



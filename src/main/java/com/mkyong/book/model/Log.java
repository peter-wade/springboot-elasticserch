package com.mkyong.book.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "applog", type = "logs")
public class Log {

   private String id;
   
   private String message;

   private String version;
   
   private String timestamp;
   
   private String path;
   
   private String host;
   
   private String createDate;
   
   

    public Log() {
    }

    public Log(String id, String message, String version, String timestamp,String path,String host,String createDate) {
        this.id = id;
        this.message = message;
        this.version = version;
        this.timestamp = timestamp;
        this.path = path;
        this.host = host;
        this.createDate = createDate;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}



	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", message=" + message + ", version=" + version + ", timestamp=" + timestamp
				+ ", path=" + path + ", host=" + host + ", createDate=" + createDate + "]";
	}



	

    
}

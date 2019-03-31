package com.gdf.book.util;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author Gerald F.
 *
 */
public class BookResponse {
	@JsonView(BookResponseView.Response.class)
	private int status_code;

	@JsonView(BookResponseView.Response.class)
    private String status;

	@JsonView(BookResponseView.ResponseWithMessage.class)
    private String message;

	@JsonView(BookResponseView.Response.class)
    private Object data;

    public BookResponse(int status_code, String status, String message, Object data) {
        this.status_code = status_code;
        this.status = status;
        this.message = message;
        this.data = (data != null) ? data : new Object[]{};
    }

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}

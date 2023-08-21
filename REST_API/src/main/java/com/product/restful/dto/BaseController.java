package com.product.restful.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

public abstract class BaseController {

	protected static <T> BaseResponse<T> ok(T data) {
		return new BaseResponse<>(data);
	}

	@Data
	public static class BaseResponse<T> {

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
		private Date creationDate = new Date();

		private T data;

		public BaseResponse(T data) {
			this.data = data;
		}

	}

}

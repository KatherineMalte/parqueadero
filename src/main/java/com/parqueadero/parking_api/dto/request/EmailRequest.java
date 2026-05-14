package com.parqueadero.parking_api.dto.request;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailRequest {
	 private ConfigParams configParams;
	    private Receivers receivers;
	    private Email email;

	    @Getter
	    @Setter
	    @NoArgsConstructor
	    @AllArgsConstructor
	    @Builder
	    public static class ConfigParams {
	        private String idUser;
	        private String idMessage;
	    }

	    @Getter
	    @Setter
	    @NoArgsConstructor
	    @AllArgsConstructor
	    @Builder
	    public static class Receivers {
	        private String emailOrigen;
	        private List<String> to;
	        private List<String> copyTo;
	        private List<String> hiddenCopyTo;
	    }

	    @Getter
	    @Setter
	    @NoArgsConstructor
	    @AllArgsConstructor
	    @Builder
	    public static class Email {
	        private String subject;
	        private String urlHeader;
	        private String urlFooter;
	        private String message;
	        private List<String> url_files;
	    }
}

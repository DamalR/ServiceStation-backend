package lk.damal.hdrservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ResponseDTO {
    @Getter
    @Setter
    private boolean success;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private Object data;

    public ResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseDTO(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}

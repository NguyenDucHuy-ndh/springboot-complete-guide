package vn.huynguyen.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorResponse {
    private Date timestamp; // thoi gian xay ra loi
    private int status; // ma loi
    private String path; // duong dan
    private String error; // ten loi
    private String message; // thong bao loi
}

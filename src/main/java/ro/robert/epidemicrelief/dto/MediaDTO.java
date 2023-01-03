package ro.robert.epidemicrelief.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
@Getter
@Setter
public class MediaDTO {
    private Long id;
    private String name;
    private Blob data;

}

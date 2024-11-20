package com.limon.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful response Information"
)
public class ResponseDto {
    @Schema(
            name = "Status Code",
            description = "Schema to hold Status Code"
    )
    private String statusCode;

    @Schema(
            name = "Status Message",
            description = "Schema to hold Status Message"
    )
    private String statusMsg;
}

package dev.knoblauch.piilog.data;

import lombok.Data;

@Data
public class ModelResponse {
    private Boolean is_sensitive;
    private Float probability;
}

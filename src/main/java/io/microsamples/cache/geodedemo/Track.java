package io.microsamples.cache.geodedemo;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.Indexed;
import org.springframework.data.gemfire.mapping.Region;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@Region("Tracks")
public class Track implements Serializable {

    @Id
    private Long id;

    @Indexed
    @NonNull
    private String name;
}

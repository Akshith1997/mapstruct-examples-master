/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.example.dto;

import lombok.*;

/**
 * @author Gunnar Morling
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GolfPlayerDto {

    private double handicap;
    private String name;

    // Missing fields
    private String settlement;
    private String issuer;
}
